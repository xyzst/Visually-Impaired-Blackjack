package tnc16_coh5_d_r273.blind_blackjack;

import java.util.ArrayList;

/**
 * Created by Valar-Morghulis on 3/21/17.
 */

public class Hand {
    ArrayList<Card> hand;

    String perspectiveOf;
    State state;
    Bet bet;

    boolean isPlaying = true,
            toBePaid = true,
            hasAce = false;

    // TODO: write documentation
    Hand(String name) {
        perspectiveOf = name;
        hand = new ArrayList<>();
        bet = new Bet();
        setState(State.PLAYING);
    }

    // TODO: write documentation
    Hand(String name, Card card, float betAmount) {
        perspectiveOf = name;
        hand = new ArrayList<>();
        addCard(card);
        bet = new Bet(betAmount);
        setState(State.PLAYING);
    }

    // TODO: write documentation
    void update() {
        hand.removeAll(hand);
    }

    // TODO: write documentation
    void addCard(Card card) {
        if (card != null) {
            hand.add(card);
        }
    }

    // TODO: write documentation
    void removeCard(byte a) {
        hand.remove(a);
    }

    // TODO: write documentation
    Card getCard(byte a) {
        return hand.get(a);
    }

    // TODO: write doc
    Card popCard(byte a) {
        Card card = hand.get(a);
        hand.remove(a);
        return card;
    }

    // TODO: write doc
    void setState (State state) {
        this.state = state;
    }

    // TODO: write doc
    void setToBePaid (boolean a) {
        toBePaid = a;
    }

    // TODO: write doc
    void setIsPlaying (boolean a) {
        isPlaying = a;
    }

    // TODO: write doc
    void increaseBetAmountBy(float stake) {
        bet.increaseBetAmountByStake(stake);
    }

    // TODO: write doc
    void clearBet() {
        bet.clear();
    }

    // TODO: write doc
    String getPerspectiveOf() {
        return perspectiveOf;
    }

    // TODO: write doc
    State getState() {
        return state;
    }

    //TODO: write doc
    boolean getIsPlaying() {
        return isPlaying;
    }

    // TODO: write doc
    boolean getToBePaid() {
        return toBePaid;
    }

    // TODO: write doc
    Bet getBet() {
        return bet;
    }

    // TODO: write doc
    byte getCardCount() {
        return (byte)hand.size();
    }

    //TODO: write doc
    boolean isEmpty() {
        return hand.isEmpty();
    }

    // TODO: write doc
    boolean getHasAce() {
        return hasAce;
    }
}