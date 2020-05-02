package de.dhbw.ravensburg.zuul;
import java.util.ArrayList;
import java.util.HashMap;
import de.dhbw.ravensburg.zuul.item.Item;

/**
 * A class that holds an ArrayList<Item> and provides Methods to manage it as an inventory.
 * 
 * To be used in Player, Room and Creature.
 * 
 * @author Frederik Dammeier
 * @version 1.0
 *
 */
public class Inventory {
	private ArrayList<Item>	inventory;
	private float size;
	private boolean unlimited;
	
	/**
	 * A new inventory with unlimited capacity.
	 */
	public Inventory() {
		inventory = new ArrayList<>();
		unlimited = true;
	}
	
	/**
	 * A new inventory with limited capacity.
	 * 
	 * @param size
	 */
	public Inventory(float size) {
		inventory = new ArrayList<>();
		unlimited = false;
		this.size = size;
	}
	
	/**
	 * Checks whether the inventory contains the given item.
	 * 
	 * @param item
	 * @return
	 */
	public boolean containsItem(Item item) {
		return inventory.contains(item);
	}
	
	/**
	 * Calculates the current combined weight of all items in the players inventory.
	 * 
	 * @return Weight in units.
	 */
	public float getCurrentInventoryWeight() {
		float amount = 0.0f;
		
		for(int i = 0; i < inventory.size(); i++) {
			amount = inventory.get(i).getWeight();
		}
		
		return amount;
	}
	
	/**
	 * Removes an item from the inventory.
	 * 
	 * @param item The item to remove.
	 * @return true if successful. false if the inventory didn't contain the item.
	 */
	public boolean removeItem(Item item) {
		return inventory.remove(item);
	}
	
	/**
	 * Adds an item to the inventory.
	 * 
	 * @param item  The item that gets added.
	 * @return true if successful, false if the new item exceeds the inventories capacity.
	 */
	public boolean addItem(Item item) {
		if(unlimited) {
			inventory.add(item);
			return true;
		}else if(getCurrentInventoryWeight() + item.getWeight() <= size) {
			inventory.add(item);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Return the number of items in the inventory.
	 * 
	 * @return
	 */
	public int getNumberOfItemsInInventory() {
		return inventory.size();
	}
	
	/**
	 * Prints the contents of the inventory to the console. To be changed for the GuI later.
	 */
	public void printContents() {
		HashMap<Item, Integer> tmp = new HashMap<>(); //HashMap to map the number of a specific item in the inventory to the item
		Item currentItem;
		for(int i = 0; i < inventory.size(); i++) {
			currentItem = inventory.get(i);
			if(tmp.containsKey(currentItem)) {
				tmp.replace(currentItem, tmp.get(currentItem)+1);
			} else {
				tmp.put(currentItem, 1);
			}
		}
		if(tmp.size() > 0) {
			for(Item i : tmp.keySet()) {
				System.out.println(i.getName() + ": " + tmp.get(i) + "x");
			}
		}	
	}
}
