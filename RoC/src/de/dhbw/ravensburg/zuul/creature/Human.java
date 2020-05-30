package de.dhbw.ravensburg.zuul.creature;
import de.dhbw.ravensburg.zuul.Inventory;
import de.dhbw.ravensburg.zuul.Parser;

/**
 * Class Human - an human in an adventure game.

 * A "Human" can be a mage, an native, a prisoner, Freitag or a hunter. They have different abilities
 * and can help or attack you. 
 * "Human" is a subclass from "Creature" and uses one constructor of "Creature". 
 * 
 * @author  Moritz Link
 * @version 23.05.2020
 */
public class Human extends Creature{
	
	
	/**
     * Creates a human. 
     * It calls the constructor from the superclass "Creature". 
     */	
	public Human() {
		super( );	
		
	}

	
	/* * Defines the Dialog between the player and the other Human.
	 * Defines what happens when the player wants to talk with the Human. 
	 * The effect depends on the different Human.
	 * @param inventory The players inventory.
	 * @param parser	The parser to check yes and no answers
	 */
	public void talk(Inventory inventory, Parser parser) {  }

}
