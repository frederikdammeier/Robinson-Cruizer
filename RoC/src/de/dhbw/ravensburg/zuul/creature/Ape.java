package de.dhbw.ravensburg.zuul.creature;


/**
 * Class Ape - an ape in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Ape" is an animal in the game and has the ability to attack the player and drop an Item.  
 * A dead ape will drop "Meat" as an Item.  
 * "Ape" is a subclass from "Animal"
 * 
 * @author  Moritz Link
 * @version 06.05.2020
 */
public class Ape extends Animal {
	
	
	/**
	 * Create an ape the number of lifepoints the ape has.
	 * It calls the constructor from the superclass "Animal".
	 * @param lifepoints  The number of lifepoints the ape has. 
	 */	
	public Ape(int lifepoints) {
		super(lifepoints);
		setName("Ape");
		setPeaceful(false);
		setDamage(10);
		
		
	}

}