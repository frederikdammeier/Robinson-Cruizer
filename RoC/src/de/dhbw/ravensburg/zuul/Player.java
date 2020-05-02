package de.dhbw.ravensburg.zuul;
import java.util.ArrayList;
import de.dhbw.ravensburg.zuul.item.Item;


/**
 * This class is part of the World of Zuul Application.
 * World of Zuul is a simple adventure game.
 * 
 * 
 * It holds variables and methods for the main protagonist of the game.
 * 
 * @author Frederik Dammeier
 * @version 1.0
 *
 */
public class Player {
	private String name;
	private int hunger;
	private int health;
	private ArrayList<Item> inventory;
	private float inventorySize;
	private int maxLife;
	
	/**
	 * Creates a new object of type Player
	 * 
	 * @param name The name of the player. Doesn't have a function yet.
	 * @param inventorySize The maximum weight the Player can carry. Gets determined by the games difficulty.
	 */
	public Player(String name, float inventorySize, int maxLife) {
		this.name = name;
		hunger = 100;
		health = this.maxLife = maxLife;
		inventory = new ArrayList<>();
	}
	
	
	/**
	 * Increases or diminishes the players hunger count. He has a max hunger of 100.
	 * 
	 * @param amount Positive number: player eats; Negative number: player gets increasingly hungry.
	 */
	public void eat(int amount) {
		hunger += amount;
		if(hunger < 0) hunger = 0;
		else if(hunger > 100) hunger = 100;
	}
	
	/**
	 * Gives damage to the player by decreasing his health count.
	 * 
	 * @param amount 
	 */
	public void takeDamage(int amount) {
		health -= amount;
		if(health <= 0) health = 0; //in this case, the player is dead.
	}
	
	/**
	 * Regenerates the players health by increasing his health count.
	 * 
	 * @param amount
	 */
	public void regenerate(int amount) {
		health += amount;
		if(health > maxLife) health = maxLife; //a player cannot have more than maxLife health.
	}
	
	/**
	 * Adds an item to the players inventory
	 * 
	 * @param item  The item that gets added.
	 * @return true if successful, false if the player already carries too much.
	 */
	public boolean takeItem(Item item) {
		if(getCurrentInventoryWeight() + item.getWeight() <= inventorySize) {
			inventory.add(item);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Removes an item from the players inventory.
	 * 
	 * @param item The item to remove.
	 * @return true if successful. false if the inventory didn't contain the item.
	 */
	public boolean dropItem(Item item) {
		return inventory.remove(item);
	}
	
	/**
	 * Calculates the current weight of all items in the players inventory.
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
	 * @return Name of the player.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return Amount of hunger points left
	 */
	public int getHunger() {
		return hunger;
	}
	
	/**
	 * @return Amount of health points left
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * @return The whole inventory as a ArrayList.
	 */
	public ArrayList<Item> getInventory() {
		return inventory;
	}
	
	/**
	 * @return The inventorys size (in units).
	 */
	public float getInventorySize() {
		return inventorySize;
	}
}
