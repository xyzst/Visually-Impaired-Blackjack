package tnc16_coh5_d_r273.blind_blackjack;

/**
 * Created by Valar-Morghulis on 3/21/17.
 */

public class Settings {
    byte decks,
         splits,
         burns,
         shuffleTimes;

    float startCash,
          tableMin,
          tableMax;

    float bjPay,
          surrenderPay,
          insurancePay,
          winPay;

    boolean doubleDownAfterSplit,
            surrender,
            insurance,
            stand17soft,
            aceResplit,
            dd1011;

    private boolean[] bools = {true , false};
    private byte[] bytes = {6, 8};

    //TODO write doc
    Settings() {
        bjPay = 1.5f;
        surrenderPay = -0.5f;
        insurancePay = 2f;
        winPay = 1f;
        decks = 1;
        splits = 1;
        burns = (byte)(5.0*Math.random());
        shuffleTimes = 3;
        startCash = 10000f;
        tableMin = 5f;
        tableMax = 1000f;
        doubleDownAfterSplit = true;
        surrender = true;
        insurance = true;
        stand17soft = true;
        aceResplit = true;
        dd1011 = true;
    }

    // TODO write doc
    private boolean sometimes(boolean[] j) {
        short i = (short)Math.round(Math.random());
        if (i == 1) {
            return j[0];
        }
        else {
            return j[1];
        }
    }

    //TODO write doc
    private byte sometimes(byte[] j) {
        short x = (short)Math.round(Math.random());
        if (x == 1) {
            return j[0];
        }
        else {
            return j[1];
        }
    }

    // TODO write doc
    void vegasStrip() {
        insurance = false;
        decks = 4;
        stand17soft = false;
        dd1011 = false;
    }

    // TODO write doc
    void downtownVegas() {
        dd1011 = false;
        surrender = false;
        doubleDownAfterSplit = sometimes(bools);
        aceResplit = sometimes(bools);
    }

    //TODO write doc
    void rino() {
        insurance = sometimes(bools);
        surrender = false;
        aceResplit = false;
    }

    //TODO write doc
    void atlanticCity() {
        decks=sometimes(bytes);
        stand17soft = false;
        dd1011 = false;
        surrender = false;
        aceResplit = sometimes(bools);
    }
}