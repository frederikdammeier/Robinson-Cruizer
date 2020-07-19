package de.dhbw.ravensburg.zuul.item;

/**
 * Class Food - an Item with a nutritional value.
 * 
 * <p>
 * Therefore it can be eaten by the "Player".
 *  
 * @author Philipp Schneider
 * @version 02.05.2020
 */

public class Food extends Item {

	/**The nutritional-value of the food.*/
	private int nutrition;	
	// private boolean edible;
	
	/**
	 * Create a Food-Item, that is decribed by "name", has a 
	 * "weight" and a "nutrition[al]" value.
	 * @param name		The name of the food.
	 * @param weight	The weight of the food.
	 * @param nutrition	The nutrional value of the food.
	 */
	public Food(String name, float weight, int nutrition) {
		
		super(name, weight);
		this.nutrition = nutrition;
		setPortable(true);
	}

	/**
	 * @return The nutritional value of the food.
	 */
	public int getNutrition() {
		return nutrition;
	}
}
