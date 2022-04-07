package cardgame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cardgame.Card.Rank;

public class Gamefunc {
	Player1 p1=new Player1();//creating objects for player 1
	Player2 p2=new Player2();//creating objects for player 2

	/**
	 * Function for Begining the game
	 */

	List<Card> begin(List<Card> deck) {
		int i;
		
		/**
		 * creating array lists for 2 players
		 */
		
		List<Card> player1 = new ArrayList<>();
		List<Card> player2 = new ArrayList<>();
		
	
		/**
		 * Allocation of cards for 2 players
		 */
		
		for(i=1;i<=7;i++) 
		{
					
			player1.add(deck.get(0));
			deck.remove(0);	

            player2.add(deck.get(0));
            deck.remove(0);	

		}
		
		

		/**
		 * Displaying cards of the players
		 */
	
		System.out.println();
		System.out.println("Player1 : ");	
		System.out.println();
		for(i=0;i<player1.size();i++)
		{
			System.out.println(player1.get(i).getRank()+" "+player1.get(i).getSuit()+" ");
		}
		System.out.println();
		System.out.println("Player2 : ");
		System.out.println();
		for(i=0;i<player2.size();i++)
		{
			System.out.println(player2.get(i).getRank()+" "+player2.get(i).getSuit()+" ");
		}
		

		/**
		 * Getting cards from players
		 */
	
		
		p1.receiveInitialCards(player1);
		p2.receiveInitialCards(player2);
		return deck;
	
	}

	/**
	 * Function for playing the game
	 */

	
	void run(List<Card> deck) {
		int i;
		Card openCard = null;
		int score1=0,score2=0;
		openCard=deck.get(0);			//Declaring the topcard of the deck as openCard
		deck.remove(0);		
		

		/**
		 * Displaying the open card to the players
		 */
	
		System.out.println();
		System.out.println("OpenCard : "+openCard.getRank()+" "+openCard.getSuit());
		System.out.println();
		Card.Suit decCard=null;	
		while(score1<200 && score2<200 && !deck.isEmpty()) 
		{
			for(i=0; i<3; i++) 
			{
				if(p1.shouldDrawCard(openCard, decCard))
				{
					if(deck.size()!=0) 
					{	
						p1.receiveCard(deck.get(0));		
						deck.remove(0);			
					}
				}
				else{
					openCard=p1.playCard();
					if(openCard.getRank() == Rank.EIGHT && p1.myCards.size() != 0) 
					{
						decCard=p1.declareSuit();
					}
					break;
				}
			}
			for(i=0; i<3; i++) 
			{
				if(p2.shouldDrawCard(openCard, decCard))
				{
					if(deck.size()!=0) 
					{
						p2.receiveCard(deck.get(0));		
						deck.remove(0);			
					}
				}
				else
				{
					openCard=p2.playCard();
					if(openCard.getRank()==Rank.EIGHT && p2.myCards.size()!=0)
					{
						decCard=p2.declareSuit();
					}
					break;
				}
			}
		

			/**
			 * Getting the score from the player class for each player
			 */
		
			if(p1.myCards.size()==0 ||deck.size()==0) {
				score2=p2.getScore();
				System.out.println("\n Player1 :"+score2);
				System.out.println();
			}
			if(p2.myCards.size()==0 ||deck.size()==0) {
				score1=p1.getScore();
				System.out.println("\n Player2 :"+score1);
				System.out.println();
			}

			/**
			 * If the deck is empty the discarded cards are shuffled again
			 */
		
			if(deck.size()==0) {
				deck=Card.getDeck();
				Collections.shuffle(deck);
			}
		}
		winner(score1,score2);
	}
	

	/**
	 * The winner is decided based on the score 
	 */

	void winner(int s1,int s2) {
		if(s1>=200) {
			System.out.println("\n Player2 Wins");
		}
		else if(s2>=200) {
			System.out.println("\n Player1 Wins");
		}
		else {
			System.out.println("Finished");
		}
		

	}
}
