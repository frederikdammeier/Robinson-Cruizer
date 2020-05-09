package de.dhbw.ravensburg.zuul.creature;

/**
 * Class Human - an human in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Human" can be a mage or an native. They have different abilities
 * and can help or attack you. 
 * "Human" is a subclass from "Creature" and uses one constructor of "Creature". 
 * "Human" is the superclass from "Mage" and "Native".
 * 
 * @author  Moritz Link
 * @version 04.05.2020
 */
public class Human extends Creature{
	
	
	/**
     * Create a human  with with number of lifepoints. 
     * It calls the constructor from the superclass "Creature".
     * This constructor is used for "Native" and "Mage". 
     * @param lifepoints  The number of lifepoints the Human has. 
     */	
	public Human(int lifepoints) {
		super( lifepoints);	
		
	}

	
	/**
     * Defines what the human can tell you and if he is a friend or an enemy. 
     * @return String which depends on the value of innocent. 
     */
	public void talk() {
		if(getPeaceful() == true) {
			System.out.println("Hello nice to meet you my friend. My name is " + getName());
			System.out.println("I can help you if you want.");
		}
		else if(getPeaceful() == false) {
			System.out.println(getName() + " I want to kill you." );
		
		}
		
		
	}

}
