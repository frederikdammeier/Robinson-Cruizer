package de.dhbw.ravensburg.zuul;

import de.dhbw.ravensburg.zuul.creature.*;

public class Preditor implements Runnable {

	Creature creatureInRoom;
	Player player;

	public Preditor() {
	player = Game.getPlayer();
	creatureInRoom = Game.getCurrentRoom().getCreature();

	}

	@Override
	public void run() {

		long timeOfLastAttack = 0;
		System.out.println("in run ");

		if (Game.getCurrentRoom().getCreature() != null) {

			System.out.println("found Creature");
			

			// repeat until creature is dead;
			while (creatureInRoom.isDead() == false) {
				System.out.println(Game.getGameTime());

				// attack just every 3 seconds
				if ((Game.getGameTime() - timeOfLastAttack > 3)) {

					// check if the player is still alive
					if (player.getHealth() > 0) {
						// check if the creature is peaceful -> if NOT, attack!
						if (creatureInRoom.getPeaceful() != true) {
							// transfer the creatures damage to
							System.out.println(creatureInRoom.getName() + " attacks you");
							player.takeDamage(creatureInRoom.getDamage());
							timeOfLastAttack = Game.getGameTime();
						} else {
							// nothing to do here
							System.out.println("Creature is Peaceful -> no damage");
						}
					} else {
						// player got killed
						System.out.println("killed by " + creatureInRoom.getName());
						Game.setFinished();
						return; //leave the method 
					}
				}
				System.out.println("waiting to attack");
			}
		}
		// no Creature in the Room
		else if (Game.getCurrentRoom().getCreature() == null) {
			System.out.println("noCreature in the Room -> end Method");
		}
		System.out.println("end method ");
	}
}
