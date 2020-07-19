package de.dhbw.ravensburg.zuul.item;

/**
 * Class Mushroom - Food with predefined values.
 * 
 * @author Philipp Schneider
 * @version 03.05.2020
 */

public class Mushroom extends Food {

	/**
	 * @param name		The name of this food.
	 * @param weight	The weight of this food.
	 * @param nutrition	The nutritional-value of this food.
	 */
	public Mushroom() {
		super("Mushroom", 0.3f, 10);
	}

}
