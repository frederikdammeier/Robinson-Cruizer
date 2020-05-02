package de.dhbw.ravensburg.zuul.room;

import de.dhbw.ravensburg.zuul.room.EmptyRoom;
import de.dhbw.ravensburg.zuul.item.*;
import de.dhbw.ravensburg.zuul.Creature;

/**
 * Room inside the ruins of the islands.
 * 
 * @author Frederik Dammeier
 * @version 1.0
 */
public class Ruin extends EmptyRoom {
	
	/**
	 * 
	 * @param description
	 * @param creature Creature to spawn in this specific instance.
	 * @param specialItems Items to spawn in this specific instance.
	 */
	public Ruin(String description, Creature creature, Item... specialItems) {
		super(description);
		super.setCreature(creature);
		
		if(specialItems.length > 0) {
			for(Item i : specialItems) {
				super.addItemToRoom(i);
			}
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
