package tnc16_coh5_d_r273.blind_blackjack;

import java.util.ArrayList;

/**
 *   An object of type Deck represents an ordinary deck of 52 playing cards.
 *   The deck can be shuffled, and cards can be dealt from the deck.
 *
 *   Author of the source code for this class is acknowledged by the @author tag.
 *   Java documentation written for this class was made by Darren Rambaud (d_r273).
 *   Some changes to this class were made to allow for thread safety.
 *
 *   @author http://math.hws.edu/eck/cs124/javanotes4/c5/ex-5-5-answer.html
 */

public class Deck {

    private ArrayList<Card> deck;   // An array of 52 Cards, representing the deck.

    private int cardsUsed; // How many cards have been dealt from the deck.

    /**
     * Deck() is the constructor for the Deck class. Previously used a simple array
     * however this was replaced with an ArrayList to allow for thread safety
     */
    public Deck() {
        // Create an unshuffled deck of cards.
        deck = new ArrayList<>(52);
        int cardCt = 0; // How many cards have been created so far.
        for ( int suit = 0; suit <= 3; suit++ ) {
            for ( int value = 1; value <= 13; value++ ) {
                deck.add(cardCt, new Card(value, suit));
                cardCt++;
            }
        }
        cardsUsed = 0;
    }

    /**
     * shuffle() method uses the Math.rand() function to emulate shuffling of a deck
     */
    public void shuffle() {
        for ( int i = 51; i > 0; i-- ) {
            int rand = (int)(Math.random()*(i+1));
            Card temp = deck.get(i);
            deck.set(i, deck.get(rand));
            deck.set(rand, temp);
        }
        cardsUsed = 0;
    }

    /**
     * cardsLeft() method simply returns the number of cards that are available for play
     * @return an integer
     */
    public int cardsLeft() {
        // As cards are dealt from the deck, the number of cards left
        // decreases.  This function returns the number of cards that
        // are still left in the deck.
        return 52 - cardsUsed;
    }

    /**
     * dealCard() returns a Card object from a shuffled deck. Will automatically call shuffle()
     * method if cardsUsed == 52.
     * @return a Card object
     */
    public Card dealCard() {
        // Deals one card from the deck and returns it.
        if (cardsLeft() == 0)
            shuffle();
        cardsUsed++;
        return deck.get(cardsUsed - 1);
    }
} // end class Deck