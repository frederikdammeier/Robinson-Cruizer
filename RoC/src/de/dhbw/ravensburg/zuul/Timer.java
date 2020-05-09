package de.dhbw.ravensburg.zuul;

/**
 * A simple timer that runs in a seperate thread and counts seconds in the console.
 * 
 * @author Frederik Dammeier
 * @version 08.05.2020
 */
public class Timer implements Runnable {
	private long startTime, timePassed;
	private boolean stop;
	
	/**
	 * a new Timer Object
	 */
	public Timer() {
		startTime = System.nanoTime();
		stop = false;
	}
	
	/**
	 * Count seconds.
	 */
	@Override
	public void run() {
		while(!stop) {
			try {
	    	    Thread.sleep(1000);
	    	} catch (InterruptedException ie) {
	    	    Thread.currentThread().interrupt();
	    	}
			timePassed = System.nanoTime()/1000000 - startTime/1000000;
	    	// System.out.println(timePassed/1000);   	
		}
	}
	
	/**
	 * 
	 * @return seconds passed
	 */
	public long getTimePassedSeconds() {
		return timePassed/1000;
	}
	
	/**
	 * Stops the timer.
	 */
	public void stopTimer() {
		stop = true;
	}
}
