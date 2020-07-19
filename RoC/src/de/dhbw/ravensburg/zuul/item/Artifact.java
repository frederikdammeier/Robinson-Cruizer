package de.dhbw.ravensburg.zuul.item;


/**
 * Class Artifact - Item with predefined values.
 * 
 * @author Moritz Link
 * @version 18.07.2020
 */
public class Artifact extends Item {
	/**
	 * @param name	The name of this item.
	 * @param weight The weight of this item. 
	 */
	public Artifact() {
		super("Artifact", 100f);
		setPortable(false);
	}
}
