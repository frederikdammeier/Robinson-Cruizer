package de.dhbw.ravensburg.zuul.creature;


/**
 * Class Snake - a snake in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Snake" is an animal in the game and has the ability to attack the player and drop an Item.  
 * A dead snake will drop "Meat" as an Item.  
 * "Snake" is a subclass from "Animal"
 * 
 * @author  Moritz Link
 * @version 06.05.2020
 */
public class Snake extends Animal {

	
	/**
	 * Create a snake the number of lifepoints the snake has.
	 * It calls the constructor from the superclass "Animal".
	 * @param lifepoints  The number of lifepoints the snake has. 
	 */
	public Snake(int lifepoints) {
		super(lifepoints);
		setName("Snake");
		setPeaceful(false);
		setDamage(15);
		
	}
	

}
