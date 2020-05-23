package de.dhbw.ravensburg.zuul;

import java.util.HashMap;
import de.dhbw.ravensburg.zuul.item.*;

/**
 * Class BoatBuilding provides methods and variables which help the player to build a boat
 * and therefore be able to leave the island and finish the game.
 * 
 * @author Frederik Dammeier
 * @version 17.05.2020
 */
public class BoatBuilding {
	private HashMap<Item, Integer> recipe; 	//The items required to build the boat. <Item, Amount>
	
	/**
	 * The constructor defines the items and quantities needed to build a boat.
	 */
	public BoatBuilding() {
		recipe = new HashMap<>();
		recipe.put(new Sail(), 1);
		recipe.put(new Rope(), 2);
		recipe.put(new Timber(), 4);
		recipe.put(new Resin(), 2);
	}
	
	/**
	 * Returns true if the g
	 * @param inventory
	 * @return
	 */
	public boolean checkIfBoatCanBeBuilt(Inventory inventory) {
		return inventory.containsItemList(recipe);
	}
	
	/**
	 * Checks whether the boat can be built. If true, the given inventory gets the required items removed
	 * and a Boat is added.
	 * 
	 * @param inventory
	 * @return
	 */
	public boolean buildBoat(Inventory inventory) {
		if(checkIfBoatCanBeBuilt(inventory)) {
			//remove the required items from the players inventory.
			int i, l;
			for(Item item : recipe.keySet()) {
				 l = recipe.get(item);
				 for(i = 0; i < l; i++) {
					 inventory.removeItem(item);
				 }
			}
			//add a boat to the players inventory.
			inventory.addItem(new Boat());
			return true;
			
			
		} else {
			System.out.println("You don't seem to have the required Items in your inventory.");
		}
		return false;
	}
	
	/**
	 * Returns the recipe for the boat as a HashMap<Item, Integer>
	 * 
	 * @return the recipe as a HashMap
	 */
	public HashMap<Item, Integer> getRecipe(){
		return recipe;
	}
	
	/**
	 * Returns the recipe for the boat as a String list.
	 * 
	 * @return the recipe as a String
	 */
	public String getRecipeAsString() {
		StringBuilder sb = new StringBuilder();
		
		if(recipe.size() > 0) {
			for(Item i : recipe.keySet()) {
				sb.append("\n" + i.getName() + ": " + recipe.get(i) + "x");
			}
		}
		return sb.toString();
	}
}
