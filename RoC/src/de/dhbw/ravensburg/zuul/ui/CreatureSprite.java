package de.dhbw.ravensburg.zuul.ui;

/**
 * 
 * @author Frederick Dammeier
 *
 */
import de.dhbw.ravensburg.zuul.creature.Creature;
import javafx.scene.image.Image;

public class CreatureSprite extends Sprite {
	/**Any creature*/
	private Creature creature;
	
	/**
	 * Creates a CreatureSprite.
	 * @param pX 
	 * @param pY
	 * @param vX
	 * @param vY
	 * @param image
	 * @param creature
	 */
	public CreatureSprite(double pX, double pY, double vX, double vY, Image image, Creature creature) {
		super(pX, pY, vX, vY, image);
		this.creature = creature;
	}
	
	/**
	 * @return creature This creature.
	 */
	public Creature getCreature() {
		return creature;
	}
}
