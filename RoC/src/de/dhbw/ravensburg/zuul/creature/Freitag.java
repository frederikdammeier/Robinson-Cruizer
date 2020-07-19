package de.dhbw.ravensburg.zuul.creature;

import de.dhbw.ravensburg.zuul.Inventory;
import de.dhbw.ravensburg.zuul.Parser;

/**
 * Class Freitag
 * 
 * <p>
 * Freitag is a Human and appears at the beginning of the game. He gives the player the first informations.
 * He tells the player where he is and what he should do. 
 * "Fretiag" is a subclass from "Human".
 * 
 * @author  Moritz Link
 * @version 23.05.2020
 */
public class Freitag extends Human{

	/**
	 * Creates a "Freitag" and set the abilities fFreitag has.
	 * It calls the constructor from the superclass "Human".
	 */	
	public Freitag() {
		super();
//		setInvincible(true);
		setLifepoints(50);
		setPeaceful(true);		
		setDropItem(null);
		setDamage(0);
		setName("Freitag");

	}

	/**
	 * Defines the Dialog between the player and Freitag.
	 * Here the player gets information about where he is and what he should do. 
	 * 
	 * @param inventory The players inventory.
	 * @param parser	The parser to check yes and no answers
	 */
	@Override
	public void talk(Inventory inventory, Parser parser) {
		System.out.println("Nice to meet you foreign. My name is Freitag and i see you are in trouble. ");
		System.out.println("I saw how you ship sunk and you almost died in the water but thanks to god you are alive. ");
		System.out.println("You are on a island and i believe you want to escape from here. ");
		System.out.println("Hmmm.... To escape you need a new boat. ");
		System.out.println("I think you can find on this island everything what you need to build a new one. ");
		System.out.println("You can ask the Natives on the island, they will know where you can find your equipment. ");
		System.out.println("oh but you shouldn't make them angry because then they will try to kill you. ");
		System.out.println("But only when you make them angry so try to be polite to them, also you can try to find the Mage somewhere. ");
		System.out.println("I don't know where he is but i am sure he can help you. ");

		System.out.println("Oh but you must be careful. ");
		System.out.println("On this island are many hunters who want to kill you because they think you are against their god. " );
		System.out.println("So try to avoid them. ");
		System.out.println("It was nice to meet you, i hope you will survive. Good bye. ");
	}
}
