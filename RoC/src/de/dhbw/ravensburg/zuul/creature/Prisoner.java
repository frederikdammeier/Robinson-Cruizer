package de.dhbw.ravensburg.zuul.creature;
import de.dhbw.ravensburg.zuul.Inventory;
import de.dhbw.ravensburg.zuul.item.*;

import java.util.Scanner;
/**
 * Class Prisoner - a prisoner in an adventure game.
 *
 * A prisoner is a human with the ability help the player when he gets the key for his prison. 
 * Then he tells the player where he can find the mage.  
 * "Prisoner" is a subclass from "Human".
 * 
 * @author  Moritz Link
 * @version 21.05.2020
 */
public class Prisoner extends Human{
	private Inventory inventory;
	
	/**
	 * Create the prisoner and set the abilities the prisoner has.
	 * It calls the constructor from the superclass "Human".
	 */	
	public Prisoner() {
		super();
		setLifepoints(50);
		setPeaceful(true);
		setTrade(new Key());
		setName("Guy Fawkes");
		
		}
	
	/**
	 * Defines the Dialog between the player and the Prisoner.
	 * Here the player gets in exchange for the Key the information where the mage is. 
	 */
	@Override
	public void talk() {
		Scanner scanner = new Scanner(System.in);
		Item key = new Key();
		System.out.println("Ah please help me. I am in this Prison since two weeks. ");
		System.out.println("Can you open it? When you will free me i can give you information about the island. ");
		System.out.println("Do you have the key for me? ");
		
		inventory = new Inventory(); //darf ich nicht machen 
		String answer = scanner.next();
		answer= answer.toLowerCase();
		
		scanner.close();
		if(answer.equals("no")) {
			System.out.println("Then came back if you found it but i won't tell you information without the key. ");
			System.out.println("Bye. ");
			return;
		}
		
		if(answer.equals("yes")) {
			 
			// ab hier geht es nicht mehr
			//bei nein
			if(inventory.containsItem(key) == false) {
				System.out.println("Don't try to betray me fuck of. ");
				return;
			}
			
			//bei ja:
			if(inventory.containsItem(key) == true) {							
				// dann aus dm Inventar entfernen 
				getInventory().removeItem(getTrade());	
			
				//Info ausgeben			
				System.out.println("Thank you now i am free. ");
				System.out.println("So i will tell you my secret information about the Mage. ");
				System.out.println("On this island is a mage and he can help you. ");
				System.out.println("He waits on the second floor int the Room named ruinMage. "); // Stimmt die Bezwichnung? 
				System.out.println("I hope you can find him.");
			}
			
			
		}
		
		
		
		
	}
}
