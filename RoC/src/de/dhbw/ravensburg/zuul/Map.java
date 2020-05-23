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
	private Room currentRoom;
	private ArrayList<Room> map; //List that only holds references to the rooms in which the player can be teleported randomly.

	Room westBeach, eastBeach, northBeach, southBeach;
	Room westForest, eastForest, northForest, southForest;
	Room redWoodTree, deepForest;
	Room ruinWestEntrance, ruinEastEntrance, ruinNorthEntrance, ruinSouthEntrance;
	Room ruinStairCase0, ruinStairCase1;
	Room ruinWatchTower, ruinLibrary, ruinPraying, ruinMage, ruinDungeon, ruinLaboratory;
	
	Room finalRoom;
	
	public Map() {
		map = new ArrayList<>();
		
		//Initialize: Beaches
		westBeach = new Beach("on the Beach", new WaterPig(), RoomType.BEACH_WEST, new Stick(), new Sword(), new Apple(), new Apple(), new Banana());
		map.add(westBeach);
		eastBeach = new Beach("on the Beach", null, RoomType.BEACH_EAST);
		map.add(eastBeach);
		northBeach = new Beach("on the Beach", null, RoomType.BEACH_NORTH);
		map.add(northBeach);
		southBeach = new Beach("on the Beach", null, RoomType.BEACH_SOUTH);
		map.add(southBeach);
		
		//Initialize: Forest
		westForest = new Forest("in the Forest", new Native(), RoomType.FOREST);
		map.add(westForest);
		eastForest = new Forest("in the Forest", null, RoomType.FOREST);
		map.add(eastForest);
		northForest = new Forest("in the Forest", null, RoomType.FOREST);
		map.add(northForest);
		southForest = new Forest("in the Forest", null, RoomType.FOREST);
		map.add(southForest);
		
		redWoodTree = new Forest("at the large tree", null, RoomType.REDWOOD, new MagicMushroom());
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
		
		ruinWatchTower = new Ruin("on the top of the Watchtower", null, RoomType.RUIN_TOP, new RoomKey("Key to the Library"));
		map.add(ruinWatchTower);
		ruinLibrary = new Ruin("in the ruins: Aincient Library", null);
		ruinLibrary.lockRoom(new RoomKey("Key to the Library"));
		map.add(ruinLibrary);
		ruinPraying = new Ruin("in the ruins: Holy Artefact", null);
		map.add(ruinPraying);
		ruinMage = new Ruin("in the ruins: Mage", new Mage());
		map.add(ruinMage);
		ruinDungeon = new Ruin("in the ruins: Dungeon", null);
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
	 * @return The room in which the player is currently residing.
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}
	
	/**
     * "Teleports" the player to a random room that isn't the current room.
     * 
     * To be called when interacting with the mage.
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
    	northBeach.setExit("east", finalRoom);
    	southBeach.setExit("south", finalRoom);
    }
}
