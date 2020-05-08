package de.dhbw.ravensburg.zuul.creature;

import de.dhbw.ravensburg.zuul.item.*;

public class Native extends Human{

	/**
	 * Class Native - a native in an adventure game.
	 *
	 * This class is part of the "World of Zuul" application. 
	 * "World of Zuul" is a very simple, text based adventure game.  
	 *
	 * A native  is a human with the ability to attack the player and reduce his livepoints. 
	 * "Native" is a subclass from "Human".
	 * 
	 * @author  Moritz Link
	 * @version 2020.05.02
	 */
	private Weapon weapon; // muss den Schaden der Waffe wissen, um Schaden von Nativ festzulegen.
	
	/**
	 * Create a native with values: name, innocent, drop. It calls the constructor from the superclass "Human".
	 * @param name The name of the native.
	 * @param innocent If the native is friendly or not.
	 * @param drop The Item the native drops when he dies. 
	 */	
	public Native(String name, boolean innocent, Item drop) {
		super(name, innocent, drop);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Define the damage the native can cause the player.
	 * @param damage the damage the native can cause the player. 
	 */
	@Override
	public void setDamage(int damage) {
		damage  = weapon.getDamage(); 	/* Diese Methode soll den von seiner 
											Waffe abh�ngig machen  */

	}
}
