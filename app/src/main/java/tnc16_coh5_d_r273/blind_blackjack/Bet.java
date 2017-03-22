package tnc16_coh5_d_r273.blind_blackjack;

/**
 * Created by Valar-Morghulis on 3/21/17.
 */

public class Bet {
    private float betAmount;

    // TODO: write documentation
    Bet() {
        betAmount = 0;
    }

    // TODO: write documentation
    Bet(float betAmount) {
        this.betAmount = betAmount;
    }

    // TODO: write documentation
    void increaseBetAmountByStake(float stake) {
        betAmount += stake;
    }

    // TODO: write documentation
    float getBetAmount() {
        return betAmount;
    }

    // TODO: write documentation
    void clear() {
        betAmount = 0;
    }
}