package de.dhbw.ravensburg.zuul.creature;


/**
 * Class Ape - an ape in an adventure game.
 * 
 * A "Ape" is an animal in the game and has the ability to attack the player and drop an Item.  
 * A dead ape will drop "Meat" as an Item.  
 * "Ape" is a subclass from "Animal"
 * 
 * @author  Moritz Link
 * @version 23.05.2020
 */
public class Ape extends Animal {
	
	
	/**
	 * Create an ape.
	 * It calls the constructor from the superclass "Animal". 
	 */	
	public Ape() {
		super();
		setLifepoints(30);
		setName("Ape");
		setPeaceful(false);
		setDamage(10);
		
		
	}

}
