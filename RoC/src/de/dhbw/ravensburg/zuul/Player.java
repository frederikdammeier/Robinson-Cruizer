package de.dhbw.ravensburg.zuul;

import java.util.Timer;
import java.util.TimerTask;

/**
 * It holds variables and methods for the main protagonist of the game.
 * 
 * @author Frederik Dammeier - Philipp Schneider - Moritz Link
 * @version 11.05.2020
 *
 */
public class Player {
	private String name;
	private int hunger;
	private int health;
	private Inventory inventory;
	private int maxLife;
	private int damage;
	private long timeOfLastAttack;	
	private int timer;
	

	
	/**
	 * Creates a new object of type Player
	 * 
	 * @param name The name of the player. Doesn't have a function yet.
	 * @param inventorySize The maximum weight the Player can carry. Gets determined by the games difficulty.
	 */
	public Player(String name, float inventorySize, int maxLife) {
		this.name = name;
		hunger = 100;
		health = this.maxLife = maxLife;
		inventory = new Inventory(inventorySize);
		damage = 100;
	}
	
	
	/**
	 * Increases or diminishes the players hunger count. He has a max hunger of 100.
	 * 
	 * @param amount Positive number: player eats; Negative number: player gets increasingly hungry.
	 */
	public void eat(int amount) {
		hunger += amount;
		if(hunger < 0) hunger = 0;
		else if(hunger > 100) hunger = 100;
	}
	
	/**
	 * 
	 * @param amount that damage increases/decreases.
	 */
	public void setDamage(int amount) {
		damage = damage + amount;
	}
	
	/**
	 * Gives damage to the player by decreasing his health count.
	 * 
	 * @param amount of damage that is transfered by a creature.
	 * @return If the player has been killed by the damage.
	 */
	public boolean takeDamage(int amount) {
		health = health - amount;
		if(health <= 0) {
			health = 0;
			return true;              //in this case, the player is dead.
		}
		return false;
	}
	
	/**
	 * Regenerates the players health by increasing his health count.
	 * 
	 * @param amount
	 */
	public void regenerate(int amount) {
		health += amount;
		if(health > maxLife) health = maxLife; //a player cannot have more than maxLife health.
	}
	
	/**
	 * @return Name of the player.
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return Amount of hunger points left
	 */
	public int getHunger() {
		return hunger;
	}
	
	/**
	 * @return Amount of health points left
	 */
	public int getHealth() {
		return health;
	}
	
	/**
	 * @return A reference to the players inventory.
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/**
	 * @return Current damage that is transfered by the player.
	 */
	public int getDamage() {
		return damage;
	}

	/**
	 * @param timeOfAttack Last time at which the player started to attack.
	 */
	public void setTimeOfLastAttack(long timeOfAttack) {
		timeOfLastAttack = timeOfAttack;
	}
	
	/**
	 * @return timeOfAttack Last time at which the player started to attack.
	 */
	public long getTimeOfLastAttack() {
		return timeOfLastAttack;
	}
	
	/**
	 * 
	 * @param timer The timer when the player loses a hunger point.
	 */
	public void setTimer(int timer) {
		this.timer = timer;
	}
	/**
	 * 
	 * @return timer The timer when the player loses a hunger point.
	 */
	public int getTimer() {
		return timer;
	}
	
	/**
	 * The ability of the player to starve. 
	 * @param amount The amount of points the player starves.
	 * @return The number of hunger points from the player.
	 */
	public int hunger(int amount) {
		hunger = hunger - amount;
		return hunger;
	}
	/**
	 * Defines what happens if the player has different number of hunger points left. 
	 * When the player has only a low number left he gets a warning that he has to eat something.
	 * When he has no points left the player starts to get damage until he dies or eats something. 
	 * @param zähler The timer how much time until the player starves.
	 */
	public void hungerCounter(int zähler) 
	{
	
	 timer = zähler;
		
		final Timer TimerHunger = new Timer();
		TimerTask taskHunger = new TimerTask() 
		{
		
			
			@Override
			public void run() 
			{
				// Gibt an, dass der Timer immer runterzählen muss. 
				if(timer >= 0) {
					System.out.println(" "+ timer);
					timer--;
				}
				// was er nach dem Countdown machen soll
				if(timer == -1) {
					
					//immer einen HUngerpunkt abziehen, bis er  bei 0 ist. 
					if(getHunger()>=1) {
						System.out.println("Aktion: Spieler hungert");				
						System.out.println("Hunger: " +hunger(1));
						System.out.println();
						
					}
					// dem Timer wieder den Wert geben. Muss man manuell machen.
					timer = 3;
					
					//Bei 4 hungerpunkten soll eine Warunug ausgegeben werden.
					if(getHunger()== 4) {
						System.out.println("Sie sollten was essen! Sie verhungern gerade. ");
					}
					
					//Bei 0 Hungerpunkte soll der Spieler einen Schaden nehmen
					if(getHunger() == 0) {
						System.out.println("Aktion: Der Spieler nimmt Schaden");
						takeDamage(1);
						System.out.println("Gesundheit: " + getHealth());
						
						
					}
					
					// Wenn der Spieler keine Lebenspunkte mehr übrig hat soll der Timer aufhören zu laufen. 
					if(getHealth()== 0) {
						TimerHunger.cancel();
					}
//					if(getHealth() == 97) {
//						eat(20);
//						regenerate(5);
//					}
					
					
					
				}
						
				
			}
		};
		
		TimerHunger.schedule(taskHunger, 0, 1000 );
	}

	
}
