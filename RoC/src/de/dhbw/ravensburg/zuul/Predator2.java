package de.dhbw.ravensburg.zuul;

import de.dhbw.ravensburg.zuul.ui.CreatureSprite;

public class Predator2 implements Runnable {
	private Game game;
	private CreatureSprite currentCreatureSprite;
	private Player player;
	private boolean running = true;
	
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
						player.takeDamage(game.getCurrentRoom().getCreature().getDamage());
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

	
	public void stopThread() {
		running = false;
	}
}
