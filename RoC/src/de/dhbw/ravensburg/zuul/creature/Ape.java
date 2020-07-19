package de.dhbw.ravensburg.zuul.creature;


/**
 * Class Ape - an ape in an adventure game.
 * 
 * <p>
 * A "Ape" is an animal in the game and has the ability to attack the player and drop an Item.  
 * A dead ape will drop "Meat" as an Item.  
 * "Ape" is a subclass from "Animal"
 * 
 * @author  Moritz Link
 * @version 23.05.2020
 */
public class Ape extends Animal {
	
	
	/**
	 * Creates an ape.
	 * It calls the constructor from the superclass "Animal". 
	 */	
	public Ape() {
		super();
		setLifepoints(70);
		setName("Ape");
		setPeaceful(false);
		setDamage(20);
		
		
	}

}
