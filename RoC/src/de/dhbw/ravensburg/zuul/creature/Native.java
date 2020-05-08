package de.dhbw.ravensburg.zuul.creature;
import de.dhbw.ravensburg.zuul.item.*;


/**
 * Class Native - a native in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A native  is a human with the ability to attack the player and reduce his livepoints. 
 * "Native" is a subclass from "Human".
 * 
 * @author  Moritz Link
 * @version 04.05.2020
 */
public class Native extends Human{

	
	/**
	 * Create a native the number of livepoints the Native has.
	 * It calls the constructor from the superclass "Human".
	 * @param livepoints  The number of livepoints the Native has. 
	 */	
	public Native(int livepoints) {
		super(livepoints);
		setInnocent(false);
		setDamage(30);
		setDropItem(new Weapon("Sword", 30));
		setName("Native");
		
	}

	
	
	
	
}
