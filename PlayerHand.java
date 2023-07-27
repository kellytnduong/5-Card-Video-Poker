
/* 

Kelly Duong, Caleb Dodd, Ihsan Aday, & Taylor Williams

Single-Player Video Poker

================
PlayerHand Class
================

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The PlayerHand creates a hand for players. This class has a constructor, a hand size accessor, and card accessor, an add card method, a remove card method, a remove-at-index method, a poll method, and a method that checks for pairs in the hand and removes them.
*/

import java.util.*;

public class PlayerHand {
  //Instance Variables
  private LinkedList <Card> hand = new LinkedList <Card> ();
  
  //Constructor
  public PlayerHand() {
  }
  
  //Accessors
  public int getSize() {
    return hand.size();
  }

  //Mutators
  //Print hand method: Prints out the hand
  public void printHand() {
    for (int i=0;i<hand.size();i++) {
      System.out.print(hand.get(i)+"  ");
    }
    System.out.println();
  }

  //Add card-at-index: Index determines which card to add to the hand
  public Card getCard(int index) {
    return hand.get(index);
  }
  //Add card method
  public void add(Card card) {
    hand.add(card);
  }
  //Remove card method: Removes the card from the hand
  public void remove(Card card) {
    hand.remove(card);
  }
  //Remove-at-index method: Removes the card at the specified index
  public void remove(int index) {
    hand.remove(index);
  }
  //Poll method: Removes card at the top of the hand
  public Card poll() {
    return hand.poll();
  }
  //isEmpty method: Checks if the hand is empty
  public boolean isEmpty() {
    if (hand.size() == 0) {
      return true;
    }
    else {
      return false;
    }
  }

  //TWO PAIR CHECK METHOD: Checks for 2 pairs in the player's hand
  public boolean twoPairCheck() {
    int [] handRank = handRankSort(hand);
    
    int pairCount = 0;
    for (int i=0;i<handRank.length;i++) {
      for (int j=i+1;j<handRank.length;j++) {
        if (handRank[i] == handRank[j]) { //checks for pairs
          pairCount++; //keeps an active counter of the number of pairs
        }
      }
    }
    if (pairCount == 2) {//if two pairs are found it returns true
      return true;
    }
    return false;
  }
  
  
  //FULL HOUSE CHECK METHOD: Checks for a Full House
  public boolean fullHouseCheck() {
    int [] handRank = handRankSort(hand);
    boolean fullHouse = false;
    if(handRank[0]==handRank[1] && handRank[2]==handRank[3] && handRank[3]==handRank[4]){ //checks for a Pair, then a 3 of a Kind
      return fullHouse = true;
    }
    else if(handRank[0]==handRank[1] && handRank[1]==handRank[2] && handRank[3]==handRank[4]){ //checks for a 3 of a Kind, then a Pair
      return fullHouse = true;
    }
    return fullHouse; //returns false if no Full House is found
  }


  //OF A KIND AND PAIR CHECK METHOD: Checks for a pair, 3 of a Kind, 4 of a Kind

  public int ofAKindCheck() {
    int [] handRank = handRankSort(hand);
    int ofAKind = 0;
    for (int i=0;i<handRank.length;i++) {
      for (int j=i+1;j<handRank.length;j++) {
        if (handRank[i] == handRank[j]) {//if there is a Pair inside the hand, it checks for a 3 of a Kind
          if (j < handRank.length-1){ //ensures that the 3 of a Kind check only proceeds IF there are more cards in the hand to check (prevents out of bounds error)
            if(handRank[i] == handRank[j+1]){ //if there is a 3 of a Kind inside the hand, it checks for a 4 of a Kind
              if (j < handRank.length-2){ //ensures that the 3 of a Kind check only proceeds IF there are more cards in the hand to check (prevents out of bounds error)
                if (handRank[i] == handRank[j+2]){ //if there is a 4 of a Kind, then it returns to the main program with the knowledge that the player's hand contains a 4 of a Kind
                  ofAKind = 4;
                  return ofAKind;
                }
              } 
              ofAKind = 3;
              return ofAKind; //returns a 3 of a Kind if the hand does not contain a 4 of a Kind
            }
          }
          ofAKind = 2; 
          return ofAKind; //returns a Pair if the hand does not contain a 4 of a Kind or a 3 of a Kind
        }
      }
    }
    return ofAKind; //returns a 0 indicating that a Pair, 3 of a Kind, and 4 of a Kind weren't found
  }
  
  //STRAIGHT CHECK METHOD: Checks for a straight
  public boolean straightCheck() {
    boolean straightCheck = false;

    // Returns a sorted array of the card ranks
    int [] handRank = handRankSort(hand);

    int cardCount = 0;
    int cardCount2 = 0;
    
    for(int i=1; i<5; i++){
      //If the values in the hand in the position i+1 are one more than the position i then there is a straight
      if (handRank[i] - 1 == handRank[i-1]) {
        cardCount++;
        if (cardCount == 4) {
          straightCheck = true;
        }
      }
      // If there is an ace and 10, jack, queen, king then there is still a straight (High ace straight). It checks for an ace, then checks if all the other values are i + 9 more.
      if (handRank[0] == 1 && handRank[i] == i + 9) {
        cardCount2++;
        if (cardCount2 == 4) {
          straightCheck = true;
        } 
      }
    }
   return straightCheck;
  }

  //FLUSH CHECK METHOD: Checks for a flush
  public boolean flushCheck() {
    boolean flushCheck = true;
    
    for (int i = 1; i < hand.size(); i++) {
      Card card1 = hand.get(i - 1);
      Card card2 = hand.get(i);
      // If the suit of the first card is not the same as the second, it is not a flush.
      if (card1.getSuit() != card2.getSuit()) {
        return flushCheck = false;
      }
    }
    return flushCheck;
  }

  //ROYAL FLUSH CHECK: Checks for a royal flush
  public boolean royalFlushCheck() {
    boolean royalFlushCheck = true;

    int [] handRank = handRankSort(hand);
    
    for (int i = 1; i < hand.size(); i++) {
      Card card1 = hand.get(i - 1);
      Card card2 = hand.get(i);
      // handRank is sorted so handRank[0] will be 1 if it is a royal flush. Also check if the suit of the card at i (1 first) is the same as the card at i -  1
      if (card1.getSuit() != card2.getSuit() || handRank[0] != 1 || handRank[i] != i + 9) {
        royalFlushCheck = false;
      }
    }
    return royalFlushCheck;
  }

  //STRAIGHT FLUSH CHECK: Checks for a straight flush
  public boolean straightFlush(){
    boolean straightFlush = false;
    if (straightCheck()==true && flushCheck()==true){ //checks for a straight and a flush within a hand
      straightFlush = true;
    }
    return straightFlush;
  }

  //HAND RANK SORT METHOD: Sorts the player's hand by rank
  public int[] handRankSort(LinkedList <Card> hand) {
    // Normal array of integers to store the 5 rank values.
    int [] handArray = new int [5];
    for (int i = 0; i < 5; i++) {
      Card card = hand.get(i); // Get the card.
      int cardRank = card.getRank(); // Assign an int as the rank of the card.
      handArray[i] = cardRank; // Assign that int to the array, repeat.
    }

    // Selection sort to sort the array of ranks after it has been generated.
    for(int i=0; i<5; i++){
      int index=i;

      for(int j=i+1; j<5; j++){
        if(handArray[j]<handArray[index]){
          index=j;
        }
      }
      int smallest=handArray[index];
      handArray[index]=handArray[i];
      handArray[i]=smallest;
    }
    return handArray;
  } 
}