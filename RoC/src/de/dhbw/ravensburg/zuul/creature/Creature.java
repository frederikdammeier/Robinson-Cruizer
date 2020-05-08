package de.dhbw.ravensburg.zuul.creature;


import de.dhbw.ravensburg.zuul.item.*;

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
 * @version 04.05.2020
 */
public class Creature {
	

	private boolean innocent;
	private int damage;
	private int livepoints;
	private Item drop;
	private String name; 
	
	
	/**
     * Create a "Creature" with the value livepoints.
     * This constructor is used for "Human" and "Animal".   
     * @param livepoints  The number of livepoints the Creature has.
     */
	public Creature(int livepoints) { // livepoints? 
		
		this.livepoints = livepoints;	
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
	 * Defines which Item the Creature will drop when the Creature dies. 
	 * @param drop  The Item the Creature drop when it dies.
	 */
	public void setDropItem(Item drop ) {

//		drop = new Item("Test", 8); // Bei aufruf immer null
		this.drop = drop;			// Bei Aufruf immer link
	}
	
	/** 
	 * Return the Item the Creature drops.
	 * @return drop The Item the Creature can drop when it dies. 
	 */
	public Item getDropItem() {
		return drop;
		
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
		this.livepoints = livepoints; 	
	}
	
	/** 
	 * Return the number of livepoints from the Creature.
	 * @return livepoints the number of livepoints from the Creature.
	 */
	public int getLivepoints() {
	return livepoints;
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
	 * @param name The name of the Creature.
	 */
	public void setName(String name) {
		this.name = name;
	}



	/** 
	 *Return if the Creature is alive or not. 
	 *When their are no livepoints anymore. The Creature is dead.
	 * @return alive Boolean if Creature is dead or not. 
 	 */
	// Diese Methode soll �berpr�fen, ob die Creatur noch lebt und Anwort als Boolean zur�ckgeben
	public boolean isDead() {
		boolean dead = false;
		if(livepoints == 0) {
			dead = true;
			return dead;
		}
		return dead;
	}
	
	/** 
	 * Drop the Item when Creature is dead.
	 * @return drop The Item the Creature drops when it dies. 
	 */
	// Platzhalter
	public void dropItem() { 
		
		if(isDead() == true) {
			 //Drop Item
			System.out.println(drop.getName() +  " Hier müsste stehen welches Objekt ausgegeben wurde");
		}
		else {
			System.out.println("nichts zum Ausgeben");
		}
	}
	
	
	/**
	 * The Creature can attack the player with this method and cause different amount of damage. 
	 * @return livepoints  The number of livepoints the player has left after the attack. 
	 */
	public int attack() { 
		
		if(getInnocent() == false) {
			System.out.println("Hier greifen die Creaturen an " + getName());
			System.out.println(livepoints);
		}
		else{
			System.out.println("Diese Creature kann nicht angreifen");
		}
		
		return livepoints;
	}
	


}
