
package de.dhbw.ravensburg.zuul.creature;


/**
 * Class WaterPig - a water-pig in an adventure game.

 * A "WaterPig" is an animal in the game. It does not attack the player but when it dies it drops an Item. 
 * A dead water-pig will drop "Meat" as an Item.  
 * "WaterPig" is a subclass from "Animal"
 * 
 * @author  Moritz Link
 * @version 06.05.2020
 */
public class WaterPig extends Animal{

	
	/**
	 * Create a water-pig.
	 * It calls the constructor from the superclass "Animal".
	 */
	public WaterPig() {
		super();
		setLifepoints(30);
		setName("Waterpig");
		setPeaceful(true);
		setDamage(0);
		
		
	}

}


