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
 * @version 04.05.2020
 */
public class Magier extends Human{
	
	
	/**
	 * Create a magier the number of livepoints the magier has.
	 * It calls the constructor from the superclass "Human".
	 * @param livepoints  The number of livepoints the magier has. 
	 */	
	public Magier(int livepoints) {
		super(livepoints);
		setName("Gandalf der Graue"); 
		setInnocent(true);
		
	}

	/**
	 * Method should teleport the player to a random room when the player talks with the magier.
	 */
	// soll bei einem Aufruf den Spieler teleprotieren k�nnen
	public void teleport() {
		System.out.println("Soll hier Spieler in anderen Raum bringen");
	}
}

