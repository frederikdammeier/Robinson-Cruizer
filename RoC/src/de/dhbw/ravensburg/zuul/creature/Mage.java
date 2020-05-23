package de.dhbw.ravensburg.zuul.creature;
import de.dhbw.ravensburg.zuul.*;
import java.util.Scanner;
/**
 * Class Mage - a mage in an adventure game.
 * A mage is a human with the ability to teleport the player to a random room. 
 * "Mage" is a subclass from "Human".
 * 
 * @author  Moritz Link
 * @version 04.05.2020
 */
public class Mage extends Human{
//	private Map map;
	
	/**
	 * Create a mage.
	 * It calls the constructor from the superclass "Human". 
	 */	
	public Mage() {
		super();
		setLifepoints(50);
		setName("Gandalf der Graue"); 
		setPeaceful(true);

		
	}

//	/**
//	 * Defines the Dialog between the player and the Mage.
//	 * The Mage asks the player if he wants to teleport to a random room. 
//	 * The player has the choice if he wants or not. 
//	 */
//	@Override
//	public void talk(Map map, Parser parser) {
//		
//		Command command = parser.getCommand(); 
//		
//		System.out.println("Hello foreign, ");
//		System.out.println("Nice to meet you. If you want I can help you. ");
//		System.out.println("I am a powerful mage and can teleport you to different places on this island. ");
//		System.out.println("Do you want to? ");
//		
//		
//		
//		if(command.getCommandWord().equals("yes")) {
//			System.out.println("OK i hope you will escape from this island. ");
//			System.out.println("Good bye. ");
//			
//			
//			//map.teleport(); // müssen erst schauen ob es funktioniert
//							// zu teleportiert Spieler
//		}
//		
//		else {
//			System.out.println("Ok but i wish you luck and wait here for you. ");
//		}
//	}
//	
//	
//	
//	/**
//	 * Method should teleport the player to a random room when the player talks with the mage.
//	 */
//	public void teleport() {
//		System.out.println("Soll hier Spieler in anderen Raum bringen");
//	}
}



