package de.dhbw.ravensburg.zuul.creature;

/**
 * Class Mage - a mage in an adventure game.
 * A mage is a human with the ability to teleport the player to a random room. 
 * "Mage" is a subclass from "Human".
 * 
 * @author  Moritz Link
 * @version 04.05.2020
 */
public class Mage extends Human{
	
	
	/**
	 * Create a mage the number of lifepoints the mage has.
	 * It calls the constructor from the superclass "Human".
	 * @param lifepoints  The number of lifepoints the mage has. 
	 */	
	public Mage(int lifepoints) {
		super(lifepoints);
		setName("Gandalf der Graue"); 
		setPeaceful(true);

		
	}

	/**
	 * Method should teleport the player to a random room when the player talks with the mage.
	 */
	public void teleport() {
		System.out.println("Soll hier Spieler in anderen Raum bringen");
	}
}



