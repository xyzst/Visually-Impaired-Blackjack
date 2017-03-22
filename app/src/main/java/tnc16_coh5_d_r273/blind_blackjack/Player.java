package tnc16_coh5_d_r273.blind_blackjack;

import java.util.ArrayList;

/**
 * Created by Valar-Morghulis on 3/21/17.
 */

public class Player {
    private final String name;
    private Purse purse;
    private ArrayList<BlackJackHand> hands = new ArrayList<>();
    private boolean hasOptedForInsurance = false;
    private boolean isPlayerPlaying;
    private float rebet = 0;

    // TODO write doc
    Player(String name, float initialCurrency) {
        this.name = name;
        purse = new Purse(initialCurrency);
        isPlayerPlaying = true;
        addHand(new BlackJackHand(name));
    }

    //TODO write doc
    void clearHands() {
        hands.clear();
    }

    //TODO write doc
    void deposit(float amount) {
        purse.deposit(amount);
    }

    //TODO write doc
    void withdraw(float amount) {
        purse.withdraw(amount);
    }

    //TODO write doc
    void addHand(BlackJackHand hand) {
        hands.add(hand);
    }

    //TODO write doc
    void takeInsurance() {
        hasOptedForInsurance = true;
    }

    // TODO write doc
    String getName() {
        return name;
    }

    // TODO write doc
    float getBalance() {
        return purse.getBalance();
    }

    //TODO write doc
    boolean getHasOptedForInsurance() {
        return hasOptedForInsurance;
    }

    // TODO write doc
    float getRebet() {
        return rebet;
    }

    //TODO write doc
    float getInitialBet() {
        return hands.get(0).getBet().getBetAmount();
    }

    //TODO write doc
    boolean getIsPlayingPlaying() {
        return isPlayerPlaying;
    }

    //TODO write doc
    byte getNumberOfHands() {
        return (byte) hands.size();
    }

    //TODO write doc
    ArrayList<BlackJackHand> getHands() {
        return hands;
    }

    // TODO write doc
    BlackJackHand getHand(byte a) {
        return hands.get(a);
    }
}