package de.dhbw.ravensburg.zuul.item;

/**
 * Class Weapon - Items that can be used for fighting.
 * <p>
 * The power of a weapon is described by "damage".
 * During a fight, the preditor "Creature" is harmed 
 * exactly by the damage-value of a weapon. 
 * 
 * @author Philipp Schneider
 * @version 02.05.2020
 */
public class Weapon extends Item {
	
	/**Damage that is transfered.*/
	private int damage;	
	
	/**
	 * Create a weapon described "name", with a specific "weight" and
	 * a "damage"-value.
	 * @param name		The name of the weapon.
	 * @param weight	The weight of the weapon.
	 * @param damage	The damage that is transfered.
	 */
	public Weapon(String name, float weight, int damage) {
		super(name, weight);
		this.damage = damage;
		setPortable(true);
	}

	/**
	 * 
	 * @return The damage that is transfered.
	 */
	public int getDamage() {
		return damage;
	}

}
