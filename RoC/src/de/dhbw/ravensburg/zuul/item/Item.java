package de.dhbw.ravensburg.zuul.item;

/**
 * Class Item - an item in the game.
 * 
 * An "Item" represents a individual unit that the player can use
 * for a specific purpose.
 * Items can be found in "Rooms" of the game.
 * All items can be taken into the "Player"s inventory.
 * 
 * @author Philipp Schneider
 * @version 17.05.2020
 */

public class Item {
	
	/** The name of an item*/
	private String name;
	/** The weight of an item*/
	private float weight;
	
	/**
	 * Create an item that is described with "name" and has a specific weight.
	 * The name and weight of an item can not be changed later anymore.
	 * 
	 * @param name		The item's name.
	 * @param weight	The item's weight.
	 */
		public Item(String name, float weight) {
		this.name = name;
		this.weight = weight;	
	}
	
	/**
	 * @return The name of an item as a string.
	 * (the one thats was definied in the constructor).
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return The weight of an item as a float.
	 */
	public float getWeight() {
		return weight;
	}
	
	/**
	 * To check if 2 items are internally identical.
	 * 
	 * @param Item the Item to compare to.
	 * @return 
	 */
	public boolean equals(Item item) {
		if(item != null) {
			return item.getWeight() == weight && item.getName().equals(name);
		} else {
			return false;
		}
	}		
}
