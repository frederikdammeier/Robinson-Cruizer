package de.dhbw.ravensburg.zuul.creature;

import de.dhbw.ravensburg.zuul.item.*;

public class WaterPig extends Animal {

	public WaterPig() {
		super("Water Pig", true, 0, new Meat());
		setLivepoints(50);
		
	}

}
