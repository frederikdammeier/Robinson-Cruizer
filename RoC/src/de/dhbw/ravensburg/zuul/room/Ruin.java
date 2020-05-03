package de.dhbw.ravensburg.zuul.room;

import de.dhbw.ravensburg.zuul.room.Room;
import de.dhbw.ravensburg.zuul.item.*;
import de.dhbw.ravensburg.zuul.Creature;

/**
 * Room inside the ruins of the islands.
 * 
 * @author Frederik Dammeier
 * @version 1.0
 */
public class Ruin extends Room {
	
	/**
	 * 
	 * @param description
	 * @param creature Creature to spawn in this specific instance.
	 * @param specialItems Items to spawn in this specific instance.
	 */
	public Ruin(String description, Creature creature, Item... specialItems) {
		super(description);
		super.setCreature(creature);
		setType(RoomType.RUIN);
		
		setInventory();
		
		if(specialItems.length > 0) {
			for(Item i : specialItems) {
				super.getInventory().addItem(i);
			}
		}
		
		populateRoomInventory();
	}
	
	public Ruin(String description, Creature creature, RoomType type, Item... specialItems) {
		super(description);
		super.setCreature(creature);
		setType(type);
		
		setInventory();
		
		if(specialItems.length > 0) {
			for(Item i : specialItems) {
				super.getInventory().addItem(i);
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
