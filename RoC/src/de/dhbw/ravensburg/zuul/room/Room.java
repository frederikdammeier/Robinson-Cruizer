package de.dhbw.ravensburg.zuul.room;
import java.util.Set;

import java.util.ArrayList;
import java.util.HashMap;

import de.dhbw.ravensburg.zuul.creature.*;
import de.dhbw.ravensburg.zuul.item.Item;
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
    private HashMap<String, Room> exits;        // stores exits of this room.
    private Creature creatureInRoom;			
    private Inventory inventory;			//Stores the Items that are currently present in the room.
    private RoomType type;					//To determine the graphical representation for the room.
    protected HashMap<String, Integer> creatureSpawnProbability;  	//Stores a mapping of creatures in percentages

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
		this.description = description;
        exits = new HashMap<>();
        setInventory();
        creatureInRoom = creature;
        this.type = type;
		
        inventory.addMultipleItems(specialItems);
	}
	
	
	protected void spawnCreature() {
		if(creatureSpawnProbability != null) {
			String[] helperArray = new String[100];
			for(int j = 0; j < 100; j++) {
				helperArray[j] = "";
			}
			
			int i = 0, b = 0, r;
			Set<String> keys = creatureSpawnProbability.keySet();
			
			//Generate selector Array
			for(String s : keys) {
				b += creatureSpawnProbability.get(s);
				for(; i < b && i < 100; i++) {
					helperArray[i] = s;
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
     * A reference to the rooms inventory instance.
     * 
     * @return The rooms inventory instance.
     */
    public Inventory getInventory() {
    	return inventory;
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
}

