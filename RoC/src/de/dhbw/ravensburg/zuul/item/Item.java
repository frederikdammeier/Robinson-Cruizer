package de.dhbw.ravensburg.zuul.item;

public class Item {
	
	private String name;
	private float weight;
	
	public Item(String name, float weight) {
		this.name = name;
		this.weight = weight;	
	}
		
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public float getWeight() {
		return weight;
	}
	
	public void setWeight(float weight) {
		this.weight = weight;
	}
	
	
}
