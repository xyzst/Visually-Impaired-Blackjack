package tnc16_coh5_d_r273.blind_blackjack;

/**
 * Created by Valar-Morghulis on 3/21/17.
 *
 * An object of class card represents one of the 52 cards in a
 * standard deck of playing cards.  Each card has a suit and
 * a value.
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

    public Card(int theValue, int theSuit) {
        // Construct a card with the specified value and suit.
        // Value must be between 1 and 13.  Suit must be between
        // 0 and 3.  If the parameters are outside these ranges,
        // the constructed card object will be invalid.
        value = theValue;
        suit = theSuit;
    }

    public int getSuit() {
        // Return the int that codes for this card's suit.
        return suit;
    }

    public int getValue() {
        // Return the int that codes for this card's value.
        return value;
    }

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

    public String getValueAsString() {
        // Return a String representing the card's value.
        // If the card's value is invalid, "??" is returned.
        switch ( value ) {
            case 1:   return "Ace";
            case 2:   return "Two";
            case 3:   return "Three";
            case 4:   return "Four";
            case 5:   return "Five";
            case 6:   return "Six";
            case 7:   return "Seven";
            case 8:   return "Eight";
            case 9:   return "Nine";
            case 10:  return "Ten";
            case 11:  return "Jack";
            case 12:  return "Queen";
            case 13:  return "King";
            default:  return "??";
        }
    }

    public String toString() {
        // Return a String representation of this card, such as
        // "10 of Hearts" or "Queen of Spades".
        return getValueAsString() + "_of_" + getSuitAsString();
    }


} // end class Card