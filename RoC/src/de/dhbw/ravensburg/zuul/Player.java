package de.dhbw.ravensburg.zuul;

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
	private Inventory inventory;
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
		inventory = new Inventory(inventorySize);
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
	 * @return A reference to the players inventory.
	 */
	public Inventory getInventory() {
		return inventory;
	}
}
