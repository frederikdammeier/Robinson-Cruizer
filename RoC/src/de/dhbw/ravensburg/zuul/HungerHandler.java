package de.dhbw.ravensburg.zuul;

/**
 * This class provides a method to automatically decrease the players hunger counter as the game progresses.
 * 
 * @author Frederik Dammeier
 * @version 27.05.2020
 *
 */
public class HungerHandler implements Runnable {
	private Player player;
	private int timeUntilFirstHunger = 20; //Time in seconds before the hunger counter begines to decrease.
	private float hungerRate = 1f; //the rate at which the player looses his hunger points in hp/s
	private boolean finished;
	
	/**
	 * @param p The player object to apply the HungerHanldler to.
	 */
	public HungerHandler(Player p) {
		player = p;
	}
	
	/**
	 * After his initial saturation period (timeUntilFirstHunger), the player looses hunger points at the constant speed of hungerRate / second.
	 */
	@Override
	public void run() {
		while(!finished) {
			if(player.getHunger() == 100) {				
				try {
					Thread.sleep(1000*timeUntilFirstHunger);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				player.eat(-1);
				
			} else {
				try {
					Thread.sleep((int) (1000/hungerRate));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				player.eat(-1);
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
