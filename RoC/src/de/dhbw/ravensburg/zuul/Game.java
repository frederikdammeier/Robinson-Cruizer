package de.dhbw.ravensburg.zuul;

import de.dhbw.ravensburg.zuul.room.*;
import de.dhbw.ravensburg.zuul.creature.*;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom; 
    private Timer timer;
    
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
    	timer = new Timer();
        createRooms();
        parser = new Parser(); 
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
		Room westBeach, eastBeach, northBeach, southBeach;
		Room westForest, eastForest, northForest, southForest;
		Room redWoodTree, deepForest;
		Room ruinWestEntrance, ruinEastEntrance, ruinNorthEntrance, ruinSouthEntrance;
		Room ruinStairCase0, ruinStairCase1;
		Room ruinWatchTower, ruinLibrary, ruinPraying, ruinMage, ruinDungeon, ruinLaboratory;
		
		Room finalRoom;
		
		//Initialize: Beaches
		westBeach = new Beach("on the Beach", null, RoomType.BEACH_WEST);
		eastBeach = new Beach("on the Beach", null, RoomType.BEACH_EAST);
		northBeach = new Beach("on the Beach", null, RoomType.BEACH_NORTH);
		southBeach = new Beach("on the Beach", null, RoomType.BEACH_SOUTH);
		
		//Initialize: Forest
		westForest = new Forest("in the Forest", null, RoomType.FOREST);
		eastForest = new Forest("in the Forest", null, RoomType.FOREST);
		northForest = new Forest("in the Forest", null, RoomType.FOREST);
		southForest = new Forest("in the Forest", null, RoomType.FOREST);
		
		redWoodTree = new Forest("at the large tree", null, RoomType.REDWOOD);
		deepForest = new Forest("in the dark forest", null, RoomType.DEEP_FOREST);
		
		//Initialize: Ruin
		ruinWestEntrance = new Ruin("in the ruins: West Entrance", null);
		ruinEastEntrance = new Ruin("in the ruins: East Entrance", null);
		ruinNorthEntrance = new Ruin("in the ruins: North Entrance", null);
		ruinSouthEntrance = new Ruin("in the ruins: South Entrance", null);
		
		ruinStairCase0 = new Ruin("in the ruins: Staircase", null);
		ruinStairCase1 = new Ruin("in the ruins: Staircase", null);
		
		ruinWatchTower = new Ruin("on the top of the Watchtower", null, RoomType.RUIN_TOP);
		ruinLibrary = new Ruin("in the ruins: Aincient Library", null);
		ruinPraying = new Ruin("in the ruins: Holy Artefact", null);
		ruinMage = new Ruin("in the ruins: Mage", new Mage("Gandalf der Graue", true));
		ruinDungeon = new Ruin("in the ruins: Dungeon", null);
		ruinLaboratory = new Ruin("in the ruins: Abandoned Laboratory", null);
		
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
                
        boolean finished = false;
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
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
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
        // else command not recognised.
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around at the university.");
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
            System.out.println("There is no door!");
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
     * Prints all available exits to the console.
     */
    private void lookAround() {
		System.out.println(currentRoom.getLongDescription());
	}
}
