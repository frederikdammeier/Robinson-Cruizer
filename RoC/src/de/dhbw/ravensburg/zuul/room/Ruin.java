package de.dhbw.ravensburg.zuul.room;

import de.dhbw.ravensburg.zuul.room.Room;
import de.dhbw.ravensburg.zuul.item.*;

import java.util.HashMap;

import de.dhbw.ravensburg.zuul.creature.*;

/**
 * Room inside the ruins of the islands.
 * 
 * @author Frederik Dammeier
 * @version 09.05.2020
 */
public class Ruin extends Room {
	
	/**
	 * Creates new room of type Ruin. RoomType: RUIN.
	 * 
	 * @param description The rooms description.
	 * @param creature Creature to spawn in this specific instance.
	 * @param specialItems Items to spawn in this specific instance.
	 */
	public Ruin(String description, Creature creature, Item... specialItems) {
		super(description, creature, RoomType.RUIN, specialItems);
		
		//Make sure the probabilities don't add up to over 100%
		if(creature == null) {
			creatureSpawnProbability = new HashMap<>();
			creatureSpawnProbability.put("Native", 20);
			creatureSpawnProbability.put("Snake", 20);
			
			spawnCreature();
		}
		setItemProbabilites();
		populateInventory();
	}
	
	/**
	 * Creates new room of type Ruin. RoomType: custom.
	 * 
	 * @param description The rooms description.
	 * @param creature Creature to spawn in this specific instance.
	 * @param type The rooms RoomType.
	 * @param specialItems Items to spawn in this specific instance.
	 */
	public Ruin(String description, Creature creature, RoomType type, Item... specialItems) {
		super(description, creature, type, specialItems);
		
		if(creature == null) {
			setCreatureProbabilities();
			spawnCreature();
		}
		setItemProbabilites();
		populateInventory();
	}
	/**
	 * Define spawn probabilities here (in %). Try not to exceed 100% in total. That would lead to unexpected results.
	 */
	private void setCreatureProbabilities() {
		creatureSpawnProbability = new HashMap<>();
		creatureSpawnProbability.put("Native", 20);			//Sets the probability to spawn a Native in a new object Ruin to 20%.
		creatureSpawnProbability.put("Snake", 20);
	}
	
	/**
	 * Define spawn probabilities here (in %). Try not to exceed 100% in total. That would lead to unexpected results.
	 */
	private void setItemProbabilites() {
		itemSpawnProbability = new HashMap<>();
		itemSpawnProbability.put("Mushroom", 40);				//A Mushroom will spawn in 40% of all objects Ruin.
		itemSpawnProbability.put("Rope", 10);
	}	
}
