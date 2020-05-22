package de.dhbw.ravensburg.zuul.item;

/**
 * Class Rope - Item with predefined values.
 * 
 * @author Philipp Schneider
 * @version 03.05.2020
 */

public class Rope extends Item {

	/**
	 * @param name	The name of this item.
	 * @param weight The weight of this item. 
	 */
	public Rope() {
		super("Rope", 1.5f);
		setPortable(true);
	}

}
