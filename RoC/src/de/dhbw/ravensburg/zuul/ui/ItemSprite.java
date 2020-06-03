package de.dhbw.ravensburg.zuul.ui;

import de.dhbw.ravensburg.zuul.item.Item;
import javafx.scene.image.Image;

public class ItemSprite extends Sprite {
	private Item item;
	
	public ItemSprite(double pX, double pY, double vX, double vY, Image image, Item item) {
		super(pX, pY, vX, vY, image);
		this.item = item;
	}
	
	public Item getItem() {
		return item;
	}
}
