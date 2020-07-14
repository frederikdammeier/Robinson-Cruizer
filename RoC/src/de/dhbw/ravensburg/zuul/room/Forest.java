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
		super(description, creature, RoomType.FOREST_HORIZONTAL, specialItems);
		
		if(creature == null) {
			setCreatureProbabilities();
			spawnCreature();
		}
		setItemProbabilites();
		populateInventory();
		generateItemSprites();
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
		creatureSpawnProbability.put("Ape", 30);			//Sets the probability to spawn an Ape in a new object Forest to 30%.
		creatureSpawnProbability.put("Snake", 20);
	}
	
	/**
	 * Define spawn probabilities here (in %).
	 */
	private void setItemProbabilites() {
		itemSpawnProbability = new HashMap<>();
		itemSpawnProbability.put("Banana", 20);				//A Banana will spawn in 20% of all objects Forests.
		itemSpawnProbability.put("Stick", 15);
		itemSpawnProbability.put("Timber", 15);
	}	
}
