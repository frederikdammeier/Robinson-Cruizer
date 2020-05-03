package de.dhbw.ravensburg.zuul.room;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashMap;

import de.dhbw.ravensburg.zuul.Creature;
import de.dhbw.ravensburg.zuul.item.Item;

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
 * @version 1.1
 */

public class EmptyRoom 
{
	private String description;
    private HashMap<String, EmptyRoom> exits;        // stores exits of this room.
    private Creature creatureInRoom;			
    private ArrayList<Item> inventory;			//Stores the Items that are currently present in the room.

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public EmptyRoom(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        inventory = null;
        creatureInRoom = null;
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, EmptyRoom neighbor) 
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
        return "You are " + description + ".\n" + getExitString();
    }

    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public EmptyRoom getExit(String direction) 
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
     * Tries to remove given Item.
     * 
     * @param item
     * @return true if successful; false if item didn't exist.
     */
    public boolean removeItemFromRoom(Item item) {
    	return inventory.remove(item);
    }
    
    /**
     * Adds an item to the rooms Inventory.
     * 
     * @param item
     */
    public void addItemToRoom(Item item) {
    	inventory.add(item);
    }
    
    /**
     * checks whether a given item is in the rooms inventory.
     * 
     * @param item
     * @return
     */
    public boolean checkIfItemInRoom(Item item) {
    	return inventory.contains(item); 
    }
}

