package de.dhbw.ravensburg.zuul.creature;
import de.dhbw.ravensburg.zuul.item.*;

/**
 * Class Animal - an animal in an adventure game.
 *  
 *
 * A "Animal" can be a monkey, ape,snake or water pig. They have the ability. 
 * Some attack the player and cause damage. A dead animal will drop an Item.  
 * "Animal" is a subclass from "Creature" and uses the constructor of "Creature". 
 * 
 * @author  Moritz Link
 * @version 23.05.2020
 */
public class Animal extends Creature {
	
	/**
	 * Creates an animal in the game.
	 * Animals can be an ape, a snake or a water pig.  
	 * Animal calls the constructor from the superclass "Creature".
	 */	
	public Animal() {
		super();
		setDropItem(new Meat());
		
	}
	
	

	
	 
}
