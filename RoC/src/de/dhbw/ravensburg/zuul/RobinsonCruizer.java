package de.dhbw.ravensburg.zuul;

/**
 * -----------------
 * deprecated
 *------------------
 * Welcome to the Robinson Cruizer Adventure Game.
 * 
 * In this game, you're waking up on a beach on a lonely island somewhere in the
 * middle of the ocean. It' no big deal to leave, you just need a boat.
 * Too bad that the one you came with is hanging between those rocks.
 * 
 * 
 * As you go into the nearby rainforest seeking for building materials, strange things
 * start to happen...
 * 
 * 
 * @author Frederik Dammeier - Moritz Link - Philipp Schneider
 * @version 2020
 *
 */

public class RobinsonCruizer {

	public static void main(String[] args) {
		Game game = new Game(Difficulty.EASY);
		game.play();
	}
}
