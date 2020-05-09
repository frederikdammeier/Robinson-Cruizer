package de.dhbw.ravensburg.zuul.creature;
import de.dhbw.ravensburg.zuul.item.*;
public class AppCreature {

	public static void main(String[] args) {

		Snake s = new Snake(20);
		System.out.println(s.getDamage());
		System.out.println(s.getName());
		s.attack();
		System.out.println(s.getDropItem()); // wird immer link ausgegeben
		s.dropItem();		// funktioniert
		System.out.println();
		
		Ape a = new Ape(30);
		System.out.println(a.getName());
		a.attack();	
		System.out.println();
		
		WaterPig wp = new WaterPig(0);
		wp.dropItem();  
		System.out.println(wp.getName());
		System.out.println();
		
		Mage magier = new Mage(30);
		System.out.println(magier.getName());		
		magier.teleport();
		magier.attack();
		magier.talk();
		System.out.println();
		
		Native n = new Native(0);
		n.attack();
		n.dropItem();
		n.talk();
		System.out.println(n.getDropItem());
		System.out.println(n.getName());
	}

}
