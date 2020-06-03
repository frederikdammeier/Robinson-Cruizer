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
		
		if(creature == null) {
			setCreatureProbabilities();			
			spawnCreature();
		}
		setItemProbabilites();
		populateInventory();
		generateItemSprites();
	}
	
	/**
	 * Define spawn probabilities here (in %). Try not to exceed 100% in total. That would lead to unexpected results.
	 */
	private void setCreatureProbabilities() {
		creatureSpawnProbability = new HashMap<>();
		creatureSpawnProbability.put("Waterpig", 50);			//Sets the probability to spawn a WaterPig in a new object Beach to 50%.

	}
	
	/**
	 * Define spawn probabilities here (in %). 
	 */
	private void setItemProbabilites() {
		itemSpawnProbability = new HashMap<>();
		itemSpawnProbability.put("Coconut", 50);				//A Coconut will spawn in 50% of all objects Forests.
	}	
}
