package de.dhbw.ravensburg.zuul.item;

/**
 * Class Banana - Food with predefined values.
 * 
 * @author Philipp Schneider
 * @version 03.05.2020
 */

public class Banana extends Food{

	/**
	 * @param name		The name of this food.
	 * @param weight	The weight of this food.
	 * @param nutrition	The nutritional-value of this food.
	 */
	public Banana() {
		super("Banana", 0.25f, 15);
	}	
}
