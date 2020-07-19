package de.dhbw.ravensburg.zuul;

import de.dhbw.ravensburg.zuul.ui.CreatureSprite;

/**
 * Enables peaceful and agressive actions by creatures.
 * 
 * If a creature is aggressive, damage is transefers to the player.
 * If a creature is peaceful, the thread is still running, to check if this is going to change.
 * If there is no creature in a room (anymore), the thread is ended.
 * 
 * @author Frederick Dammeier
 *
 */
public class Predator2 implements Runnable {
	private Game game;
	private CreatureSprite currentCreatureSprite;
	private Player player;
	private boolean running = true;
	
	/**
	 * @param game The game.
	 */
	public Predator2(Game game) {
		this.game = game;
		player = game.getPlayer();
	}
	
	@Override
	public void run() {
		while(running) {
			currentCreatureSprite = game.getCurrentRoom().getCreatureSprite();
			if(currentCreatureSprite != null) {
				if(!game.getCurrentRoom().getCreature().getPeaceful()) {
					if(player.getPlayerSprite().distanceTo(currentCreatureSprite.getCenterX(), currentCreatureSprite.getCenterY()) < 100){
						player.takeDamage((int) (game.getCurrentRoom().getCreature().getDamage()*game.getEnemyDamageRate()));
						if(player.getHealth() == 0) {
							game.setDead(true);
						}
					}
				}		
			}
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * Stops the thread by setting running boolean false.
	 */
	public void stopThread() {
		running = false;
	}
}
