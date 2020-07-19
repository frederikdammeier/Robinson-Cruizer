package de.dhbw.ravensburg.zuul.creature;

import de.dhbw.ravensburg.zuul.Parser;
import de.dhbw.ravensburg.zuul.Inventory;
import de.dhbw.ravensburg.zuul.Player;
import de.dhbw.ravensburg.zuul.item.*;
import de.dhbw.ravensburg.zuul.ui.CreatureSprite;



/**
 * Class Creature - a creature in an adventure game.
 * 
 * <p>
 * A "Creature" can be an "Animal" or an "Human". Creatures have different abilities. 
 * They can help or attack the player. 
 * "Creature" is the superclasse from "Animal" and "Human".
 * 

 * @author  Moritz Link - Philipp Schneider
 * @version 23.05.2020
 */
public class Creature {

	/**Creatures are peaceful or not.*/
	private boolean peaceful;
	/**Damage transfered by this creature*/
	private int damage;
	/**This creatures lifepoints*/
	private int lifepoints;
	/**Item dropped by this creature*/
	private Item drop;
	/**This creatures name*/
	private String name;
	/**Creatures can be invincible*/
	private boolean invincible;
	/**Current health status of the creature*/
	private boolean isDead;
	/**This creatures sprite*/
	private CreatureSprite sprite;
	


	/**
     * Creates a "Creature".
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
	 * @return peaceful True if the creature is peaceful.
	 */
	public boolean getPeaceful() {
		return peaceful;
	}

	/** 
	 * Defines if the creature is innocent
	 * @param peaceful True if the creature is peaceful.
	 */
	public void setPeaceful(boolean peaceful) {
		this.peaceful = peaceful;
	}
	
	
	/**
	 * Defines which item this creature will drop when it dies. 
	 * @param drop  The item this creature drops when it dies.
	 */
	public void setDropItem(Item drop ) {

		this.drop = drop;			
	}
	
	/** 
	 * Returns the item this creature drops.
	 * @return drop The item this creature drops when it dies.  
	 */
	public Item getDropItem() {
		return drop;
		
	}
	
	/** 
	 * Returns the damage this creature can cause.
	 * @return damage The damage this creature causes the player. 
	 */
	public int getDamage() {
		return damage;
	}

	/** 
	 * Defines the damage this creature can cause.
	 * @param damage The damage this creature causes the player.
	 */
	public void setDamage(int damage) {
		this.damage = damage;
	}
	

	
	/** 
	 * Defines how much lifepoints this creature has.
	 * @param lifepoints How much lifepoints this creature has.
	 */
	public void setLifepoints(int lifepoints) {
		this.lifepoints = lifepoints; 	
	}
	
	/** 
	 * Returns the number of lifepoints from this creature.
	 * @return lifepoints the number of lifepoints this the creature.
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
	 * Returns if this creature is dead.
	 * @return isDead the status if this creature is dead or alive.
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
	
	/**
	 * @return sprite This creatures Sprite.
	 */
	public CreatureSprite getSprite() {
		return sprite;
	}
	
	/**
	 * @param sprite This creatures Sprite
	 */
	public void setSprite(CreatureSprite sprite) {
		this.sprite = sprite;
	}



}
