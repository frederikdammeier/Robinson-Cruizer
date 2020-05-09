package de.dhbw.ravensburg.zuul.creature;
import de.dhbw.ravensburg.zuul.item.*;
/**
 * Class Animal - an animal in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Animal" can be a monkey, ape,snake or water pig. They have the ability. 
 * Some attack the player and cause damage. A dead animal will drop an Item.  
 * "Animal" is a subclass from "Creature" and uses the constructor of "Creature". 
 * 
 * @author  Moritz Link
 * @version 04.05.2020
 */
public class Animal extends Creature {
	
	/**
	 * Create an animal the number of lifepoints the animal has.
	 * It calls the constructor from the superclass "Creature".
	 * @param lifepoints  The number of lifepoints the animal has. 
	 */	
	public Animal(int lifepoints) {
		super(lifepoints);
		setDropItem(new Food("Meat", 3));
		
	}
	
	
//	@Override
//	public void setDropItem(Item drop) {
//		String name = "Meat";
//		float weight = 3;
//		drop = new Food(name, weight);
//	}
	
	
	 
}
