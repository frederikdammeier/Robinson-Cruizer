package de.dhbw.ravensburg.zuul.item;

/**
 * Class RoomKey - An Item required to access a locked room.
 * 
 * @author Frederik Dammeier
 * @version 17.05.2020
 */
public class RoomKey extends Item {
	/**
	 * Create object of RoomKey.
	 * 
	 * @param name The keys identifier.
	 */
	public RoomKey(String name) {
		super(name, 0.0f);
		setPortable(true);
	}
}
