package de.dhbw.ravensburg.zuul;

import de.dhbw.ravensburg.zuul.item.Item;
import de.dhbw.ravensburg.zuul.item.Weapon;
import de.dhbw.ravensburg.zuul.ui.Sprite;
import javafx.scene.image.Image;

/**
 * It holds variables and methods for the main protagonist of the game.
 * 
 * @author Frederik Dammeier - Philipp Schneider
 * @version 08.05.2020
 *
 */
public class Player {
	private String name;
	private int hunger;
	private int health;
	private Inventory inventory;
	private int maxLife;
	private int damage;
	private long timeOfLastAttack;
	private Sprite playerSprite;
	
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
		damage = 0;
		Image robinImage = new Image("Images/Misc/Robinson.PNG", 125, 125, true, true);
		playerSprite = new Sprite(400.0, 400.0, 50.0, 50.0, robinImage);
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
	 * @param amount that damage increases/decreases.
	 */
	public void setDamage(int amount) {
		damage =  amount;
	}
	
	/**
	 * Updates the players damage.
	 * @param item To be checked.
	 */
	public void updatePlayerDamage(Item item) {
		if(item instanceof Weapon) {
			damage = ((Weapon) item).getDamage();
		}
	}
	
	/**
	 * Gives damage to the player by decreasing his health count.
	 * 
	 * @param amount of damage that is transfered by a creature.
	 * @return If the player has been killed by the damage.
	 */
	public boolean takeDamage(int amount) {
		health = health - amount;
		if(health <= 0) {
			health = 0;
			return true;              //in this case, the player is dead.
		}
		return false;
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

	/**
	 * @return Current damage that is transfered by the player.
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param timeOfAttack Last time at which the player started to attack.
	 */
	public void setTimeOfLastAttack(long timeOfAttack) {
		timeOfLastAttack = timeOfAttack;
	}
	
	/**
	 * @return timeOfAttack Last time at which the player started to attack.
	 */
	public long getTimeOfLastAttack() {
		return timeOfLastAttack;
	}
	
	/**
	 * @return playerSprite
	 */
	public Sprite getPlayerSprite() {
		return playerSprite;
	}
	
}
