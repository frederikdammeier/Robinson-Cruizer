package de.dhbw.ravensburg.zuul.ui;

import de.dhbw.ravensburg.zuul.creature.Creature;
import javafx.scene.image.Image;

public class CreatureSprite extends Sprite {
	private Creature creature;
	
	public CreatureSprite(double pX, double pY, double vX, double vY, Image image, Creature creature) {
		super(pX, pY, vX, vY, image);
		this.creature = creature;
	}
	
	public Creature getCreature() {
		return creature;
	}
}
