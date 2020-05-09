package de.dhbw.ravensburg.zuul.room;

import de.dhbw.ravensburg.zuul.room.Room;

import de.dhbw.ravensburg.zuul.item.*;

import java.util.HashMap;

import de.dhbw.ravensburg.zuul.creature.*;

/**
 * Part of the beach of the island.
 * 
 * @author Frederik Dammeier
 * @version 09.05.2020
 */
public class Beach extends Room {

	/**
	 * Creates new room of type Beach. RoomType: custom.
	 * 
	 * @param description The rooms description.
	 * @param creature Creature to spawn in this specific instance.
	 * @param type The rooms RoomType.
	 * @param specialItems Items to spawn in this specific instance.
	 */
	public Beach(String description, Creature creature, RoomType type, Item... specialItems) {
		super(description, creature, type, specialItems);
		
		//Make sure the probabilities don't add up to over 100%
		if(creature == null) {
			creatureSpawnProbability = new HashMap<>();
			creatureSpawnProbability.put("Waterpig", 20);
					
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
