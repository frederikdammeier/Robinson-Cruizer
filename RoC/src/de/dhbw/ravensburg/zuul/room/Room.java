package de.dhbw.ravensburg.zuul.room;
import java.util.Set;

import java.util.HashMap;

import de.dhbw.ravensburg.zuul.creature.*;
import de.dhbw.ravensburg.zuul.item.*;
import de.dhbw.ravensburg.zuul.Inventory;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  Michael Kölling, David J. Barnes and Frederik Dammeier
 * @version 09.05.2020
 */

public class Room 
{
	private String description;
    private HashMap<String, Room> exits;        					//Stores exits of this room.
    private Creature creatureInRoom;			
    private Inventory inventory;									//Stores the Items that are currently present in the room.
    private RoomType type;											//To determine the graphical representation for the room.
    protected HashMap<String, Integer> creatureSpawnProbability;  	//Stores a mapping of creatures in percentages
    protected HashMap<String, Integer> itemSpawnProbability; 		//Stores a mapping of items in percentages
    private boolean locked;
    private RoomKey key;
    
	/**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard". May add "Items" to the room.
     * 
     * @param description The room's description.
     * @param specialItems Items to add to the rooms inventory.
     */
    public Room(String description, Item... specialItems) 
    {
    	locked = false;
        this.description = description;
        exits = new HashMap<>();
        setInventory();
        creatureInRoom = null;
        type = RoomType.EMPTY_ROOM;
        
        inventory.addMultipleItems(specialItems);  
    }
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard". Adds a "Creature" to the room. 
     * May add "Items" to the room.
     * 
     * @param description The room's description.
     * @param creature Creature to add to the room.
     * @param specialItems Items to add to the room.
     */
    public Room(String description, Creature creature, Item... specialItems) {
    	locked = false;
    	this.description = description;
        exits = new HashMap<>();
        setInventory();
        creatureInRoom = creature;
        type = RoomType.EMPTY_ROOM;
		
        inventory.addMultipleItems(specialItems);
	}
	
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard". Adds a "Creature" to the room. 
     * Assigns the room a type to be recognized by the UI.
     * May add "Items" to the room.
     * 
     * @param description The room's description.
     * @param creature Creature to add to the room.
     * @param type Type of the room.
     * @param specialItems Items to add to the room.
     */
	public Room(String description, Creature creature, RoomType type, Item... specialItems) {
		locked = false;
		this.description = description;
        exits = new HashMap<>();
        setInventory();
        creatureInRoom = creature;
        this.type = type;
		
        inventory.addMultipleItems(specialItems);
	}
	
	/**
	 * Randomly chooses a creature for the instants room based on the probabilities in creatureSpawnProbability.
	 * 
	 * Make sure to add all newly added creatures here.
	 */
	protected void spawnCreature() {
		if(creatureSpawnProbability != null) {
			String[] helperArray = new String[100];
			for(int j = 0; j < 100; j++) {
				helperArray[j] = "";
			}
			
			int i = 0, b = 0, r;
			Set<String> keys = creatureSpawnProbability.keySet();
			
			//Generate selector Array
			for(String creature : keys) {
				b += creatureSpawnProbability.get(creature);
				for(; i < b && i < 100; i++) {
					helperArray[i] = creature;
				}
			}
			
			//Chose random number:
			r = (int) (Math.random()*100);
			
			//Generate chosen Creature
			switch(helperArray[r]){
			case "Ape": 
				creatureInRoom = new Ape(50);
				break;
			case "Mage": 
				creatureInRoom = new Mage(50);
				break;
			case "Native": 
				creatureInRoom = new Native(50);
				break;
			case "Snake": 
				creatureInRoom = new Snake(50);
				break;
			case "Waterpig": 
				creatureInRoom = new WaterPig(50);
				break;
			}
		}
	}
	
	
	/**
	 * Randomly chooses a item for the instants room based on the probabilities in itemSpawnProbability.
	 * 
	 * Make sure to add all newly added items here.
	 */
	protected void populateInventory() {
		if(itemSpawnProbability != null) {
			Set<String> keys = itemSpawnProbability.keySet();
			int r;
			
			for(String item : keys) {
				r = (int) (Math.random()*100);
				if(r < itemSpawnProbability.get(item)) {
					switch(item) {
					case "Apple":
						inventory.addItem(new Apple());
						break;
					case "Banana":
						inventory.addItem(new Banana());
						break;
					case "Bread":
						inventory.addItem(new Bread());
						break;
					case "Coconut":
						inventory.addItem(new Coconut());
						break;
					case "Meat":
						inventory.addItem(new Meat());
						break;
					case "Mushroom":
						inventory.addItem(new Mushroom());
						break;
					case "Resin":
						inventory.addItem(new Resin());
						break;
					case "Rope":
						inventory.addItem(new Rope());
						break;
					case "Sail":
						inventory.addItem(new Sail());
						break;
					case "Stick":
						inventory.addItem(new Stick());
						break;
					case "Sword":
						inventory.addItem(new Sword());
						break;
					case "Timber":
						inventory.addItem(new Timber());
						break;					
					}
				}
			}
		}
	}

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitsAsString(); 
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitsAsString()
    {
        StringBuilder sb = new StringBuilder("Exits:");
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
        	sb.append("\n");
            sb.append(" ");
            sb.append(exit);
            sb.append(": ");
            sb.append(exits.get(exit).getShortDescription());
        }
        if(creatureInRoom != null) {
        	sb.append("\nCreature in Room: ");
        	sb.append(creatureInRoom.getName());
        }
        if(inventory.getNumberOfItemsInInventory() > 0) {
        	sb.append("\nItems in Room: ");
        	sb.append(inventory.getContentsAsString());
        }
        return sb.toString();
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
    
    /**
     * Getter for creature in the room.
     * 
     * @return
     */
    public Creature getCreature() {
    	return creatureInRoom;
    }
    
    /**
     * Replaces creature in room with the given Creature. 
     * 
     * @param creature null to remove the rooms creature.
     */
    public void setCreature(Creature creature) {
    	creatureInRoom = creature;
    }
    
    /**
     * Initializes the rooms inventory variable. To be called by the inheriting classes.
     * 
     * @return false if already initialized.
     */
    protected boolean setInventory() {
    	if(inventory == null) {
    		inventory = new Inventory();
    		return true;
    	} else {
    		return false;
    	}
    }
    
    /**
     * To be called on initialization if a room should be locked.
     * 
     * @param key The key required to unlock the room;
     */
    public void lockRoom(RoomKey key) {
    	locked = true;
    	this.key = key;
    }
    
    /**
     * Check whether the room is locked.
     * 
     * @return true if locked, false if unlocked.
     */
    public boolean isLocked() {
    	return locked;
    }
    
    /**
     * Unlocks the room.
     */
    public void unLock() {
    	locked = false;
    }
    
    /**
     * A reference to the room's inventory instance.
     * 
     * @return The room's inventory instance.
     */
    public Inventory getInventory() {
    	return inventory;
    }
    
    /**
     * Getter for the room's key.
     * 
     * @return The room's key.
     */
    public RoomKey getKey(){
    	return key;
    }
    
    /**
     * Getter for type.
     * @return RoomType.
     */
    public RoomType getType() {
		return type;
	}
    
    /**
     * To be used by the implementing classes.
     * @param type
     */
	protected void setType(RoomType type) {
		this.type = type;
	}
	
	/**
	 * Checks whether a given directional exit exists for a room.
	 * 
	 * @param exit
	 * @return
	 */
	public boolean hasExit(String exit) {
		return exits.containsKey(exit);
	}
	
	/**
	 * Checks whether the room has an exit towards the given room.
	 * 
	 * @param room The room to check for.
	 * @return
	 */
	public boolean hasExitToRoom(Room room) {
		return exits.containsValue(room);
	}
	
}

