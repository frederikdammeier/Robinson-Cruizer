package de.dhbw.ravensburg.zuul;

/**
 * This class provides a method to automatically decrease the players hunger counter as the game progresses.
 * 
 * @author Frederik Dammeier
 * @version 17.07.2020
 *
 */
public class HungerHandler implements Runnable {
	/**The player of the game*/
	private Player player;
	/**Time in seconds before the hunger counter begines to decrease.*/
	private int timeUntilFirstHunger = 20;
	/**The rate at which the player looses his hunger points in hp/s*/
	private float hungerRate = 1f; 
	/**Boolean to stop this thread*/
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
				player.eat(-1);
				try {
					Thread.sleep((int) (1000/hungerRate));
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}		
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
