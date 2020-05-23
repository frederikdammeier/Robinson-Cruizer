package de.dhbw.ravensburg.zuul;

import de.dhbw.ravensburg.zuul.creature.*;

/**
 * The predator thread handels peaceful and agressive actions by creatures.
 * 
 * 
 * If a creature is aggressive, damage is transefers to the player.
 * If a creature is peaceful, the thread is still running, to check if this is going to change.
 * If there is no creature in a room (anymore), the thread is ended.
 * 
 * @author Philipp Schneider
 * @version 18.05.2020
 */


public class Predator implements Runnable {

	
	Game game;
	Creature creatureInRoom;
	Player player;
	long timeOfLastAttack;

	public Predator(Game game) {
		this.game = game;
		player = game.getPlayer();		
		timeOfLastAttack = 0;
	}

	@Override
	public void run() {
		System.out.println("");


		// Check if the thread has been interrupted
		while (!Thread.currentThread().isInterrupted()) {

			// checking which creature is in the room.
			creatureInRoom = game.getCurrentRoom().getCreature();

			// check if there's a creature in the room
			if (game.getCurrentRoom().getCreature() != null) {
				//System.out.print("There is a " + creatureInRoom.getName());
				System.out.print("There is a " + creatureInRoom.getName());

				// check if the player is still alive
				if (game.getPlayer().getHealth() > 0) {

					// attack just every 3 seconds and check if creature is peaceful
					if (!creatureInRoom.getPeaceful()) {
						if ((game.getGameTime() - timeOfLastAttack) > 3) {
							// transfer the creatures damage to
							System.out.println("\n				" + creatureInRoom.getName() + " attacks you");
							//System.out.println("				your health: " + 100 + "FIX" );
							game.getPlayer().takeDamage(creatureInRoom.getDamage());
							timeOfLastAttack = game.getGameTime();
						} else {
							System.out.println(" preparing to attack...");
						}
					} else {
						System.out.println(" which is peaceful at the moment...");
					}

					// In this case, the player is dead
				} else {
					System.out.println("killed by " + creatureInRoom.getName());
					game.setDead();
					Command quitGame = new Command("quit", null); //----Kill player
					game.quit(quitGame);
					return;
				}

			} else {
				// no creature in the room
				return;
			}

			//sleeping for a few seconds 
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		}
	}
}
