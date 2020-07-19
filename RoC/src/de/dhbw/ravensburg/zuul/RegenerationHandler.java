package de.dhbw.ravensburg.zuul;

/**
 * This class provides a method to automatically increase the players health counter if he isn't at maxHealt and he has full hunger points.
 * 
 * @author Frederik Dammeier
 * @version 27.05.2020
 *
 */
public class RegenerationHandler implements Runnable {
	private Player player;
	private boolean finished;
	private int regenSpeed = 2; //regeneration speed in hp/s
	private int starvationSpeed = 5; //how fast the player starves to his death in hp/s

	/**
	 * @param p The player Object on which to run the regeneration handler.
	 */
	public RegenerationHandler(Player p) {
		player = p;
		finished = false;
	}

	/**
	 * If the player has full hunger points, he regenerates his health at a speed of 10hp/s.
	 * If the player has 0 hunger points, he looses health at a speed of 5hp/s.
	 */
	@Override
	public void run() {
		while(!finished) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(player.getHunger() >= 100) {
				player.regenerate(regenSpeed);
			} else if(player.getHunger() == 0) {
				player.takeDamage(starvationSpeed);
			}
		}
	}

	/**
	 * To stop the tread.
	 */
	public void finish() {
		finished = true;
	}

}
