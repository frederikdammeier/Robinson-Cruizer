package de.dhbw.ravensburg.zuul.item;

/**
 * Class Sail - Item with predefined values.
 * 
 * @author Philipp Schneider
 * @version 03.05.2020
 */
public class Sail extends Item {

	/**
	 * @param name	The name of this item.
	 * @param weight The weight of this item. 
	 */
	public Sail() {
		super("Sail", 3f);
		setPortable(true);
	}

}
