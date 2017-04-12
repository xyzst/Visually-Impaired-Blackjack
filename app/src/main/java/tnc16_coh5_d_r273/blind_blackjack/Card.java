package tnc16_coh5_d_r273.blind_blackjack;

/**
 * An object of class card represents one of the 52 cards in a
 * standard deck of playing cards.  Each card has a suit and
 * a value.
 *
 * The code written in this class is attributed by the @author however java documentation
 * written herein is created by Darren Rambaud (d_r273)
 *
 * @author http://math.hws.edu/eck/cs124/javanotes4/c5/ex-5-5-answer.html
 */

public class Card {

    public final static int SPADES = 0,       // Codes for the 4 suits.
            HEARTS = 1,
            DIAMONDS = 2,
            CLUBS = 3;

    public final static int ACE = 1,          // Codes for the non-numeric cards.
            JACK = 11,        //   Cards 2 through 10 have their
            QUEEN = 12,       //   numerical values for their codes.
            KING = 13;

    private final int suit;   // The suit of this card, one of the constants
    //    SPADES, HEARTS, DIAMONDS, CLUBS.

    private final int value;  // The value of this card, from 1 to 11.

    /**
     * This method is the constructor of the class and sets the Card object's suit and value.
     *
     * @param theValue is a value between 1 and 13
     * @param theSuit is a value between 0 and 3
     */
    public Card(int theValue, int theSuit) {
        value = theValue;
        suit = theSuit;
    }

    /**
     * getSuit(), as implied by the name is a getter for the Card object's suit value
     * @return suit, an integer between 0 and 3
     */
    public int getSuit() {
        // Return the int that codes for this card's suit.
        return suit;
    }

    /**
     * getValue() is a getter for the Card's value
     * @return value, an integer between 1 and 13
     */
    public int getValue() {
        // Return the int that codes for this card's value.
        return value;
    }

    /**
     * getSuitAsString() returns the Card object's suit (assuming a face card)
     * as a String object
     * @return a String object representing the cards suit
     */
    public String getSuitAsString() {
        // Return a String representing the card's suit.
        // (If the card's suit is invalid, "??" is returned.)
        switch ( suit ) {
            case SPADES:   return "Spades";
            case HEARTS:   return "Hearts";
            case DIAMONDS: return "Diamonds";
            case CLUBS:    return "Clubs";
            default:       return "??";
        }
    }

    /**
     * getValueAsString() returns the Card object's value as a String (eg, 1 returns "Ace")
     * @return a String object
     */
    public String getValueAsString() {
        // Return a String representing the card's value.
        // If the card's value is invalid, "??" is returned.
        switch ( value ) {
            case ACE:   return "Ace";
            case 2:   return "Two";
            case 3:   return "Three";
            case 4:   return "Four";
            case 5:   return "Five";
            case 6:   return "Six";
            case 7:   return "Seven";
            case 8:   return "Eight";
            case 9:   return "Nine";
            case 10:  return "Ten";
            case JACK:  return "Jack";
            case QUEEN:  return "Queen";
            case KING:  return "King";
            default:  return "??";
        }
    }

    /**
     * toString() returns the Card objects String representation (eg, Ace_of_Clubs). This
     * method was rewritten to facilitate in finding the appropriate resource file to
     * display for the Card object
     * @return a String object
     */
    public String toString() {
        // Return a String representation of this card, such as
        // "10 of Hearts" or "Queen of Spades".
        return getValueAsString() + "_of_" + getSuitAsString();
    }
} // end class Card