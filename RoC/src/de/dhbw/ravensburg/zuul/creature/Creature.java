package de.dhbw.ravensburg.zuul.creature;


import de.dhbw.ravensburg.zuul.item.Item;
/**
 * Class Creature - a creature in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Creature" can be an "Animal" or an "Human". Creatures have different abilities
 * and can help or attack you. 
 * "Creature" is the superclasse from "Animal" and "Human".
 * 
 * @author  Moritz Link - Philipp Schneider
 * @version 08.05.2020
 */
public class Creature {
	private String name;
	private boolean innocent;
	private int damage;
	private int livepoints;
	private boolean isDead;
	private boolean invincible;
//	private String description;
	
	private Item drop;
	
	/**
     * Create a "Creature" with different values: name, innocent, damage, drop.
     * This constructor is used for "Animals". 
     * @param name The name of the creature.
     * @param innocent If the creature is innocent. 
     * @param livepoints of the creature.
     * @param damage how string the creature can damage the player.
     * @param drop the Item the creature drops when it dies. 
     */
	public Creature(String name, boolean innocent, int damage, Item drop, int livepoints) {
		this.name = name;
		this.innocent = innocent;
		this.damage = damage;
		this.drop = drop;
		this.livepoints = livepoints;
		isDead = false;
		invincible = false;
	}
	/**
     * Create a "Creature" with different values: name, innocent, damage, drop.
     * This constructor is used for "Animals". 
     * @param name The name of the creature.
     * @param innocent If the creature is innocent. 
     * @param damage how string the creature can damage the player.
     * @param drop the Item the creature drops when it dies. 
     */
	public Creature(String name, boolean innocent, int damage, Item drop) {
		this.name = name;
		this.innocent = innocent;
		this.damage = damage;
		this.drop = drop;
		isDead = false;
		invincible = false;
	}
	
	
	/**
     * Create a "Creature" with different values: name, innocent,  drop.
     * This constructor is used for "Human". 
     * @param name The name of the creature.
     * @param innocent If the creature is innocent. 
     * @param drop the Item the creature drops when it dies. 
     */	
	public Creature(String name, boolean innocent, Item drop) {
		this.name = name;
		this.innocent = innocent;
		this.drop = drop;
		isDead = false;
		invincible = false;
	}
	
	
	/**
     * Create a "Creature" with different values: name, innocent.
     * This Constructor is used for "Human". 
     * @param name The name of the creature.
     * @param innocent If the Creature is innocent. 
     */
	public Creature(String name, boolean innocent) {
		this.name = name;
		this.innocent = innocent;
		isDead = false;
		invincible = false;
	}

	/** 
	 * Return the name of the Creature.
	 * @return name the name of the Creature.
	 */
	public String getName() {
		return name;
	}

	/** 
	 * Define the name of the Creature.
	 * @param name The name of teh Creature.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/** 
	 * Return if the Creature is innocent.
	 * @return innocent If the Creature is innocent.
	 */
	public boolean getInnocent() {
		return innocent;
	}

	/** 
	 * Define if the Creature is innocent
	 * @param innocent If the Creature is innocent.
	 */
	public void setInnocent(boolean innocent) {
		this.innocent = innocent;
	}

	/** 
	 * Return the damage the Creature can cause.
	 * @return damage The damage the Creature can cause. 
	 */
	public int getDamage() {
		return damage;
	}

	/** 
	 * Define the damage the Creature can cause.
	 * @param damage The damage the Creature can cause.* @return
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	/**
	 * Decreases the creatures livepoints by the amount of damage the player can transfer.
	 * If the livescore is negativ, it's set to 0. A messeage is printed about the health status of the creature.
	 * @param amount of damage that is transfered.
	 */
	public void takeDamage(int amount) {
		livepoints = livepoints - amount;
		if (livepoints<=0) {
			livepoints = 0;
			System.out.println(name + " has been defeated!");
			isDead=true; 											//in this case the Creature is dead
			return;		//leaving method to stop print a redundant message about the lifepoints.
			}
		System.out.println(name + " has " + livepoints + " livepoints left.");

	}
	
	/** 
	 * Define how much livepoints the Creature has.
	 * @param livepoints How much livepoints the Creature has.
	 */
	public void setLivepoints(int livepoints) {
		this.livepoints = 20; 	// feste Zahl f�r die Lebenspunkte von Creaturen 
							   // Anpassungen an Schwierigkeitsstufe oder Unterschied zwischen Tier und Mensch
	}
	
	/** 
	 * Return the number of livepoints from the Creature.
	 * @return livepoints the number of livepoints from the Creature.
	 */
	public int getLivepoints() {
	return livepoints;
	}

	
	/** 
	 *Return if the Creature is alive or not. 
	 *When their are no livepoints anymore. The Creature is dead.
	 * @return alive Boolean if Creature is dead or not. 
 	 */
	// Diese Methode soll �berpr�fen, ob die Creatur noch lebt und Anwort als Boolean zur�ckgeben
//	public boolean isDead() {
//		boolean alive = false;
//		if (livepoints == 0) {
//			alive = true;
//			return alive;
//		}
//		return alive;
//	}
	
	public boolean isDead() {
		return isDead;
	}
	
	/** 
	 * @return drop Drop the Item that is stored in the creatures inventory.
	 */
	public Item dropItem() { 
		System.out.println("The " + name + " dropped " + drop.getName());
		return drop;
	}


	public boolean isInvincible() {
		return invincible;
	}
	
//	public int attack() { // livepoints vom Spieler (int livepoints)
//		
//		if(getInnocent() == false) {
//			livepoints = getLivepoints()- getDamage(); // livepoints vom Spieler 
//			
//			System.out.println(livepoints);
//		}
//		
//		return livepoints;
//	}
	

//
//public String getDescription() {
//	
//
//	return description;
//}
//
//public void setDescription(String description) {
//	this.description = description;
//}
}
