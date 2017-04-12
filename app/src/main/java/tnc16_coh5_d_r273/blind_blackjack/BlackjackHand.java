package tnc16_coh5_d_r273.blind_blackjack;

/**
 * class BlackjackHand is a representation of an individuals hand (eg, dealer or user)
 * and fundamentally is a subclass of class Hand.
 *
 * The author/source of this class is acknowledged with @author however the java
 * documentation written herein is written by myself, Darren Rambaud (d_r273). Minor
 * changes to the source code were made however were more for style as opposed to modifying
 * the logic.
 *
 * @author http://math.hws.edu/eck/cs124/javanotes4/c5/ex-5-5-answer.html
 */

public class BlackjackHand extends Hand {
    /**
     * getBlackjackValue returns the int value of the BlackjackHand object
     * @return an int, representing the person's/object's hand value (max == 21)
     */
    public int getBlackjackValue() {

        int val;      // The value computed for the hand.
        boolean ace;  // This will be set to true if the
                      // hand contains an ace.
        int cards;    // Number of cards in the hand.

        val = 0;
        ace = false;
        cards = getCardCount();

        for ( int i = 0;  i < cards;  i++ ) {
            // Add the value of the i-th card in the hand.
            Card card;    // The i-th card;
            int cardVal;  // The blackjack value of the i-th card.
            card = getCard(i);
            cardVal = card.getValue();  // The normal value, 1 to 13.
            if (cardVal > 10) {
                cardVal = 10;   // For a Jack, Queen, or King.
            }
            if (cardVal == 1) {
                ace = true;     // There is at least one ace.
            }
            val += cardVal;
        }

        // Now, val is the value of the hand, counting any ace as 1.
        // If there is an ace, and if changing its value from 1 to
        // 11 would leave the score less than or equal to 21,
        // then do so by adding the extra 10 points to val.

        if ( ace  &&  val + 10 <= 21 )
            val += 10;

        return val;

    }  // end getBlackjackValue()
}
