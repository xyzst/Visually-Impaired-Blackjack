package tnc16_coh5_d_r273.blind_blackjack;

/**
 *  An object of type Hand represents a hand of cards.  The maximum number of
 *  cards in the hand can be specified in the constructor, but by default
 *  is 5.  A utility function is provided for computing the value of the
 *  hand in the game of Blackjack.
 *
 *  Source code written for this class acknowledged in the @author tag however Java
 *  documentation is written by Darren Rambaud (d_r273)
 *
 *  @author http://math.hws.edu/eck/cs124/javanotes4/c5/ex-5-5-answer.html
 */

import java.util.ArrayList;

public class Hand {

    private ArrayList<Card> hand;   // The cards in the hand.

    /**
     * Hand() is the constructor of the class and initializes the private declared ArrayList
     */
    public Hand() {
        // Create a Hand object that is initially empty.
        hand = new ArrayList<>();
    }

    /**
     * clear() upon invocation will call the .clear() method and discard all Card objects
     */
    public void clear() {
        // Discard all the cards from the hand.
        hand.clear();
    }

    /**
     * addCard() accepts a single Card object and will add the Card to the Hand object's
     * ArrayList
     * @param c a Card object
     */
    public void addCard(Card c) {
        // Add the card c to the hand.  c should be non-null.  (If c is
        // null, nothing is added to the hand.)
        if (c != null)
            hand.add(c);
    }

    /**
     * removeCard() will remove a specific Card object, assuming it exists
     * @param c a Card object
     */
    public void removeCard(Card c) {
        // If the specified card is in the hand, it is removed.
        hand.remove(c);
    }

    /**
     * removeCard() will remove a card based upon the position in the ArrayList.
     * To ensure there are no out of bounds exceptions, the integer is bounds checked before
     * attempting to access the array at that specified position
     * @param position an integer
     */
    public void removeCard(int position) {
        // If the specified position is a valid position in the hand,
        // then the card in that position is removed.
        if (position >= 0 && position < hand.size())
            hand.remove(position);
    }

    /**
     * getCardCount() will return the number of Card objects in the current Hand object
     * @return an integer
     */
    public int getCardCount() {
        // Return the number of cards in the hand.
        return hand.size();
    }

    /**
     * getCard() returns a Card object at the specified position. Bounds checking occurs here
      * @param position, an integer
     * @return a Card object
     */
    public Card getCard(int position) {
        // Get the card from the hand in given position, where positions
        // are numbered starting from 0.  If the specified position is
        // not the position number of a card in the hand, then null
        // is returned.
        if (position >= 0 && position < hand.size())
            return hand.get(position);
        else
            return null;
    }

    /**
     * sortBySuit will sort the Hand by grouping similar suits together and be sorted in
     * descending order from highest to lowest (an ace is the lowest value)
     */
    public void sortBySuit() {
        ArrayList<Card> newHand = new ArrayList<>();
        while (hand.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Card c = hand.get(0);  // Minimal card.
            for (int i = 1; i < hand.size(); i++) {
                Card c1 = hand.get(i);
                if ( c1.getSuit() < c.getSuit() ||
                        (c1.getSuit() == c.getSuit() && c1.getValue() < c.getValue()) ) {
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }

    /**
     * sortByValue will sort the hand by value, then if there are collisions between the value
     * then they are sorted by suit.
     */
    public void sortByValue() {
        ArrayList<Card> newHand = new ArrayList<>();
        while (hand.size() > 0) {
            int pos = 0;  // Position of minimal card.
            Card c = hand.get(0);  // Minimal card.
            for (int i = 1; i < hand.size(); i++) {
                Card c1 = hand.get(i);
                if (c1.getValue() < c.getValue() ||
                        (c1.getValue() == c.getValue() && c1.getSuit() < c.getSuit())) {
                    pos = i;
                    c = c1;
                }
            }
            hand.remove(pos);
            newHand.add(c);
        }
        hand = newHand;
    }
}