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
 * @author  Moritz Link
 * @version 2020.05.02
 */
public class Creature {
	private String name;
	private boolean innocent;
	private int damage;
	private int livepoints;
//	private String description;
	
	private Item drop;
	
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
	public boolean isDead() {
		boolean alive = false;
		if(livepoints == 0) {
			alive = true;
			return alive;
		}
		return alive;
	}
	
	/** 
	 * Drop the Item when Creature is dead.
	 * @return drop The Item the Creature drops when it dies. 
	 */
	// Diese Methode soll, wenn die Creatur nicht mehr lebt ein Item zur�ckgeben. 
	public Item dropItem() { 
		if(isDead() == true) {
			return drop;
		}
		return null;
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
