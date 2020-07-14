package de.dhbw.ravensburg.zuul.creature;

import de.dhbw.ravensburg.zuul.Parser;
import de.dhbw.ravensburg.zuul.Inventory;
import de.dhbw.ravensburg.zuul.Player;
import de.dhbw.ravensburg.zuul.item.*;
import de.dhbw.ravensburg.zuul.ui.CreatureSprite;



/**
 * Class Creature - a creature in an adventure game.

 * A "Creature" can be an "Animal" or an "Human". Creatures have different abilities. 
 * They can help or attack the player. 
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
	
	private CreatureSprite sprite;
	


	/**
     * Create a "Creature".
     * Creature can be a human or an animal. 
     * This constructor is used for "Human" and "Animal".   
     */
	public Creature() {  
			
	}
	/**
	 * * Defines the Dialog between the player and the Creature
	 * @param inventory The players inventory.
	 * @param parser	The parser to check yes and no answers. 
	 */
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
	 * Returns if the creature is innocent.
	 * @return innocent If the creature is innocent.
	 */
	public boolean getPeaceful() {
		return peaceful;
	}

	/** 
	 * Defines if the creature is innocent
	 * @param innocent If the creature is innocent.
	 */
	public void setPeaceful(boolean peaceful) {
		this.peaceful = peaceful;
	}
	
	
	/**
	 * Defines which item the creature will drop when the creature dies. 
	 * @param drop  The item the creature drop when it dies.
	 */
	public void setDropItem(Item drop ) {

		this.drop = drop;			
	}
	
	/** 
	 * Returns the item the creature drops.
	 * @return drop The item the creature can drop when it dies. 
	 */
	public Item getDropItem() {
		return drop;
		
	}
	
	/** 
	 * Returns the damage the creature can cause.
	 * @return damage The damage the creature causes the player. 
	 */
	public int getDamage() {
		return damage;
	}

	/** 
	 * Defines the damage the creature can cause.
	 * @param damage The damage the creature causes the player.
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	

	
	/** 
	 * Defines how much lifepoints the creature has.
	 * @param lifepoints How much lifepoints the creature has.
	 */
	public void setLifepoints(int lifepoints) {
		this.lifepoints = lifepoints; 	
	}
	
	/** 
	 * Returns the number of lifepoints from the creature.
	 * @return lifepoints the number of lifepoints from the creature.
	 */
	public int getLifepoints() {
	return lifepoints;
	}

	
	/** 
	 * Returns the name of the creature.
	 * @return name the name of the creature.
	 */
	public String getName() {
		return name;
	}


	/** 
	 * Defines the name of the creature.
	 * @param name The name of the creature.
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
	/**
	 * Returns if the creature is dead.
	 * @return isDead the status if the creature is dead or alive.
	 */
	public boolean isDead() {
		return isDead;
	}
	
	/** 
	 * @return drop Drops the Item which is stored in the creatures inventory.
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
	 * The creature can attack the player with this method and cause different amount of damage. 
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
	public CreatureSprite getSprite() {
		return sprite;
	}
	public void setSprite(CreatureSprite sprite) {
		this.sprite = sprite;
	}



}
