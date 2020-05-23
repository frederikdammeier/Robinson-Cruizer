package de.dhbw.ravensburg.zuul.creature;
import de.dhbw.ravensburg.zuul.Command;
import de.dhbw.ravensburg.zuul.Inventory;
import de.dhbw.ravensburg.zuul.Parser;
import de.dhbw.ravensburg.zuul.item.*;
import java.util.HashMap;
import java.util.Random;


/**
 * Class Native - a native in an adventure game.
 *
 * A native is a human with the ability to attack the player and reduce his lifepoints. 
 * Also a native can give the player informations about the island but he wants something for the information. 
 * "Native" is a subclass from "Human".
 * 
 * @author  Moritz Link
 * @version 23.05.2020
 */
public class Native extends Human{

	private Meat tradeItem;
	
	/**
	 * Create a native.
	 * It calls the constructor from the superclass "Human". 
	 */	
	public Native() {
		super();
		setLifepoints(50);
		setPeaceful(true);
		setDropItem(new Stick());
		setDamage(new Stick().getDamage());
		setName("Native");
		tradeItem = new Meat();
		
	}

	public void changePeaceful() { // nur wenn man ihnen nicht gibt was sie wollen betrügt

		setPeaceful(false); 

		// hier müsste dann die Abfrage kommen, ob Native gut oder böse ist--> angreifen

	}
	
	/**
	 * Defines the Dialog between the player and the native.
	 * Here the player gets in exchange for meat information about the island. 
	 * Not any native know something, some of them only try to get meat. 
	 */
	@Override
	public void talk(Inventory inventory, Parser parser) {
		Random random = new Random();



		HashMap<Integer, String> trade = new HashMap<Integer, String>();

		trade.put(1, "The prisoner knows where the mage is. ");
		trade.put(2, " s2"); // wissen wo das material ist
		trade.put(3, "s3"); // "-"
		trade.put(4, "somewhere on this island is a magic mushroom which can give you unlimited power. ");
		trade.put(5, "i have nothing for you you fool ");
		trade.put(6, "s6"); // noch etwas neues


		System.out.println("Hello do you have any meat for me? ");
		System.out.println("i can give you some inforamtion about the island in exchange for the meat.");
		System.out.println("Do you want to change? ");

		// Abfrage ob man meat hat im Inventar
		// Abfrage ob man handeln möchte

		Command command = parser.getCommand();





		if(command.getCommandWord().equals("yes")) {
			System.out.println("That's nice. ");
			// Abfrage ob man meat hat im Inventar
			// bei nein abbruch und man wird böse

			if(inventory.containsItem(tradeItem) == false) {
				System.out.println("Oh i see you tried to betray me! ");
				changePeaceful();

			}

			if(inventory.containsItem(tradeItem) == true) {

			inventory.removeItem(tradeItem);	


				int info = random.nextInt(6)+1;
				String information = trade.get(info);

				System.out.println("Ok then i will tell you something: ");
				System.out.println(information);
			}				

		}

		else {
			System.out.println("Then go away.");
			return;
		}

	}
	
}
