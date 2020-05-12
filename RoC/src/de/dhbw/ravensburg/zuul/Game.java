package de.dhbw.ravensburg.zuul;

import de.dhbw.ravensburg.zuul.room.*;
import de.dhbw.ravensburg.zuul.creature.*;
import de.dhbw.ravensburg.zuul.item.*;
import java.util.ArrayList;


/**
 *  This class is the main class of the "RobinsonCruizer" application. 
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes - 
 * 			further developed by Frederick Dammeier - Philipp Schneider
 * @version 08.05.2020
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom; 
    private Timer timer;
    private Player player;
    private boolean finished;
    private ArrayList<Room> map;
    
    

	/**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
    	timer = new Timer();
        createRooms();
        parser = new Parser();
        player = new Player("Players Name", 20f, 100); 
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
    	map = new ArrayList<>();
    	
		Room westBeach, eastBeach, northBeach, southBeach;
		Room westForest, eastForest, northForest, southForest;
		Room redWoodTree, deepForest;
		Room ruinWestEntrance, ruinEastEntrance, ruinNorthEntrance, ruinSouthEntrance;
		Room ruinStairCase0, ruinStairCase1;
		Room ruinWatchTower, ruinLibrary, ruinPraying, ruinMage, ruinDungeon, ruinLaboratory;
		
		Room finalRoom;
		
		
		
		//Initialize: Beaches
		westBeach = new Beach("on the Beach", new WaterPig(50), RoomType.BEACH_WEST, new Apple(), new Apple(), new Banana());
		map.add(westBeach);
		eastBeach = new Beach("on the Beach", null, RoomType.BEACH_EAST);
		map.add(eastBeach);
		northBeach = new Beach("on the Beach", null, RoomType.BEACH_NORTH);
		map.add(northBeach);
		southBeach = new Beach("on the Beach", null, RoomType.BEACH_SOUTH);
		map.add(southBeach);
		
		//Initialize: Forest
		westForest = new Forest("in the Forest", null, RoomType.FOREST);
		map.add(westForest);
		eastForest = new Forest("in the Forest", null, RoomType.FOREST);
		map.add(eastForest);
		northForest = new Forest("in the Forest", null, RoomType.FOREST);
		map.add(northForest);
		southForest = new Forest("in the Forest", null, RoomType.FOREST);
		map.add(southForest);
		
		redWoodTree = new Forest("at the large tree", null, RoomType.REDWOOD);
		map.add(redWoodTree);
		deepForest = new Forest("in the dark forest", null, RoomType.DEEP_FOREST);
		map.add(deepForest);
		
		//Initialize: Ruin
		ruinWestEntrance = new Ruin("in the ruins: West Entrance", null);
		map.add(ruinWestEntrance);
		ruinEastEntrance = new Ruin("in the ruins: East Entrance", null);
		map.add(ruinEastEntrance);
		ruinNorthEntrance = new Ruin("in the ruins: North Entrance", null);
		map.add(ruinNorthEntrance);
		ruinSouthEntrance = new Ruin("in the ruins: South Entrance", null);
		map.add(ruinSouthEntrance);
		
		ruinStairCase0 = new Ruin("in the ruins: Staircase", null);
		map.add(ruinStairCase0);
		ruinStairCase1 = new Ruin("in the ruins: Staircase", null);
		map.add(ruinStairCase1);
		
		ruinWatchTower = new Ruin("on the top of the Watchtower", null, RoomType.RUIN_TOP);
		map.add(ruinWatchTower);
		ruinLibrary = new Ruin("in the ruins: Aincient Library", null);
		map.add(ruinLibrary);
		ruinPraying = new Ruin("in the ruins: Holy Artefact", null);
		map.add(ruinPraying);
		ruinMage = new Ruin("in the ruins: Mage", new Mage(60));
		map.add(ruinMage);
		ruinDungeon = new Ruin("in the ruins: Dungeon", null);
		map.add(ruinDungeon);
		ruinLaboratory = new Ruin("in the ruins: Abandoned Laboratory", null);
		map.add(ruinLaboratory);
		
		//Set connections: Axis West-East
		westBeach.setExit("east", westForest);
		westForest.setExit("west", westBeach);
		westForest.setExit("east", ruinWestEntrance);
		ruinWestEntrance.setExit("west", westForest);
		ruinWestEntrance.setExit("east", ruinStairCase0);
		ruinStairCase0.setExit("west", ruinWestEntrance);
		ruinStairCase0.setExit("east",  ruinEastEntrance);
		ruinEastEntrance.setExit("west",  ruinStairCase0);
		ruinEastEntrance.setExit("east", eastForest);
		eastForest.setExit("west", ruinEastEntrance);
		eastForest.setExit("east", eastBeach);
		eastBeach.setExit("west", eastForest);
		
		//Set connections: Axis North-South
		northBeach.setExit("south", northForest);
		northForest.setExit("north", northBeach);
		northForest.setExit("south", ruinNorthEntrance);
		ruinNorthEntrance.setExit("north", northForest);
		ruinNorthEntrance.setExit("south", ruinStairCase0);
		ruinStairCase0.setExit("north", ruinNorthEntrance);
		ruinStairCase0.setExit("south",  ruinSouthEntrance);
		ruinSouthEntrance.setExit("north",  ruinStairCase0);
		ruinSouthEntrance.setExit("south", southForest);
		southForest.setExit("north", ruinSouthEntrance);
		southForest.setExit("south", southBeach);
		southBeach.setExit("north", southForest);
		
		//Set connections Ruins level 0
		ruinLibrary.setExit("south",  ruinWestEntrance);
		ruinWestEntrance.setExit("north", ruinLibrary);
		ruinLibrary.setExit("east",  ruinNorthEntrance);
		ruinNorthEntrance.setExit("west",  ruinLibrary);
		
		ruinEastEntrance.setExit("down", ruinDungeon); //dungeon has currently no way to get out.
		
		ruinStairCase0.setExit("down", ruinLaboratory);
		ruinStairCase0.setExit("up", ruinStairCase1);
		
		//Set connections Ruins level -1
		ruinLaboratory.setExit("up",  ruinStairCase0);
		
		//Set connections Ruins level 1
		ruinStairCase1.setExit("down", ruinStairCase0);
		ruinStairCase1.setExit("up", ruinWatchTower);
		ruinStairCase1.setExit("west", ruinMage);
		ruinMage.setExit("east", ruinStairCase1);
		ruinStairCase1.setExit("south", ruinPraying);
		ruinPraying.setExit("north", ruinStairCase1);
		
		//Set connections Ruins level 2
		ruinWatchTower.setExit("down",  ruinStairCase1);
		
		//Set connections Outside
		northForest.setExit("east", redWoodTree);
		redWoodTree.setExit("west", northForest);
		northForest.setExit("west", deepForest);
		deepForest.setExit("east", northForest);
		
		currentRoom = westBeach; // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {    
    	
        printWelcome();
        
        //Add a new Timer to measure passed game Time.
        Thread timeThread = new Thread(timer, "timer");
        timeThread.start();
        
        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
            printTimePassed();
        }
        System.out.println("Thank you for playing.  Good bye!");
        timer.stopTimer();
    }
    
    /*
     * Print the Seconds that have passed since starting the game to the console.
     */
    private void printTimePassed() {
    	System.out.println(timer.getTimePassedSeconds() + " seconds have passed since starting the game.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the Robinson Cruizer Island Adventure!");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        lookAround();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if(commandWord.equals("look")) {
        	lookAround();
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("attack")) {
            playerAttack();
        } else if (commandWord.equals("teleport")) {
        	teleport();
        }
        // else command not recognised.
        return wantToQuit;
    }

    
    /**
     * Processes an attack request by the player.
     * Valid request cause damage to be transfered to a creature, therefore reducing its health.
     * Dead creatures are removed from the rooms inventory and might drop an item.
     */
	private void playerAttack() {

		// check if there is no creature or only an invincible one.
		if (currentRoom.getCreature() == null || currentRoom.getCreature().isInvincible()) {
			System.out.println("There are no creatures to attack.");
		}

		else {
			// set the time of this attack.
			player.setTimeOfLastAttack(timer.getTimePassedSeconds());

			// get the creature of this room.
			Creature creatureInRoom = currentRoom.getCreature();

			// decrease the creatures health by the amount of damage the player can
			// tranfered.
			creatureInRoom.takeDamage(player.getDamage());

			if (creatureInRoom.isDead()) {
				// Try to add the drop item of the creature into the players inventory.
				try {
					player.getInventory().addItem(creatureInRoom.dropItem());
				} catch (Exception e) {
					System.out.println("The " + creatureInRoom.getName() + " dropped nothing.");
				}
				
				//"deletes" the creature in the current room.
				currentRoom.setCreature(null);

				// else put the updated creature in the room.
			} else {
				currentRoom.setCreature(creatureInRoom);
			}
		}
	}

	/**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the island.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }

    /** 
     * Try to in to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no way to go!");
        }
        else {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    /**
     * "Teleports" the player to a random room that isn't the current room.
     * 
     * To be called when interacting with the mage.
     */
    private void teleport() {
    	int l = map.size();
    	int r;
    	
    	do {
    		r = (int) (Math.random()*l);
    	} while (currentRoom == map.get(r));
    	
    	currentRoom = map.get(r);
    	lookAround();
    }
    
    /**
     * Prints all available exits to the console.
     */
    private void lookAround() {
		System.out.println(currentRoom.getLongDescription());
	}
    
    public void setFinished(boolean finished) {
		this.finished = finished;
	}
}
