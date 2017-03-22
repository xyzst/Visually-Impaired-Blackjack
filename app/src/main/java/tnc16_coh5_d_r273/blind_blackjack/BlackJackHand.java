package tnc16_coh5_d_r273.blind_blackjack;

/**
 * Created by Valar-Morghulis on 3/21/17.
 */

public class BlackJackHand extends Hand {
    private boolean isBusted = false,
                    hasBlackjack = false,
                    didDoubleDown = false;

    private byte hardValue,
                 softValue;

    // TODO: write documentation
    BlackJackHand(String name) {
        super(name);
    }

    // TODO: write documentation
    BlackJackHand(String name, Card card, float betAmount) {
        super(name, card, betAmount);
    }

    // TODO: write documentation
    void update() {
        hand.clear();
        isBusted = false;
        hasBlackjack = false;
        didDoubleDown = false;
        hasAce = false;

        hardValue = 0;
        softValue = 0;
    }

    //TODO write doc
    void takeDoubleDown() {
        didDoubleDown = true;
    }

    // TODO write doc
    void ascertainIfBusted() {
        if (hardValue > 21) {
            isBusted = true;
        }
    }

    // TODO write doc
    void ascertainIfBlackjackObtained() {
        if (hardValue == 21) {
            hasBlackjack = true;
        }
    }

    // TODO write doc
    boolean isHandSplittable(boolean resplitAce) {
        if (getCardCount() > 2) {
            return false;
        }

        byte former = hand.get((byte)0).getValue();
        byte latter = hand.get((byte)1).getValue();

        if (former == 1 && latter == 1) {
            if (resplitAce) {
                return true;
            }
            else {
                return false;
            }
        }

        return (former == latter);
    }

    // TODO write doc
    void addCard(Card a) {
        if (a != null) {
            hand.add(a);
            calculateBlackJackValue();
        }
    }

    //TODO write doc
    private void calculateBlackJackValue() {
        hardValue = 0;

        boolean isAtLeastOneFaceCard = false;

        for (Card card : hand) {
            int valueOfCard = card.getValue();

            if (valueOfCard > 10) {
                isAtLeastOneFaceCard = true;
                valueOfCard = 10;
            }
            else if (valueOfCard == 1) {
                hasAce = true;
            }

            hardValue += valueOfCard;
        }

        if (hasAce && hardValue <= 11) {
            softValue = (byte)(hardValue + 10);
            if (isAtLeastOneFaceCard) {
                hardValue = softValue;
            }
        }
        else {
            softValue = 0;
        }
    }

    //TODO write doc
    boolean getHasBlackjack() {
        return hasBlackjack;
    }

    //TODO write doc
    boolean getIsBusted() {
        return isBusted;
    }

    // TODO write doc
    boolean getDoubleDown() {
        return didDoubleDown;
    }

    //TODO write doc
    byte getHardBlackJackValue() {
        return hardValue;
    }

    //TODO write doc
    byte getSoftBlackJackValue() {
        return softValue;
    }
}
