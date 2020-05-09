package de.dhbw.ravensburg.zuul.room;

import de.dhbw.ravensburg.zuul.room.Room;
import de.dhbw.ravensburg.zuul.item.*;
import de.dhbw.ravensburg.zuul.creature.*;
import java.util.HashMap;

/**
 * Part of the forest on the island.
 * 
 * @author Frederik Dammeier
 * @version 09.05.2020
 */
public class Forest extends Room {

	/**
	 * Creates new room of type Forest. RoomType: FOREST.
	 * 
	 * @param description The rooms description.
	 * @param creature Creature to spawn in this specific instance.
	 * @param specialItems Items to spawn in this specific instance.
	 */
	public Forest(String description, Creature creature, Item... specialItems) {
		super(description, creature, RoomType.FOREST, specialItems);
		
		//make sure total probability doesn't exceed 100%
		if(creature == null) {
			creatureSpawnProbability = new HashMap<>();
			creatureSpawnProbability.put("Ape", 30);
			creatureSpawnProbability.put("Snake", 20);
			
			spawnCreature();
		}
		
		populateRoomInventory();
	}
	
	/**
	 * Creates new room of type Forest. RoomType: custom.
	 * 
	 * @param description The rooms description.
	 * @param creature Creature to spawn in this specific instance.
	 * @param type The rooms RoomType.
	 * @param specialItems Items to spawn in this specific instance.
	 */
	public Forest(String description, Creature creature, RoomType type, Item... specialItems) {
		super(description, creature, type, specialItems);
		
		//make sure total probability doesn't exceed 100%
		if(creature == null) {
			creatureSpawnProbability = new HashMap<>();
			creatureSpawnProbability.put("Ape", 30);
			creatureSpawnProbability.put("Snake", 20);
					
			spawnCreature();
		}
		
		populateRoomInventory();
	}
	
	/**
	 * This method will later pre-populate the room with items typically spawning there.
	 */
	private void populateRoomInventory() {
		//code for auto-generating Items here
	}	
}
