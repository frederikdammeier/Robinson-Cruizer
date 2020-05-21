package de.dhbw.ravensburg.zuul.creature;
import java.util.Scanner;

import de.dhbw.ravensburg.zuul.item.Item;

/**
 * Class Human - an human in an adventure game.

 * A "Human" can be a mage, an native, a prisoner, Freitag or a hunter. They have different abilities
 * and can help or attack you. 
 * "Human" is a subclass from "Creature" and uses one constructor of "Creature". 
 * 
 * @author  Moritz Link
 * @version 04.05.2020
 */
public class Human extends Creature{
	
	private Item trade;
	/**
     * Create a human. 
     * It calls the constructor from the superclass "Creature".
     * This constructor is used for "Native", "Freitag", "prisoner", "Hunter" and "Mage".  
     */	
	public Human() {
		super();	
		
	}
	
	/**
	 * Defines the Dialog between the player and the other Human.
	 * Defines what happens when the player wants to talk with the Human. 
	 * The effect depends on the different Human.
	 */
	public void talk() {
		// Scanner scanner = new Scanner(System.in);		
		if(getPeaceful() == true) {
			System.out.println("Hello nice to meet you my friend. My name is " + getName());
			System.out.println("I can help you if you want.");
		}
		else if(getPeaceful() == false) {
			System.out.println(getName() + " I want to kill you." );
		
		}	
	}

	/** 
	 * Return the Item the Human wants in exchange for information.
	 * @return drop The Item the Human wants in exchange for information. 
	 */
	public Item getTrade() {
		return trade;
	}


	/**
	 * Defines which Item the Human wants in exchange for information. 
	 * @param drop  The Item the CHuman wants in exchange for information.
	 */
	public void setTrade(Item trade) {
		this.trade = trade;
	}

	
}
