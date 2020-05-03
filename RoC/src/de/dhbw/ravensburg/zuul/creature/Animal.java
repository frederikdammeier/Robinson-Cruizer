package de.dhbw.ravensburg.zuul.creature;

/**
 * Class Animal - an animal in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Animal" can be a monkey or different animals. They have the ability
 * to attack the player and cause damage. A dead animal will drop an Item.  
 * "Animal" is a subclass from "Creature" and uses one constructor of "Creature". 
 * 
 * @author  Moritz Link
 * @version 2020.05.02
 */
public class Animal extends Creature {
	
	/**
     * Create a "Animal" with different values: name, innocent, damage, drop.
     * This constructor calls a constructor from "Creature". 
     * @param name The name of the creature.
     * @param innocent If the creature is innocent. 
     * @param damage how string the creature can damage the player.
     * @param drop the Item the creature drops when it dies. 
     */
	public Animal(String name, boolean innocent, int damage, Item drop) {
		super(name, innocent, damage, drop);
		
	}
	
	


}
