/* 

Kelly Duong, Caleb Dodd, Ihsan Aday, & Taylor Williams

Single-Player Video Poker

==========
Deck Class
==========

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The deck class creates a deck of cards. It has a constructor, a populate method, an add card  method, a peek method (which is like an accessor except it only looks at the top card), a draw card method, a deck shuffling method, and an isEmpty method that checks for whether or not the deck is empty.

*/

import java.util.*;

public class Deck {
  LinkedList<Card> deck = new LinkedList<Card>();

  //Constructor
  public Deck() {
    populate();
    Collections.shuffle(this.deck);
  }

  //Populate method: Populates the deck with cards
  public void populate() {
    for (int s=1; s<=4; s++) {
      for (int r=1; r<=13; r++){
        Card card = new Card(s,r);
        deck.add(card);
      }
    }
  }

  //Add card method
  public void add(Card card) {
    deck.add(card);
  }

  //Peek card method (only looks ar card at the top of the deck)
  public Card peek() {
    return(deck.peek());
  }

  //Draw card method: Draw card from top of the deck
  public Card draw() {
    if(!deck.isEmpty()) {
      return(deck.poll());
    }
    return(null);
  }

  //Shuffle deck method
  public void shuffle() {
    Collections.shuffle(this.deck);
  }

  //isEmpty method: checks if deck is empty or not
  public boolean isEmpty() {
    if (deck.size() == 0) {
      return true;
    }
    else {
      return false;
    }
  }
}