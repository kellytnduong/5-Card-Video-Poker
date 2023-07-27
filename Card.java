/* 

Kelly Duong, Caleb Dodd, Ihsan Aday, & Taylor Williams

Single-Player Video Poker

==========
Card Class
==========

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

The card class creates, modifies, and converts integer values of a card into output for the user. This class is used in both the deck and player hand classes and has 2 constructors, a rank mutator and accessor, suit mutator and accessor, and a toString method.

*/

public class Card {
  //Instance variables
  private int suit;
  private int rank;

  //Constructors
  public Card() {
    suit = 0;
    rank = 0;
  }
  public Card(int suit, int rank) {
    this.suit = suit;
    this.rank = rank;
  }

  //Mutators
  public void setSuit(int suit) {
    this.suit = suit;
  }
  public void setRank(int rank) {
    this.rank = rank;
  }

  //Accessors
  public int getSuit() {
    return this.suit;
  }
  public int getRank() {
    return this.rank;
  }

  //toString method
  public String toString() {
    String suitString = "";
    if (this.suit == 1) {
        suitString="♡";
      }
    else if (suit == 2) {
        suitString="♢";
      }
    else if (suit == 3) {
        suitString="♧";
      }
    else if (suit == 4) {
        suitString="♤";
      }
    switch(this.rank) {
      case 1:
        return("[A "+suitString+" ]");
      case 2:
        return("[2 "+suitString+" ]");
      case 3:
        return("[3 "+suitString+" ]");
      case 4:
        return("[4 "+suitString+" ]");
      case 5:
        return("[5 "+suitString+" ]");
      case 6:
        return("[6 "+suitString+" ]");
      case 7:
        return("[7 "+suitString+" ]");
      case 8:
        return("[8 "+suitString+" ]");
      case 9:
        return("[9 "+suitString+" ]");
      case 10:
        return("[10 "+suitString+" ]");
      case 11:
        return("[J "+suitString+" ]");
      case 12:
        return("[Q "+suitString+" ]");
      case 13:
        return("[K "+suitString+" ]");
      default:
        return("Invalid card input.");        
    }
  }
}