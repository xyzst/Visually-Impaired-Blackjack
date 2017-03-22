package tnc16_coh5_d_r273.blind_blackjack;

/**
 * Created by Valar-Morghulis on 3/21/17.
 */

public class Purse {
    private float balance;

    // TODO write doc
    public Purse() {
        balance = 0;
    }

    //TODO write doc
    public Purse(float initial) {
        balance = initial;
    }

    //TODO write doc
    public void deposit(float amount) {
        balance += amount;
    }

    //TODO write doc
    public void withdraw(float amount) {
        balance -= amount;
    }

    //TODO write doc
    public float getBalance() {
        return balance;
    }
}