package de.dhbw.ravensburg.zuul;

/**
 * This Enum holds variables to represent the different levels of difficulties in RoC.
 * 
 * Difficulty in RoC depends on three variables:
 * 
 * Capacity of the players Inventory, the rate at which enemies deal damage to the player
 * and the time which the player can use to finish the game.
 * 
 * @author Frederik Dammeier
 * @version 17.05.2020
 */
public enum Difficulty {
	EASY(100f, 0.75f, 1800), MEDIUM(75f, 1f, 1200), HARD(50f, 1.25f, 60);
	
	private final float inventoryCapacity;
	private final float enemyDamageRate;
	private final long timeLimit;
	
	/**
	 * Contructor
	 * 
	 * @param inventoryCapacity
	 * @param damageRate
	 * @param timeLimit
	 */
	Difficulty(float inventoryCapacity, float damageRate, long timeLimit){
		this.inventoryCapacity = inventoryCapacity;
		this.enemyDamageRate = damageRate;
		this.timeLimit = timeLimit;
	}

	/** 
	 * @return the associated inventory capacity
	 */
	public float getInventoryCapacity() {
		return inventoryCapacity;
	}
	
	/**
	 * @return the associated enemy damage rate
	 */
	public float getEnemyDamageRate() {
		return enemyDamageRate;
	}
	
	/**
	 * @return the time limit
	 */
	public long getTimeLimit() {
		return timeLimit;
	}
}
