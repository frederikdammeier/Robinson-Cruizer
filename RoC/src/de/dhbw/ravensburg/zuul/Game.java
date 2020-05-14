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
    private Map map;
    
    

	/**
     * Create the game and initialize its internal map.
     */
    public Game() 
    {
    	timer = new Timer();
        map = new Map();
        parser = new Parser();
        player = new Player("Players Name", 20f, 100); 
        currentRoom = map.getCurrentRoom();
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
     * Try to go in to one direction. If there is an exit, enter the new
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

        currentRoom = map.movePlayer(direction);
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
    
    private void teleport() {
    	currentRoom = map.teleport();
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
