package de.dhbw.ravensburg.zuul.creature;

import de.dhbw.ravensburg.zuul.Parser;
import de.dhbw.ravensburg.zuul.Inventory;
import de.dhbw.ravensburg.zuul.Player;
import de.dhbw.ravensburg.zuul.item.*;



/**
 * Class Creature - a creature in an adventure game.

 * A "Creature" can be an "Animal" or an "Human". Creatures have different abilities
 * and can help or attack you. 
 * "Creature" is the superclasse from "Animal" and "Human".
 * 

 * @author  Moritz Link - Philipp Schneider
 * @version 23.05.2020
 */
public class Creature {


	private boolean peaceful;
	private int damage;
	private int lifepoints;
	private Item drop;

	private String name;
	private boolean invincible;
	private boolean isDead;
	


	/**
     * Create a "Creature".
     * This constructor is used for "Human" and "Animal".   
     */
	public Creature() {  
			
	}
	
	public void talk(Inventory inventory, Parser parser) {  }
	/**
	 * Decreases the creatures livepoints by the amount of damage the player can transfer.
	 * If the livescore is negativ, it's set to 0. A messeage is printed about the health status of the creature.
	 * @param amount of damage that is transfered.
	 */
	public void takeDamage(int amount) {
		lifepoints = lifepoints - amount;
		if (lifepoints<=0) {
			lifepoints = 0;
			System.out.println(name + " has been defeated!");
			isDead=true; 											//in this case the Creature is dead
			return;		//leaving method to stop print a redundant message about the lifepoints.
			}
		System.out.println(name + " has " + lifepoints + " livepoints left.");

	}
	
	/** 
	 * Return if the Creature is innocent.
	 * @return innocent If the Creature is innocent.
	 */
	public boolean getPeaceful() {
		return peaceful;
	}

	/** 
	 * Define if the Creature is innocent
	 * @param innocent If the Creature is innocent.
	 */
	public void setPeaceful(boolean peaceful) {
		this.peaceful = peaceful;
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
	 * Define how much lifepoints the Creature has.
	 * @param lifepoints How much lifepoints the Creature has.
	 */
	public void setLifepoints(int lifepoints) {
		this.lifepoints = lifepoints; 	
	}
	
	/** 
	 * Return the number of lifepoints from the Creature.
	 * @return lifepoints the number of lifepoints from the Creature.
	 */
	public int getLifepoints() {
	return lifepoints;
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
	 *When their are no lifepoints anymore. The Creature is dead.
	 * @return isDead Boolean if Creature is dead or not. 
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

	// Platzhalter
	public Item dropItem() { 
		System.out.println("The " + name + " dropped " + drop.getName());
		return drop; 
		
	}
	
	/**
	*@return invincible 
	*/
	public boolean isInvincible() {
		return invincible;
	}
	
	/**
	 * The Creature can attack the player with this method and cause different amount of damage. 
	 * @return lifepoints  The number of lifepoints the player has left after the attack. 
	 */
	public int attack() { 
		
		if(getPeaceful() == false) {
			System.out.println("Hier greifen die Creaturen an " + getName());
			System.out.println(lifepoints);
		}
		else{
			System.out.println("Diese Creature kann nicht angreifen");
		}
		
		return lifepoints;
	}



}
