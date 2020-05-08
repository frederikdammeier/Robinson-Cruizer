package de.dhbw.ravensburg.zuul.creature;

/**
 * Class Magier - a magier in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A magier is a human with the ability to teleport the player to a random room. 
 * "Magier" is a subclass from "Human".
 * 
 * @author  Moritz Link
 * @version 2020.05.02
 */
public class Mage extends Human{

	
	/**
	 * Create a magier with values: name, innocent. It calls the constructor from the superclass "Human".
	 * @param name The name of the magier
	 * @param innocent If the magier is friendly or not
	 */	
	public Mage(String name, boolean innocent) {
		super(name, innocent);
		
	}

	/**
	 * Method should teleport the player to a random room when the player talks with the magier.
	 */
	// soll bei einem Aufruf den Spieler teleprotieren k�nnen
	public void teleport() {
		
	}
}
