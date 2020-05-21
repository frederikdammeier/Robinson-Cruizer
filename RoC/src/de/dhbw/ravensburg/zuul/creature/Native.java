package de.dhbw.ravensburg.zuul.creature;
import de.dhbw.ravensburg.zuul.item.*;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;


/**
 * Class Native - a native in an adventure game.
 *
 * A native is a human with the ability to attack the player and reduce his lifepoints. 
 * Also a native can give the player informations about the island but he wants something for the information. 
 * "Native" is a subclass from "Human".
 * 
 * @author  Moritz Link
 * @version 21.05.2020
 */
public class Native extends Human{

	
	
	/**
	 * Create a native and set the abilities the native has.
	 * It calls the constructor from the superclass "Human".
	 */	
	public Native() {
		super();
		setLifepoints(50);
		setPeaceful(true);
		setDropItem(new Stick());
		setDamage(new Stick().getDamage());
		setName("Native");
		setTrade(new Meat());
		
	}
	
	/**
	 * Changes the mode from a Native from friendly to dangerous. 
	 * This can happen when you attack the Native. 
	 */
	
	public void changePeaceful() {
		setPeaceful(false); 
		attack(); // fhelt bis jz noch oder? 
	}

	/**
	 * Defines the Dialog between the player and the native.
	 * Here the player gets in exchange for meat information about the island. 
	 * Not any native know something, some of them only try to get meat. 
	 */
	@Override
	public void talk() {
		Random random = new Random();
		Scanner scanner = new Scanner(System.in);
		HashMap<Integer, String> trade = new HashMap<Integer, String>();
		
		trade.put(1, "The prisoner knows where the mage is. ");
		trade.put(2, ""); // wissen wo das material ist
		trade.put(3, ""); // "-"
		trade.put(4, "somewhere on this island is a magic mushroom which can give you unlimited power. ");
		trade.put(5, "i have nothing for you you fool ");
		trade.put(6, ""); // noch etwas neues
		
		
		System.out.println("Hello do you have any meat for me? ");
		System.out.println("i can give you some inforamtion about the island in exchange for the meat.");
		System.out.println("Do you want to change? ");
		
		// Abfrage ob man meat hat im Inventar
		// Abfrage ob man handeln möchte
		
		String answer = scanner.next();
		answer = answer.toLowerCase();
		
		
		if(answer.equals("no")) {
			System.out.println("Then go away.");
			return;
		}
		
		if(answer.equals("yes")) {
			System.out.println("That's nice. ");
			// Abfrage ob man meat hat im Inventar
			// bei nein abbruch und man wird böse
			if(containsItem(getTrade()) == false) {
				System.out.println("Oh i see you tried to betray me! ");
				changePeaceful();
				
			}
			
			if(containsItem(getTrade()) == true) {
				removeItem(getTrade());	

				int info = random.nextInt(6)+1;
				String information = trade.get(info);
				
				System.out.println("Ok then i will tell you something: ");
				System.out.println(information);
			}				
		}		
	}
	
	
	
}
