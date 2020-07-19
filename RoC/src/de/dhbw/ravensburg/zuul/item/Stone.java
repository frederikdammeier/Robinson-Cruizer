package de.dhbw.ravensburg.zuul.item;


/**
 * Class Stone - Item with predefined values.
 * 
 * @author Moritz Link
 * @version 18.07.2020
 */
public class Stone extends Item {
	/**
	 * @param name	The name of this item.
	 * @param weight The weight of this item. 
	 */
	public Stone() {
		super("Stone", 100f);
		setPortable(false);
	}
}
