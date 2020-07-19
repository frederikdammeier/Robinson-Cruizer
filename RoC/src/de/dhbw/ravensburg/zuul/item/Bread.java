package de.dhbw.ravensburg.zuul.item;

/**
 * Class Bread - Food with predefined values.
 * 
 * @author Philipp Schneider
 * @version 03.05.2020
 */

public class Bread extends Food {

	/**
	 * @param name		The name of this food.
	 * @param weight	The weight of this food.
	 * @param nutrition	The nutritional-value of this food.
	 */
	public Bread() {
		super("Bread", 1f, 50);
	}

}
