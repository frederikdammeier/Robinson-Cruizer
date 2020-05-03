package de.dhbw.ravensburg.zuul.room;

import de.dhbw.ravensburg.zuul.item.*;

public class TestMain {

	public static void main(String[] args) {
		Room testRoom = new Ruin("testRuine", null, new Item("Apfel", 0.2f), new Item("Apfel", 0.2f), new Item("Banane", 0.3f));
		
		testRoom.getInventory().printContents();
		
		System.out.println("ein weiteres item hinzufügen");
		
		testRoom.getInventory().addItem(new Food("Fleisch", 0.5f));
		
		testRoom.getInventory().printContents();
	}

}
