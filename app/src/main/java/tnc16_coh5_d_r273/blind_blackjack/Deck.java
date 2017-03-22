package tnc16_coh5_d_r273.blind_blackjack;

import java.util.ArrayList;

/**
 * Created by Valar-Morghulis on 3/21/17.
 */

public class Deck {
    private ArrayList<Card> deck;
    static int numberOfDecks;
    static int shuffleTimes;

    //TODO write doc
    Deck(byte decks, byte burn, byte times) {
        deck = new ArrayList<>();
        numberOfDecks = decks;
        shuffleTimes = times;

        fillDeck();
        shuffleDeck();
        burn(burn);
    }

    //TODO write doc
    private void burn(byte a) {
        for (byte counter = 0; counter < a; ++counter) {
            deck.remove(0);
        }
    }

    //TODO write doc
    Card drawCard() {
        Card card = deck.get(0);
        deck.remove(0);
        return card;
    }

    // TODO write doc
    private Card popCard(short s) {
        Card card = deck.get(s);
        deck.remove(s);
        return card;
    }

    //TODO write doc
    void fillDeck() {
        // Fill the deck with 52 Cards, 4 different suits & 13 different cards in each suit.

        for (byte decks = 0; decks < numberOfDecks; ++decks) {
            for (byte suit = 0; suit < 4; ++suit) {
                for (byte value = 1; value < 14; ++value) {
                    deck.add(new Card(value, suit, true));
                }
            }
        }
    }

    //TODO write doc
    void shuffleDeck() {
        for (byte t = 0; t < shuffleTimes; ++t ) {
            for (short i = (short)(deck.size()-1); i > 0; --i ) {
                short rand = (short)(Math.random()*i); // Fetch a random Card in the range 0-i

                // Take care of case where indexes rand and i represent the same card.
                while (rand == i) {
                    rand = (short)(Math.random()*i);
                }

                Card iCard = popCard(i); // Pop the OLD Card.
                Card randCard = popCard(rand); //Pop the NEW random card.

                //Put back them in swapped places.
                deck.add(i-1,randCard);
                deck.add(rand,iCard);
            }
        }
    }

    //TODO write doc
    short cardsLeft() {
        return (short)deck.size();
    }
}
