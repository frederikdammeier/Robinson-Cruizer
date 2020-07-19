package de.dhbw.ravensburg.zuul;

import java.util.ArrayList;

import de.dhbw.ravensburg.zuul.creature.*;
import de.dhbw.ravensburg.zuul.item.*;
import de.dhbw.ravensburg.zuul.room.*;


/**
 * This class holds all the Rooms for the RoC application. 
 * It provides methods to move the player around.
 * 
 * @author Frederik Dammeier
 * 
 *@version 17.05.2020
 */
public class Map {
	/**Current room the player is in*/
	private Room currentRoom;
	/**List that only holds references to the rooms in which the player can be teleported randomly.*/
	private ArrayList<Room> map; 

	/** a beach*/
	Room westBeach, eastBeach, northBeach, southBeach;
	/** a forest*/
	Room westForest, eastForest, northForest, southForest, northWestForest, southWestForest, centralForest;
	/** a special forest*/
	Room redWoodTree, deepForest;
	/** outside the ruin*/
	Room ruinWestEntrance, ruinEastEntrance, ruinNorthEntrance, ruinSouthEntrance;
	/** inside the ruin*/
	Room ruinStairCase0, ruinStairCase1;
	/** inside the ruin*/
	Room ruinWatchTower, ruinLibrary, ruinPraying, ruinMage, ruinDungeon, ruinLaboratory, ruinEastWing, ruinWestWing, ruinNorthWing;
	/**special room*/
	Room finalRoom;
	
	/**
	 * Adds all rooms of the game to an ArrayList.
	 * Sets each rooms links to other rooms.
	 */
	public Map() {
		map = new ArrayList<>();
		
		//Initialize: Beaches
		westBeach = new Beach("on the Beach", new Freitag(), RoomType.BEACH_WEST, new Apple());
		map.add(westBeach);
		eastBeach = new Beach("on the Beach", null, RoomType.BEACH_EAST);
		map.add(eastBeach);
		northBeach = new Beach("on the Beach", null, RoomType.BEACH_NORTH);
		map.add(northBeach);
		southBeach = new Beach("on the Beach", null, RoomType.BEACH_SOUTH);
		map.add(southBeach);
		
		//Initialize: Forest
		westForest = new Forest("in the Forest", null, RoomType.FOREST_NORTHWESTSOUTH);
		map.add(westForest);
		eastForest = new Forest("in the Forest", null, RoomType.FOREST_HORIZONTAL);
		map.add(eastForest);
		northForest = new Forest("in the Forest", null, RoomType.FOREST_NORTH);
		map.add(northForest);
		southForest = new Forest("in the Forest", null, RoomType.FOREST_VERTICAL);
		map.add(southForest);
		northWestForest = new Forest("in the Forest", new Ape(), RoomType.FOREST_SOUTHWEST2);
		map.add(northWestForest);
		southWestForest = new Forest("in the Forest", null, RoomType.FOREST_SOUTHWEST);
		map.add(southWestForest);
		centralForest = new Forest("in the Forest", new Snake(), RoomType.FOREST_HORIZONTAL);
		map.add(centralForest);
		
		redWoodTree = new Forest("at the large tree", null, RoomType.REDWOOD, new MagicMushroom());
		map.add(redWoodTree);
//		deepForest = new Forest("in the dark forest", null, RoomType.DEEP_FOREST);
//		map.add(deepForest);
		
		//Initialize: Ruin
		ruinWestEntrance = new Ruin("in the ruins: West Entrance", null, RoomType.RUIN_ENTRANCE_WEST);
		map.add(ruinWestEntrance);
		ruinEastEntrance = new Ruin("in the ruins: East Entrance", null, RoomType.RUIN_ENTRANCE_EAST);
		map.add(ruinEastEntrance);
		ruinNorthEntrance = new Ruin("in the ruins: North Entrance", new Hunter(), RoomType.RUIN_ENTRANCE_NORTH);
		map.add(ruinNorthEntrance);
		ruinSouthEntrance = new Ruin("in the ruins: South Entrance", null, RoomType.RUIN_ENTRANCE_SOUTH);
		map.add(ruinSouthEntrance);
		
		ruinStairCase0 = new Ruin("in the ruins: Staircase", null, RoomType.RUIN);
		map.add(ruinStairCase0);
		ruinStairCase1 = new Ruin("in the ruins: Staircase", null, RoomType.RUIN_STAIRCASE_1);
		map.add(ruinStairCase1);
		
		ruinWatchTower = new Ruin("on the top of the Watchtower", null, RoomType.RUIN_TOP, new RoomKey("Key to the Library"));
		map.add(ruinWatchTower);
		ruinWestWing = new Ruin("in the ruins west wing", null, RoomType.RUIN);
		map.add(ruinWestWing);
		ruinEastWing = new Ruin("in the ruins west wing", null, RoomType.RUIN);
		map.add(ruinEastWing);
		ruinNorthWing = new Ruin("in the ruins north wing", null, RoomType.RUIN);
		map.add(ruinNorthWing);
		ruinLibrary = new Ruin("in the ruins: Aincient Library", null, RoomType.RUIN_LIBRARY);
		ruinLibrary.lockRoom(new RoomKey("Key to the Library"));
		map.add(ruinLibrary);
		ruinPraying = new Ruin("in the ruins: Holy Artefact", null, RoomType.RUIN);
		map.add(ruinPraying);
		ruinMage = new Ruin("in the ruins: Mage", new Mage(), RoomType.RUIN_MAGE);
		map.add(ruinMage);
		ruinDungeon = new Ruin("in the ruins: Dungeon", new Prisoner(), RoomType.RUIN_DUNGEON);
		ruinLaboratory = new Ruin("in the ruins: Abandoned Laboratory", null, RoomType.RUIN_LABORATORY, new Meat(), new Bread());
		map.add(ruinLaboratory);
		
		finalRoom = new Room("Finish", null, RoomType.FINISH);
		
		//Set connections: Axis West-East
		westBeach.setExit("east", westForest);
		westForest.setExit("west", westBeach);
		westForest.setExit("north", northWestForest);
		westForest.setExit("south", southWestForest);
		northWestForest.setExit("south", westForest);
		northWestForest.setExit("east", centralForest);
		southWestForest.setExit("north", westForest);
		centralForest.setExit("west", northWestForest);
		centralForest.setExit("east", ruinWestEntrance);
		ruinWestEntrance.setExit("west", centralForest);
		ruinWestEntrance.setExit("east", ruinWestWing);
		ruinStairCase0.setExit("west", ruinWestWing);
		ruinStairCase0.setExit("east",  ruinEastWing);
		ruinEastEntrance.setExit("west",  ruinEastWing);
		ruinEastEntrance.setExit("east", eastForest);
		eastForest.setExit("west", ruinEastEntrance);
		eastForest.setExit("east", eastBeach);
		eastBeach.setExit("west", eastForest);
		
		//Set connections: Axis North-South
		northBeach.setExit("south", northForest);
		northForest.setExit("north", northBeach);
		northForest.setExit("south", ruinNorthEntrance);
		ruinNorthEntrance.setExit("north", northForest);
		ruinNorthEntrance.setExit("south", ruinNorthWing);
		ruinStairCase0.setExit("north", ruinNorthWing);
		ruinStairCase0.setExit("south",  ruinSouthEntrance);
		ruinSouthEntrance.setExit("north",  ruinStairCase0);
		ruinSouthEntrance.setExit("south", southForest);
		southForest.setExit("north", ruinSouthEntrance);
		southForest.setExit("south", southBeach);
		southBeach.setExit("north", southForest);
		
		//Set connections Ruins level 0
		ruinLibrary.setExit("south",  ruinWestWing);
		ruinLibrary.setExit("east",  ruinNorthWing);
		ruinNorthEntrance.setExit("west",  ruinLibrary);
		ruinWestWing.setExit("west", ruinWestEntrance);
		ruinWestWing.setExit("north", ruinLibrary);
		ruinWestWing.setExit("east", ruinStairCase0);
		ruinNorthWing.setExit("north", ruinNorthEntrance);
		ruinNorthWing.setExit("west", ruinLibrary);
		ruinNorthWing.setExit("south", ruinStairCase0);
		ruinEastWing.setExit("west", ruinStairCase0);
		ruinEastWing.setExit("east", ruinEastEntrance);
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
		//northForest.setExit("west", deepForest);
//		deepForest.setExit("east", northForest);
		
		
		currentRoom = westBeach; // start game outside
    }
	
	/**
	 * @return The room in which the player is currently residing.
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	/**
     * "Teleports" the player to a random room that isn't the current room.
     * To be called when interacting with the mage.
     * @return currentRoom
     */
    public Room teleport() {
    	int l = map.size();
    	int r;
    	
    	do {
    		r = (int) (Math.random()*l);
    	} while (currentRoom == map.get(r));
    	
    	currentRoom = map.get(r);
    	return currentRoom;
    	
    }
    
    /**
     * Sets exits for the beaches so the player is able to leave with the boat
     */
    public void activateFinish() {
    	westBeach.setExit("west", finalRoom);
    	eastBeach.setExit("east", finalRoom);
    	northBeach.setExit("north", finalRoom);
    	southBeach.setExit("south", finalRoom);
    }
}
