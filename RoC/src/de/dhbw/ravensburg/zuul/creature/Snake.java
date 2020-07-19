package de.dhbw.ravensburg.zuul.creature;


/**
 * Class Snake - a snake in an adventure game.
 * <p>
 * A "Snake" is an animal in the game and has the ability to attack the player and drop an Item.  
 * A dead snake will drop "Meat" as an Item.  
 * "Snake" is a subclass from "Animal"
 * 
 * @author  Moritz Link
 * @version 06.05.2020
 */
public class Snake extends Animal {

	
	/**
	 * Creates a snake.
	 * It calls the constructor from the superclass "Animal". 
	 */
	public Snake() {
		super();
		setLifepoints(30);
		setName("Snake");
		setPeaceful(false);
		setDamage(15);
		
	}
	

}
