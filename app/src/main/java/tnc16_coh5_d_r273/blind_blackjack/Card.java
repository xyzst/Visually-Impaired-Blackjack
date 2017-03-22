package tnc16_coh5_d_r273.blind_blackjack;

/**
 * Created by Valar-Morghulis on 3/21/17.
 */

public class Card {
    private final byte suit;
    private final byte value;
    private Integer cardID;
    private boolean isFlippedUp;

    // TODO write doc
    Card(byte value, byte suit, boolean flip) {
        this.value = value;
        this.suit = suit;
        isFlippedUp = flip;
        setImage();
    }

    // TODO write doc
    void flip() {
        isFlippedUp = !isFlippedUp;
        setImage();
    }

    // TODO write doc
    // FIXME may need revision ???
    // TODO could probably clean up the code
    private void setImage() {
        cardID = null;

        if (isFlippedUp) {
            switch (suit) {
                case 0:
                    switch (value) {
                        case 1:
                            cardID=R.drawable.s1;
                            break;
                        case 2:
                            cardID=R.drawable.s2;
                            break;
                        case 3:
                            cardID=R.drawable.s3;
                            break;
                        case 4:
                            cardID=R.drawable.s4;
                            break;
                        case 5:
                            cardID=R.drawable.s5;
                            break;
                        case 6:
                            cardID=R.drawable.s6;
                            break;
                        case 7:
                            cardID=R.drawable.s7;
                            break;
                        case 8:
                            cardID=R.drawable.s8;
                            break;
                        case 9:
                            cardID=R.drawable.s9;
                            break;
                        case 10:
                            cardID=R.drawable.s10;
                            break;
                        case 11:
                            cardID=R.drawable.s11;
                            break;
                        case 12:
                            cardID=R.drawable.s12;
                            break;
                        case 13:
                            cardID=R.drawable.s13;
                            break;
                    }
                break;
                case 1:
                    switch (value) {
                        case 1:
                            cardID=R.drawable.h1;
                            break;
                        case 2:
                            cardID=R.drawable.h2;
                            break;
                        case 3:
                            cardID=R.drawable.h3;
                            break;
                        case 4:
                            cardID=R.drawable.h4;
                            break;
                        case 5:
                            cardID=R.drawable.h5;
                            break;
                        case 6:
                            cardID=R.drawable.h6;
                            break;
                        case 7:
                            cardID=R.drawable.h7;
                            break;
                        case 8:
                            cardID=R.drawable.h8;
                            break;
                        case 9:
                            cardID=R.drawable.h9;
                            break;
                        case 10:
                            cardID=R.drawable.h10;
                            break;
                        case 11:
                            cardID=R.drawable.h11;
                            break;
                        case 12:
                            cardID=R.drawable.h12;
                            break;
                        case 13:
                            cardID=R.drawable.h13;
                            break;
                    }
                break;
                case 2:
                    switch (value) {
                        case 1:
                            cardID=R.drawable.d1;
                            break;
                        case 2:
                            cardID=R.drawable.d2;
                            break;
                        case 3:
                            cardID=R.drawable.d3;
                            break;
                        case 4:
                            cardID=R.drawable.d4;
                            break;
                        case 5:
                            cardID=R.drawable.d5;
                            break;
                        case 6:
                            cardID=R.drawable.d6;
                            break;
                        case 7:
                            cardID=R.drawable.d7;
                            break;
                        case 8:
                            cardID=R.drawable.d8;
                            break;
                        case 9:
                            cardID=R.drawable.d9;
                            break;
                        case 10:
                            cardID=R.drawable.d10;
                            break;
                        case 11:
                            cardID=R.drawable.d11;
                            break;
                        case 12:
                            cardID=R.drawable.d12;
                            break;
                        case 13:
                            cardID=R.drawable.d13;
                            break;
                    }
                break;
                case 3:
                    switch (value) {
                        case 1:
                            cardID=R.drawable.c1;
                            break;
                        case 2:
                            cardID=R.drawable.c2;
                            break;
                        case 3:
                            cardID=R.drawable.c3;
                            break;
                        case 4:
                            cardID=R.drawable.c4;
                            break;
                        case 5:
                            cardID=R.drawable.c5;
                            break;
                        case 6:
                            cardID=R.drawable.c6;
                            break;
                        case 7:
                            cardID=R.drawable.c7;
                            break;
                        case 8:
                            cardID=R.drawable.c8;
                            break;
                        case 9:
                            cardID=R.drawable.c9;
                            break;
                        case 10:
                            cardID=R.drawable.c10;
                            break;
                        case 11:
                            cardID=R.drawable.c11;
                            break;
                        case 12:
                            cardID=R.drawable.c12;
                            break;
                        case 13:
                            cardID=R.drawable.c13;
                            break;
                    }
                break;
            }
        }
        else {
            cardID = R.drawable.back;
        }
    }

    //TODO write doc
    Integer getImageFromCardID() {
        return cardID;
    }

    // TODO write doc
    byte getSuit() {
        return suit;
    }

    // TODO write doc
    byte getValue() {
        return value;
    }

    // TODO write doc
    boolean getIsFlippedUp() {
        return isFlippedUp;
    }
}
