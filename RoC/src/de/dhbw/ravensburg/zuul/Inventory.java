package de.dhbw.ravensburg.zuul;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;

import de.dhbw.ravensburg.zuul.item.Item;

/**
 * A class that holds an ArrayList<Item> and provides Methods to manage it as an inventory.
 * 
 * To be used in Player, Room and Creature.
 * 
 * @author Frederik Dammeier
 * @version 17.05.2020
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
	 * Checks whether the inventory contains the given item.
	 * 
	 * @param item 
	 * @return
	 */
	public boolean containsItem(String item) {
		boolean foundItem = false;
		Item tmp;
		
		ListIterator<Item> it = inventory.listIterator();
		
		while(!foundItem && it.hasNext()) {
			tmp = it.next();
			if(item.equals(tmp.getName())) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns an Item in the list by its name.
	 * 
	 * @param name The name to look for.
	 * @return null if Item not in list
	 */
	public Item getItemByName(String name) {
		boolean foundItem = false;
		Item tmp;
		
		ListIterator<Item> it = inventory.listIterator();
		
		while(!foundItem && it.hasNext()) {
			tmp = it.next();
			if(name.equals(tmp.getName())) {
				return tmp;
			}
		}
		System.out.println("Item not found!");
		return null;
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
			System.out.println("The item you're trying to add is too heavy.");
			return false;
		}
	}
	
	/**
	 * Adds multiple items to the inventory.
	 * 
	 * @param items The items that get added.
	 * @return true if successful, false if the new items exceed the inventories capacity.
	 */
	public boolean addMultipleItems(Item...items) {
		if(items.length > 0){
			if(unlimited) {
				for(Item i : items) {
					inventory.add(i);
				}
				return true;
			} else {
				float amount = 0.0f;
				for(int i = 0; i < items.length; i++) {
					amount = items[i].getWeight();
				}
				
				if(getCurrentInventoryWeight() + amount <= size) {
					for(Item i : items) {
						inventory.add(i);
					}
					return true;
				} else {
					return false;
				}
			}
		}
		return true;	
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
	 * Removes all items from the inventory.
	 */
	public void clearInventory() {
		inventory.clear();
	}
	
	/**
	 * Prints the contents of the inventory to the console. To be changed for the GuI later.
	 */
	public void printContents() {
		System.out.println(getContentsAsString());
	}
	
	/**
	 * Returns the the contents of the inventory as a String if form of a list.
	 * 
	 * @return The contents as a String.
	 */
	public String getContentsAsString() {
		HashMap<String, Integer> tmp = new HashMap<>(); //HashMap to map the number of a specific item in the inventory to the item
		String currentItem;
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < inventory.size(); i++) {
			currentItem = inventory.get(i).getName();
			if(tmp.containsKey(currentItem)) {
				tmp.put(currentItem, tmp.get(currentItem)+1);
			} else {
				tmp.put(currentItem, 1);
			}
		}
		if(tmp.size() > 0) {
			for(String i : tmp.keySet()) {
				sb.append("\n" + i + ": " + tmp.get(i) + "x");
			}
		}
		return sb.toString();
	}
	
	public void setUnlimited() {
		unlimited = true;
	}
	
	/**
	 * Checks whether the inventory contains a HashMap<Item, Amount> of Items.
	 * 
	 * @param list A HashMap<Item, Integer>
	 * @return true if all items are contained in the specified quantities
	 */
	public boolean containsItemList(HashMap<Item, Integer> list){
		ListIterator<Item> it = inventory.listIterator();
		HashMap<Item, Integer> tmp = new HashMap<>(list);
		Item currentItem;

		while(it.hasNext()) {
			currentItem = it.next();
			if(tmp.containsKey(currentItem)) {
				if(tmp.get(currentItem) > 1){
					tmp.put(currentItem, tmp.get(currentItem)-1);		
				} else {
					tmp.remove(currentItem);
				}
			}
		}
		return tmp.isEmpty();
	}
	
	public String getNamefromList(int i) {
		String name;
		name= inventory.get(i).getName();
		
		
		return name;
	}
} 

