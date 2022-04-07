package cardgame;

import java.util.*;

import cardgame.Card.*;

public class GameSrter {
	public static void main(String[] args) {		
		Gamefunc gamefunc = new Gamefunc();
		List<Card> deck = new ArrayList<>();	//creating array lists for deck
		deck=Card.getDeck();					//getting unshuffled Deck 
		Collections.shuffle(deck);				//Shuffling the Deck
		/**
		 * Sending Function for another class
		 */
		deck=gamefunc.begin(deck);
		gamefunc.run(deck);
	}
}