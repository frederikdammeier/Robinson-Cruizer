package de.dhbw.ravensburg.zuul.creature;

import de.dhbw.ravensburg.zuul.item.Item;
/**
 * Class Human - an human in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Human" can be a Magier or an Native. They have different abilities
 * and can help or attack you. 
 * "Human" is a subclass from "Creature" and uses two constructors of "Creature". 
 * "Human" is the superclass from "Magier" and "Native".
 * 
 * @author  Moritz Link
 * @version 2020.05.02
 */
public class Human extends Creature{
	
	
	/**
     * Create a human  with different values: name, innocent,  drop.It calls the constructor from the superclass "Creature".
     * This constructor is used for "Native". 
     * @param name The name of the Human.
     * @param innocent If the human is innocent. 
     * @param drop the Item the human drops when it dies. 
     */	
	public Human(String name, boolean innocent, Item drop) {
		super(name, innocent, drop);		
	}
	
	/**
     * Create a human with different values: name, innocent. It calls the constructor from the superclass "Creature".
     * This constructor is used for "Magier". 
     * @param name The name of the human.
     * @param innocent If the human is innocent. 
     */
	public Human(String name, boolean innocent) {
		super(name, innocent);
		
	}
	
	/**
     * Defines what the human can tell you and if he is a friend or an enemy. 
     *  
     */
	// Diese Methode soll kurz ausgeben, ob der Mensch ein Freund ist oder nicht. 
	public void talk() {
		if(getInnocent() == true) {
			System.out.println("Hello nice to meet you my friend. My name is " + getName());
			System.out.println("I can help you if you want.");
		}
		else if(getInnocent() == false) {
			System.out.println(getName() + " I want to kill you." );
		
		}
		
		
	}

}
