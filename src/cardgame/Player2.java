package cardgame;

import java.util.List;

public class Player2 implements PlayerStrategy{
	int playerId;
	List<Integer> opponentIds;
	List<Card> myCards;
	Card topPileCard;
	Card.Suit changedSuit;
	public void init(int playerId, List<Integer> opponentIds) {
		this.playerId = playerId;
		this.opponentIds = opponentIds;
	}

	/**
	 *  Get the card from player2
	 */

	public void receiveInitialCards(List<Card> cards) {
		this.myCards = cards;		
	}

	/**
	 * this function decides whether to draw  or drop a card
	 */
	public boolean shouldDrawCard(Card topPileCard, Card.Suit changedSuit) {
		this.topPileCard = topPileCard;
		this.changedSuit = changedSuit;
		if(changedSuit == null) {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(topPileCard.getSuit())||myCards.get(i).getRank().equals(topPileCard.getRank())) {
					return false;
				}
			}
		}
		else {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(changedSuit)) {
					return false;
				}
			}
		}
		return true;
		
	}

	/**
	 * pick the top card from the deck
	 */
	
	public void receiveCard(Card pickCard) {
		System.out.println("Player2 received :"+pickCard.getRank()+" "+pickCard.getSuit());
		myCards.add(pickCard);
	}
	/**
     * Called when this player is ready to play a card (will not be called if this player drew on
     * their turn).
     * <p>
     * This will end this player's turn.
     *
     * @return The card this player wishes to put on top of the pile
     */
	public Card playCard() {
		Card discarded = null;
		if(changedSuit == null) {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(topPileCard.getSuit())||myCards.get(i).getRank().equals(topPileCard.getRank())) {
					System.out.println("Player2 played : "+myCards.get(i).getRank()+" "+myCards.get(i).getSuit());
					discarded = myCards.get(i);
					myCards.remove(i);
					break;
				}
			}
		}
		else {
			for(int i=0;i<myCards.size();i++) {
				if(myCards.get(i).getSuit().equals(changedSuit)) {
					System.out.println("Player2 played : "+myCards.get(i).getRank()+" "+myCards.get(i).getSuit());
					discarded = myCards.get(i);
					myCards.remove(i);
					break;
				}
			}
		}
		return discarded;
		
	}
	/**
	 * If the drop card is 8 the player should declare the suit
	 */
	
	public Card.Suit declareSuit(){
		Card.Suit declaredSuit=null;
		int max=52,count=0;
		for(int i=0;i<myCards.size();i++) {
			count = 0;
			for(int j=0;j<myCards.size();j++) {
				if(myCards.get(i).getSuit() == myCards.get(j).getSuit())
					count++;
			}
			if(count < max) {
				max = count;
				declaredSuit=myCards.get(i).getSuit();
			}
		}
		System.out.println("\n Suit Declared : "+declaredSuit);
		System.out.println();
		return declaredSuit;
		
	}
	public void processOpponentActions(List<PlayerTurn> opponentActions) {
		
	}
	 public void reset() {
		 
	 }

	 /**
	 * The player2's score is calculated
	 */
		
	 @Override
		public int getScore() {
			int score = 0;
			for(int i=0;i<myCards.size();i++) {
				if(score <= 200)
					score+=myCards.get(i).getPointValue();
			}
			return score;
		}
}
