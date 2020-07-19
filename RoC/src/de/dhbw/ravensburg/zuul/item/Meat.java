package de.dhbw.ravensburg.zuul.item;

/**
 * Class Meat - Food with predefined values.
 * 
 * @author Philipp Schneider
 * @version 03.05.2020
 */

public class Meat extends Food {

	/**
	 * @param name		The name of this food.
	 * @param weight	The weight of this food.
	 * @param nutrition	The nutritional-value of this food.
	 */
	public Meat() {
		super("Meat", 0.5f, 50);
	}

}
