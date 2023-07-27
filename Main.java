/*

Kelly Duong, Caleb Dodd, Ihsan Aday, & Taylor Williams

Version 2

Single-Player Video Poker

==========
Main Class
==========

~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

Video Poker / Single-Player Poker / 5 Card Poker starts with how much the player wants to bet, which will be multiplied based on the hand they get at the end of the game. The player is then given a hand consisting of 5 cards. The player can then choose how many cards they want to remove, in which case they will be able to choose which specific cards they want to remove. The chosen cards are then discarded and replaced with new cards. At this point, the game will display how much money the player wins.

*/

import java.util.*;

//MAIN METHOD
class Main {
  public static void main(String[] args) {
    System.out.println("\nWelcome to LEPRECHAUN POKER!"); // Intro statement displayed to user
    // The boolean below enables the user to decide whether or not they would like to play again. 'True' means 'play', 'false' means 'quit'.
    boolean playAgain = true; 
    double bet = 0;
    while (playAgain == true) { // Runs the main program while the playAgain boolean indicates 'play' with 'true'
    
      try {
        
        Random rand = new Random();
        // INITIALIZATION/GAME PREPARATIONS
        // Random name for the leprechaun.
        int randt = rand.nextInt(5);
        String name = "";
        switch(randt){
          case 0:
            name = "St. Jeremy";
            break;
          case 1:
            name = "Juan Mendez The Great";
            break;
          case 2:
            name = "Old Man Barthaloy III";
            break;
          case 3:
            name = "Orphan Cooper";
            break;
          case 4:        
            name = "XxX_Gamer69420_XxX";
            break;
        } 
        System.out.println("\n===========================================================\nYou encountered a wild leprechaun! Their name is\n" + name + " and they want to steal your money!\n"); // Intro statement displayed to user
        Scanner scn = new Scanner(System.in); // Initializes the scanner tool to enable the collection user input
        Deck deck = new Deck(); // Initializes the deck for the game
        PlayerHand hand = new PlayerHand(); // Initializes the user's hand
        dealHand(deck, hand); // Deals out the 5 cards to the user
        delay(1000);

        // BET SETUP
        // The betting amount that the user will change (that enables decimals amounts)
        if (bet == 0) { //First round bet
          System.out.print("How many GOLDEN COINS ($) do you want to bet?: ");
          bet = scn.nextDouble();
        }
        else { //Encore bets
          System.out.print("How many GOLDEN COINS ($) do you want to add to your bet of "+bet+"?: ");
          bet = bet + scn.nextDouble(); // Takes user input for the bet amount to add to previous winnings
          delay(1000);
          System.out.println("You are now playing with a bet of: "+bet);
          delay(1000);
        }
        
        // HAND PRINT: prints out user's hand
        System.out.println("\n===========================================================\nYOUR HAND:");
        hand.printHand();

        // CARD DISCARD
        int numOfDiscard = -1; // The number of cards that the user wishes to discard. The -1 enables the while-loop to run
        while (numOfDiscard < 0 || numOfDiscard > 5) { // Dummy-proof while loop to ensure that the number of cards to discard is not below 0 (because negative card discard isn't valid) and not above 5 (because the hand only consists of 5 cards)
          delay(500);
          System.out.print(
              "\nEnter the number of cards you'd like to discard and redraw\n(enter 0 if you're happy with your hand): ");
          numOfDiscard = scn.nextInt();
        }

        // CARD DRAW
        if (numOfDiscard != 0) { // If the user wants to keep all cards, then there is no need to discard and redraw cards into their hand
          cardDiscardDraw(deck, hand, numOfDiscard); // Card discard and draw method is run
        }
        delay(1000);

        // FINAL HAND PRINT
        System.out.println("\n===========================================================\n\nYOUR FINAL HAND:");
        hand.printHand();

        // CHECKING FOR WINNING HANDS
        bet = checkForWins(hand, bet); // Runs the method that checks for winning hands and displays the reward (if there is one)

        delay(1000);
        
        // PLAY AGAIN?
        String playAgainResponse = "";
        while (!playAgainResponse.equals("y") && !playAgainResponse.equals("n")) { // Dummy-proof while-loop that ensures the user's answer is a valid response to the 'play again' question.
          System.out.print("\nDo you want to play again?\nType 'y' to play again or 'n' to quit/cash out: ");
          playAgainResponse = scn.next();
        }
        if (playAgainResponse.equals("n")) { // If the answer to play again is 'no', the first while-loop is broken out of, and the game ends
          System.out.println("Thank you for playing LEPRECHAUN POKER!\nYou take your stash of "+bet+" GOLDEN COINS as winnings to feed your little tree family.");
          playAgain = false;
        }
        delay(1000);
      } catch (InputMismatchException error) { // Catches any last errors that the dumm-proofing while-loops couldn't catch
        System.out.println("\nInvalid Input! Rerun the game!\n");
      }
    }
  }

  // DELAY METHOD: Enables any other method to use a delay without the need to implement a 'try-and-catch' and excessive lines of code
  public static void delay(int time) {
    try {
      Thread.sleep(time);
    } catch (InterruptedException error) {
      Thread.currentThread().interrupt();
    }
  }

  // HAND DEALING METHOD: Deals the first 5 cards of the shuffled deck to the player
  public static void dealHand(Deck deck, PlayerHand hand) {
    for (int i=0; i<5; i++) { //Runs loop 5 times for 5 cards
      hand.add(deck.draw());
    }
  }

  // CARD DISCARDING AD DRAWING METHOD: Enables user-decided discard and a drawing
  // of another to replace the previous card
  public static void cardDiscardDraw(Deck deck, PlayerHand hand, int numCards) {
    Scanner scn = new Scanner(System.in);
    for (int i = 0; i < numCards; i++) { // Loops for as many cards the user wants to discard
      int cardIndex = 0;
      while (cardIndex > hand.getSize() || cardIndex < 1) { // Dummy-proof while-loop that ensures that the card position the user inputs is valid
        System.out.print("\nEnter the position of a card you would like to discard: ");
        cardIndex = scn.nextInt();
      }
      deck.add(hand.getCard(cardIndex - 1)); // Adds the discarded card back into the deck
      hand.remove(cardIndex - 1); // List indexes start at 0, but the typical decimal counting system begins at 1, 'cardIndex-1' ensures that the proper card is discarded
      System.out.println("\n===========================================================\nYOUR HAND:");
      hand.printHand();
    }
    deck.shuffle(); // Shuffles deck after the discard(s)
    System.out.println("The wild leprechaun is dealing out your new cards!");
    delay(1000);
    for (int j = 0; j < numCards; j++) {
      hand.add(deck.draw()); // Redraws the number of cards needed to replace the discarded cards
    }
    deck.shuffle(); // Shuffles to prepare for next round
  }

  // CHECKING FOR WINS AND REWARDS METHOD: Checks for winning hands and calculates the respective reward
  public static double checkForWins(PlayerHand hand, double bet) {
    if (hand.royalFlushCheck() == true) { // Runs the method that checks for a royal flush
      bet *= 20;
      System.out.println("\nYou got a ROYAL FLUSH - WHAT THE HECK!!!\nYou earned " + bet + " GOLDEN COINS!\n");
    }

    else if (hand.straightFlush() == true) { // Runs the method that checks for a straight flush
      bet *= 10.5;
      System.out.println("\nOH MY GOSH YOU JUST GOT A STRAIGHT FLUSH!\nYou earned " + bet + " golden coins!\n");
    }

    else if (hand.fullHouseCheck() == true) {
      bet *= 7.5;
      System.out.println("\nNICE! You got a Full House!\nYou earned " + bet + " golden coins!\n");
    }

    else if (hand.flushCheck() == true) { // Runs the method that checks for a flush
      bet *= 6;
      System.out.println("\nYou got a Flush!\nYou earned " + bet + " golden coins!\n");
    }

      
    else if (hand.straightCheck() == true) { // Runs the method that checks for a straight
      bet *= 4.5;
      System.out.println("\nYou got a Straight!\nYou earned " + bet + " golden coins!\n");
    }

    else if (hand.twoPairCheck() == true) { // Runs the method that checks for 2 pairs
          bet *= 1.5;
          System.out.println("You got 2 Pairs! double trouble!\n\nYou earned " + bet + " golden coins!\n");
    }
      
    else if (hand.ofAKindCheck()>0){
      int ofAKind = hand.ofAKindCheck(); // Runs the method that checks for a pair, a three-of-a-kind, a four-of-a-kind, and a full house
      switch (ofAKind) { //this switch handles the distribution of money depending on what type of winning hand they have
        case 2:
          bet *= 1;
          System.out.println("\nYou got a Pair!\nYou get to keep your initial " + bet + " golden coins!\n");
          break;
        case 3:
          bet *= 3;
          System.out.println("\nYou got Three of a Kind!\nYou earned " + bet + " golden coins!\n");
          break;
          
        case 4:
          bet *= 9;
          System.out.println("\nYou got Four of a Kind! Just like my lucky shamrock has leaves..!\nYou earned " + bet + " golden coins!\n");
          break;
      }
    }

    else { // If the user does not have a winning hand, they lose their bet and the game
      bet *= 0;
      System.out.println("You lost your golden coins! You suck MUAHAHAHA!\n");
    }
    return bet;
  }
}