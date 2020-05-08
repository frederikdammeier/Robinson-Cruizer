package de.dhbw.ravensburg.zuul.creature;


/**
 * Class WaterPig - a water-pig in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "WaterPig" is an animal in the game. It does not attack the player but when it dies it drops an Item. 
 * A dead water-pig will drop "Meat" as an Item.  
 * "WaterPig" is a subclass from "Animal"
 * 
 * @author  Moritz Link
 * @version 06.05.2020
 */
public class WaterPig extends Animal{

	
	/**
	 * Create a water-pig the number of livepoints the water-pig has.
	 * It calls the constructor from the superclass "Animal".
	 * @param livepoints  The number of livepoints the water-pig has. 
	 */
	public WaterPig(int livepoints) {
		super(livepoints);
		setName("Waterpig");
		setInnocent(true);
		setDamage(0);
		
		
	}

}
