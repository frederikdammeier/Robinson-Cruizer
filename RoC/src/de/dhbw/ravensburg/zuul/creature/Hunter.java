package de.dhbw.ravensburg.zuul.creature;
import de.dhbw.ravensburg.zuul.item.*;
/**
 * Class Hunter - a hunter in an adventure game.
 *
 * A hunter is the enemy from the player.He tries to attack the player when he has the chance. 
 * A "Hunter" is a subclass from "Human".
 * 
 * @author  Moritz Link
 * @version 21.05.2020
 */
public class Hunter extends Human{

	/**
	 * Creates a hunter and set the abilities the hunter has.
	 * It calls the constructor from the superclass "Human".
	 */	
	public Hunter() {
		super();
		setLifepoints(50);
		setPeaceful(false);

		setDropItem(new Sword());
		setDamage(new Sword().getDamage());
		setName("Hunter");

	}
}
