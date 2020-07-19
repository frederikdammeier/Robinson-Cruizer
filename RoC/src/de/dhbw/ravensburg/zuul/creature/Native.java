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
 * <p>
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
	 * Creates a native.
	 * It calls the constructor from the superclass "Human". 
	 */	
	public Native() {
		super();
		setLifepoints(100);
		setPeaceful(true);
		setDropItem(new Stick());
		setDamage(new Stick().getDamage());
		setName("Native");
		tradeItem = new Meat();
		
	}
	/**
	 * This method changes the type of the native to false. 
	 * This means the native will attack the player.
	 * The method will be called when the player tries to betray the native.
	 */
	public void changePeaceful() { 

		setPeaceful(false);

	}
	
	/**
	 * Defines the Dialog between the player and the native.
	 * Here the player gets in exchange for meat information about the island. 
	 * Not any native know something, some of them only try to get meat. 
	 * @param inventory The players inventory.
	 * @param parser	The parser to check yes and no answers.
	 */
	@Override
	public void talk(Inventory inventory, Parser parser) {
		Random random = new Random();



		HashMap<Integer, String> trade = new HashMap<Integer, String>();

		trade.put(1, "The prisoner knows where the mage is. ");
		trade.put(2, "The prisoner is in the dungeon. "); // wissen wo das material ist
		trade.put(3, "You should be carefull, if you try to betray the natives they will attack you. "); // "-"
		trade.put(4, "Somewhere on this island is a magic-mushroom which can give you unlimited power. ");
		trade.put(5, "i have nothing for you you fool ");
		trade.put(6, "Go to the Library there is something important for you. "); // noch etwas neues


		System.out.println("Hello do you have any meat for me? ");
		System.out.println("i can give you some inforamtion about the island in exchange for the meat.");
		System.out.println("Do you want to change? ");

	
		// Asks the player if he want to trade.
		Command command = parser.getCommand();


		if(command.getCommandWord().equals("yes")) {
			System.out.println("That's nice. ");
			
			// checks if the Item Meat is in the inventory from the player
			// when not, native feels betrayed and calls changePeaceful()
			if(inventory.containsItem(tradeItem) == false) {
				System.out.println("Oh i see you tried to betray me! ");
				changePeaceful();

			}

			if(inventory.containsItem(tradeItem) == true) {

			//removes Item Meat from the Inventory
			inventory.removeItem(tradeItem);	

				// chooses one random information
				int info = random.nextInt(6)+1;
				String information = trade.get(info);

				System.out.println("Ok then i will tell you something: ");
				System.out.println(information);
			}				

		}

		// if player doesn't want to trade.
		else {
			System.out.println("Then go away.");
			return;
		}

	}
	
}
