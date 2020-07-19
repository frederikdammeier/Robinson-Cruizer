package de.dhbw.ravensburg.zuul.ui;

import de.dhbw.ravensburg.zuul.item.Item;
import javafx.scene.image.Image;

/**
* Handling of Sprites that are Items.
*/

public class ItemSprite extends Sprite {
	private Item item;
	
	/**
	 * Creates an itemSprite.
	 * @param pX
	 * @param pY
	 * @param vX
	 * @param vY
	 * @param image
	 * @param item
	 */
	public ItemSprite(double pX, double pY, double vX, double vY, Image image, Item item) {
		super(pX, pY, vX, vY, image);
		this.item = item;
	}
	
	/**
	 * @return item Returns an item.
	 */
	public Item getItem() {
		return item;
	}
}
