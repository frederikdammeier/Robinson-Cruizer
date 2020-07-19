package de.dhbw.ravensburg.zuul;

import de.dhbw.ravensburg.zuul.room.*;
import de.dhbw.ravensburg.zuul.ui.ItemSprite;
import de.dhbw.ravensburg.zuul.creature.*;
import de.dhbw.ravensburg.zuul.item.*;
import java.util.ArrayList;

/**
 * This class is the main class of the "RobinsonCruizer" application.
 * 
 * To play this game, create an instance of this class and call the "play"
 * method.
 * 
 * This main class creates and initialises all the others: it creates all rooms,
 * creates the parser and starts the game. It also evaluates and executes the
 * commands that the parser returns.
 * 
 * @author Michael Kölling and David J. Barnes - further developed by Frederik
 *         Dammeier - Philipp Schneider
 * @version 19.07.2020
 */

public class Game{
	private Parser parser;
	private Room currentRoom;
	private Timer timer;
	private Player player;
	private boolean finished;
	private boolean dead;
	private Map map;
	private long timeLimit;
	private float enemyDamageRate;
	private BoatBuilding boatBuilder;
	private Predator predator;
	private Thread predatorThread;
	private RegenerationHandler regenHandler;
	private HungerHandler hungerHandler;

	/**
	 * Create the game and initialize its internal map.
	 */
	public Game(Difficulty difficulty) {
		timer = new Timer();
		map = new Map();
		parser = new Parser();
		predator = new Predator(this);
		player = new Player("Players Name", difficulty.getInventoryCapacity(), 100);
		boatBuilder = new BoatBuilding();
		currentRoom = map.getCurrentRoom();
		timeLimit = difficulty.getTimeLimit();
		enemyDamageRate = difficulty.getEnemyDamageRate();
		regenHandler = new RegenerationHandler(player);
		hungerHandler = new HungerHandler(player);
	}

	/**
	 * Starts all background treads and therefore starts the game.
	 */
	public void start() {
		printWelcome();

		// Add a new Timer to measure passed game Time.
		Thread timeThread = new Thread(timer, "timer");
		timeThread.start();
		
		//Regeneration Thread
		Thread regenThread = new Thread(regenHandler, "regenerate");
		regenThread.start();

		//Hunger Thread
		Thread hungerThread = new Thread(hungerHandler, "hunger");
		hungerThread.start();
	}
	
	/**
	 * Call when the game has finished. Closes all threads.
	 */
	public void endGame() {
		System.out.println("Thank you for playing.  Good bye!");
		timer.stopTimer();
		//predatorThread.interrupt();
		regenHandler.finish();
		hungerHandler.finish();
		finished = true;
		
	}

	/**
	 * --------------------------------
	 * only for playing in console mode
	 * --------------------------------
	 * Print the Seconds that have passed since starting the game to the console.
	 * 
	 * @deprecated
	 */
	private void printTimePassed() {
		System.out.println(timer.getTimePassedSeconds() + " seconds have passed since starting the game.");
	}

	/**
	 * Print out the opening message for the player.
	 */
	private void printWelcome() {
		System.out.println();
		System.out.println("Welcome to the Robinson Cruizer Island Adventure!");
		System.out.println("Type 'help' if you need help.");
		System.out.println();
		lookAround();
	}

	/**
	 * --------------------------------
	 * only for playing in console mode
	 * --------------------------------
	 * Given a command, process (that is: execute) the command.
	 * 
	 * @deprecated
	 * @param command The command to be processed.
	 * @return true If the command ends the game, false otherwise.
	 */
	private boolean processCommand(Command command) {
		boolean wantToQuit = false;

		if (command.isUnknown()) {
			System.out.println("I don't know what you mean...");
			return false;
		}

		String commandWord = command.getCommandWord();
		if (commandWord.equals("help")) {
			printHelp();
		} else if (commandWord.equals("go")) {
			goRoom(command);
		} else if (commandWord.equals("look")) {
			lookAround();
		} else if (commandWord.equals("quit")) {
			wantToQuit = quit(command);
		} else if (commandWord.equals("attack")) {
			playerAttack();
		} else if (commandWord.equals("teleport")) {
			teleport();
		} else if (commandWord.equals("take")) {
			takeItem(command);
		} else if (commandWord.equals("drop")) {
			dropItem(command);
		} else if (commandWord.equals("eat")) {
			eatFood(command);
		} else if (commandWord.equals("showInv")) {
			player.getInventory().printContents();
		} else if (commandWord.equals("buildBoat")) {
        	boatBuilder.buildBoat(player.getInventory());
        	System.out.println("A boat has been built and added to your inventory.");
        }
		else if(commandWord.equals("talk")) {
			currentRoom.getCreature().talk(player.getInventory(), parser);
		}
		// else command not recognised.
		return wantToQuit;
	}

	/**
	 * --------------------------------
	 * only for playing in console mode
	 * --------------------------------
	 * Player tries to eat something.
	 * 
	 * @deprecated
	 * @param command Information about what the player tries to eat.
	 */
	private void eatFood(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know what to eat...
			System.out.println("Eat what?");
			return;
		}
		
		Item toEat = player.getInventory().getItemByName(command.getSecondWord());
		
		if (toEat instanceof Food) {
			if(toEat.equals(new MagicMushroom())) {
				player.getInventory().setUnlimited();
				System.out.println("inventory unlimited");
			}
			player.eat(((Food) toEat).getNutrition());							
			player.getInventory().removeItem(toEat);
			System.out.println("tasty!");
		}	
		else {
			System.out.println("not edible");
		}
		
	}
	
	/**
	 * Processes an attack request by the player. Valid request cause damage to be
	 * transfered to a creature, therefore reducing its health. Dead creatures are
	 * removed from a rooms inventory. Dead creatures might drop an item.
	 */
	public void playerAttack() {

		if (player.getDamage() == 0) {
			System.out.println("You don't have a weapon to attack.");
		} else {

			// check if there is no creature or only an invincible one.
			if (currentRoom.getCreature() == null || currentRoom.getCreature().isInvincible()) {
				System.out.println("There are no creatures to attack.");
			}

			else {
				// set the time of this attack.
				player.setTimeOfLastAttack(timer.getTimePassedSeconds());

				// get the creature of this room.
				Creature creatureInRoom = currentRoom.getCreature();
				
				if(player.getPlayerSprite().distanceTo(currentRoom.getCreatureSprite().getCenterX(), currentRoom.getCreatureSprite().getCenterY()) < 100){
					// decrease the creatures health by the amount of damage the player can
					// tranfered.
					creatureInRoom.takeDamage(player.getDamage());

					if (creatureInRoom.isDead()) {
						// Try to add the drop item of the creature into the rooms inventory.
						try {
							currentRoom.addItem(creatureInRoom.getDropItem(), currentRoom.getCreatureSprite().getCenterX(), currentRoom.getCreatureSprite().getCenterY());
						} catch (Exception e) {
							System.out.println("The " + creatureInRoom.getName() + " dropped nothing.");
						}

						// "deletes" the creature in the current room.
						currentRoom.setCreature(null);
					}
				}	
			}
		}
	}

	/**
	 * --------------------------------
	 * only for playing in console mode
	 * --------------------------------
	 * Print out some help information. Here we print some stupid, cryptic message
	 * and a list of the command words.
	 * 
	 * @deprecated
	 */
	private void printHelp() {
		System.out.println("You are lost. You are alone. You wander");
		System.out.println("around the island.");
		System.out.println();
		System.out.println("Your command words are:");
		parser.showCommands();
	}

	/**
	 * --------------------------------
	 * only for playing in console mode
	 * --------------------------------
	 * Can be used to ask the player a yes/no question.
	 * 
	 * @deprecated
	 * @param command
	 * @return true if yes, false if otherwise
	 */
	private boolean getYesNoAnswer(Command command) {
		if (command.isUnknown()) {
			System.out.println("I dont know what you mean.");
			return false;
		} else if (command.getCommandWord().equals("yes")) {
			return true;
		}
		return false;
	}

	/**
	 * --------------------------------
	 * only for playing in console mode
	 * --------------------------------
	 * 
	 * Try to go in to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 * 
	 * If a room is locked, the method checks whether the player has the correct key
	 * to open it. Then the player gets asks whether he would like to open the door
	 * to the next room.
	 * 
	 * If a room can only be entered through a trap door, the player gets a warning.
	 * He gets asked whether he would like to proceed anyways.
	 * 
	 * @deprecated
	 */
	private void goRoom(Command command) {
		if (!command.hasSecondWord()) {
			// if there is no second word, we don't know where to go...
			System.out.println("Go where?");
			return;
		}

		String direction = command.getSecondWord();

		// Try to leave current room.
		Room nextRoom = currentRoom.getExit(direction);

		// check if a exit to the desired direction even exists.
		if (nextRoom == null) {
			System.out.println("There is no way to go!");

			// Check whether the room is locked.
		} else if (nextRoom.isLocked()) {
			if (player.getInventory().containsItem(nextRoom.getKey())) {

				// Ask the player if he wants to unlock the room.
				System.out.println("The room is locked. Do you want to unlock it now? (yes/no)");
				if (getYesNoAnswer(parser.getCommand())) {
					nextRoom.unLock();
					currentRoom = nextRoom;
					System.out.println("Room unlocked.");
					System.out.println(currentRoom.getLongDescription());
				} else {
					System.out.println("Allright, have a nice day.");
				}
			} else {
				System.out.println("The room is locked. Try to find a key.");
			}

			// Check whether the exit is through a trapdoor.
		} else if (!nextRoom.hasExitToRoom(currentRoom) && !nextRoom.getType().equals(RoomType.FINISH)) {

			// Asks the player if he would like to enter anyways.
			System.out.println("You are trying to enter a trapdoor. Proceed? (yes/no)");
			if (getYesNoAnswer(parser.getCommand())) {
				currentRoom = nextRoom;
				System.out.println("Good Luck!");
				System.out.println(currentRoom.getLongDescription());
			} else {
				System.out.println("Allright, have a nice day.");
			}
			// Default
		} else {
			// interrupt preditorThread when leaving a room
			predatorThread.interrupt();
			currentRoom = nextRoom;
			System.out.println(currentRoom.getLongDescription());

			// restarting the preditorThread
			predatorThread = new Thread(predator, "predator");
			predatorThread.start();
		}
		
	    //Message the player if he is ready to leave the island()
	    if(currentRoom instanceof Beach && player.getInventory().containsItem(new Boat())) {
	    	System.out.println("You are now ready to leave the island.");
	    	map.activateFinish();
	    }
	}
	
	/**
	 * --------------------------------
	 * only for playing in console mode
	 * --------------------------------
	 * 
	 * Try to go in to one direction. If there is an exit, enter the new room,
	 * otherwise print an error message.
	 * 
	 * If a room is locked, the method checks whether the player has the correct key
	 * to open it. Then the player gets asks whether he would like to open the door
	 * to the next room.
	 * 
	 * If a room can only be entered through a trap door, the player gets a warning.
	 * He gets asked whether he would like to proceed anyways.
	 * 
	 * @deprecated
	 */
	public String goRoom(String direction) {

		// Try to leave current room.
		Room nextRoom = currentRoom.getExit(direction);

		// check if a exit to the desired direction even exists.
		if (nextRoom == null) {
			System.out.println("There is no way to go!");

			// Check whether the room is locked.
		} else if (nextRoom.isLocked()) {
			if (player.getInventory().containsItem(nextRoom.getKey())) {

				// Ask the player if he wants to unlock the room.
				System.out.println("The room is locked. Do you want to unlock it now? (yes/no)");
				if (getYesNoAnswer(parser.getCommand())) {
					nextRoom.unLock();
					currentRoom = nextRoom;
					System.out.println("Room unlocked.");
					System.out.println(currentRoom.getLongDescription());
					
					return "Success";
				} else {
					System.out.println("Allright, have a nice day.");
				}
			} else {
				System.out.println("The room is locked. Try to find a key.");
			}

			// Check whether the exit is through a trapdoor.
		} else if (!nextRoom.hasExitToRoom(currentRoom) && !nextRoom.getType().equals(RoomType.FINISH)) {

			// Asks the player if he would like to enter anyways.
			System.out.println("You are trying to enter a trapdoor. Proceed? (yes/no)");
			if (getYesNoAnswer(parser.getCommand())) {
				currentRoom = nextRoom;
				System.out.println("Good Luck!");
				System.out.println(currentRoom.getLongDescription());
				return "Success";
			} else {
				System.out.println("Allright, have a nice day.");
			}
			// Default
		} else {
			// interrupt preditorThread when leaving a room
			predatorThread.interrupt();
			currentRoom = nextRoom;
			System.out.println(currentRoom.getLongDescription());

			// restarting the preditorThread
			predatorThread = new Thread(predator, "predator");
			predatorThread.start();
			return "Success";
		}
		
	    //Message the player if he is ready to leave the island()
	    if(currentRoom instanceof Beach && player.getInventory().containsItem(new Boat())) {
	    	System.out.println("You are now ready to leave the island.");
	    	map.activateFinish();
	    }
	    
	    return "fail";
	}

	/**
	 * --------------------------------
	 * only for playing in console mode
	 * --------------------------------
	 * 
	 * Takes an item from the rooms inventory and places it into the players
	 * inventory.
	 * 
	 * @deprecated
	 * @param command The command that contains the item to transfer.
	 */
	private void takeItem(Command command) {
		if (!command.hasSecondWord()) {
			System.out.println("Take what?");
		} else {
			noticeSpecialItem(command, "add");
			transferItem(currentRoom.getInventory(), player.getInventory(), command.getSecondWord());
		}
		
    	//Check if the player is now able to build the boat
    	if(boatBuilder.checkIfBoatCanBeBuilt(player.getInventory())){
    		System.out.println("You are now ready to build a Boat!\nUse command buildBoat to build it now.");
    	}
	}
	
	/**
	 * Takes an item from the rooms inventory and places it into the players
	 * inventory.
	 * 
	 *
	 * @param item
	 */
	public void takeItemSprite(ItemSprite item) {
		currentRoom.removeItem(item);
		player.getInventory().addItem(item.getItem());
		
    	//Check if the player is now able to build the boat
    	if(boatBuilder.checkIfBoatCanBeBuilt(player.getInventory())){
    		System.out.println("You are now ready to build a Boat!\nUse command buildBoat to build it now.");
    	}
	}

	/**
	 * --------------------------------
	 * only for playing in console mode
	 * --------------------------------
	 * 
	 * Takes an item from the players inventory and places it into the rooms
	 * inventory.
	 * 
	 * @deprecated
	 * @param command The command that contains the item to transfer.
	 */
	private void dropItem(Command command) {
		if (!command.hasSecondWord()) {
			System.out.println("Drop what?");
		} else {
			transferItem(player.getInventory(), currentRoom.getInventory(), command.getSecondWord());
			//Check if the item had a special effect
			noticeSpecialItem(command, "drop");
		}
	}

	/**
	 * Transfers an item between two inventories.
	 * 
	 * @param origin    The inventory that currently contains the item.
	 * @param recipient The inventory in which to move the item.
	 * @param item      The items name.
	 */
	private void transferItem(Inventory origin, Inventory recipient, String item) {
		Item transfer = origin.getItemByName(item);
		if (transfer != null) {
			recipient.addItem(transfer);
			origin.removeItem(transfer);
		
		} else {
			System.out.println("The origin inventory doesn't contain the item youre trying to exchange.");
		}
	}

	/**
	 * --------------------------------
	 * only for playing in console mode
	 * --------------------------------
	 * 
	 * Checks if the item has a special effect. If thats the case, the effect is implemented.  
	 * 
	 * @deprecated
	 * @param command The command that contains the item.
	 * @param action	Determines the impact of an effect
	 */
	public void noticeSpecialItem(Command command, String action) {
		
		//The player is adding an item.
		if (action.contains("add")) {			
			
			switch (command.getSecondWord()) {
			case "Stick":
				if (!player.getInventory().containsItem("Stick")
						&& !player.getInventory().containsItem("Sword")) {
					player.setDamage(new Stick().getDamage()); 
					System.out.println("your damage now: " + player.getDamage());
				}
				break;
			case "Sword":
				player.setDamage(new Sword().getDamage()); 							
				System.out.println("your damage now: " + player.getDamage());
				break;
			default:
			}
			
		} else if (action.contains("drop")) {
			switch (command.getSecondWord()) {
			
			case "Stick":
				//check if there are any other weapons in the inventory
				if (!player.getInventory().containsItem("Stick")
						&& !player.getInventory().containsItem("Sword")) {
					player.setDamage(0); 											
					System.out.println("your damage now: " + player.getDamage());
				}
				break;
				
			case "Sword":
				//check if there are any other weapons in the inventory
				if (!player.getInventory().containsItem("Stick")
						&& !player.getInventory().containsItem("Sword")) {
					player.setDamage(0); 
					System.out.println("your damage now: " + player.getDamage());

				}else if (player.getInventory().containsItem("Stick")) {
					player.setDamage(new Stick().getDamage());    										
					System.out.println("your damage now: " + player.getDamage());
				}
				break;
			default:
			}
		}
	}

	/**
	 * --------------------------------
	 * only for playing in console mode
	 * --------------------------------
	 * 
	 * "Quit" was entered. Check the rest of the command to see whether we really
	 * quit the game.
	 * 
	 * @deprecated
	 * @return true, if this command quits the game, false otherwise.
	 */
	public boolean quit(Command command) {
		if (command.hasSecondWord()) {
			System.out.println("Quit what?");
			return false;
		} else {
			return true; // signal that we want to quit
		}
	}

	/**
	 * Teleports the player into a randomly chosen room on the map.
	 */
	public void teleport() {
		currentRoom = map.teleport();
		lookAround();
	}

	/**
	 * --------------------------------
	 * only for playing in console mode
	 * --------------------------------
	 * 
	 * Prints all available exits to the console.
	 * 
	 * @deprecated
	 */
	private void lookAround() {
		System.out.println(currentRoom.getLongDescription());
		System.out.println("Current Health: " + player.getHealth());
		System.out.println("Current Hunger: " + player.getHunger());
	}

	/**
	 * Set this variable true to end the game
	 * @param finished
	 * @return finished
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	/**
	 * @return player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @return currentRoom 
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	/**
	 * @param The next room.
	 */
	public void setCurrentRoom(Room nextRoom) {
		this.currentRoom = nextRoom;
	}

	/**
	 * @return Seconds since the game started.
	 */
	public long getGameTime() {
		return timer.getTimePassedSeconds();
	}
	
	/**
	 * 
	 * @return Seconds left until the time runs out.
	 */
	public long getTimeLeft() {
		return timeLimit-timer.getTimePassedSeconds();
	}
	
	/**
	 * @return The games EnemyDamageRate.
	 */
	public float getEnemyDamageRate() {
		return enemyDamageRate;
	}
	
	/**
	 * Call after the player died.
	 */
	public void setDead() {
		dead = true;
	}
	
	/**
	 * @return The games time limit.
	 */
	public long getTimeLimit() {
		return timeLimit;
	}
	
	/**
	 * Let's the player eat the first occurance of food in his inventory.
	 * 
	 * @return True if successful, false if inventory doesn't contain food.
	 */
	public boolean eat() {
		for(Item i : player.getInventory().getFullInventory()) {
			if(i instanceof Food) {
				player.eat(((Food) i).getNutrition());
				player.getInventory().removeItem(i);
				return true;
			}
		}
		return false;
	}
	
	/**
	 * @return A reference to the games boatBuilder.
	 */
	public BoatBuilding getBoatBuilder() {
		return boatBuilder;
	}
	
	/**
	 * @return A reference to the games map.
	 */
	public Map getMap() {
		return map;
	}
	
	/**
	 * @return Whether the game is finished.
	 */
	public boolean getFinished() {
		return finished;
	}
	
	/**
	 * @param d True if the player died.
	 */
	public void setDead(boolean d) {
		dead = d;
	}
	
	/**
	 * @return If the player is dead.
	 */
	public boolean getDead() {
		return dead;
	}
}
