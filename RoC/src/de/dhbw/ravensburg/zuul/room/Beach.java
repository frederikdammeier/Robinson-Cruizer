package de.dhbw.ravensburg.zuul.room;

import de.dhbw.ravensburg.zuul.room.Room;

import de.dhbw.ravensburg.zuul.item.*;
import de.dhbw.ravensburg.zuul.creature.*;

/**
 * Part of the beach of the island.
 * 
 * @author Frederik Dammeier
 * @version 1.0
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
		
		populateRoomInventory();
	}
	
	/**
	 * This method will later pre-populate the room with items typically spawning there.
	 */
	private void populateRoomInventory() {
		//code for auto-generating Items here
	}	
}
