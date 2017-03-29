package tnc16_coh5_d_r273.blind_blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class Blackjack extends AppCompatActivity {
    private Settings settings;
    private Deck deck;
    private Purse purse;
    private BlackJackHand dealer;
    private Card dealerFaceDown;
    private Player player;
    private BlackJackHand currentPlayerHand;
    private boolean isBetting,
                    getNextAction;
    private ArrayList<Integer> splitHands;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        settings = new Settings();
        purse = new Purse(settings.startCash);
        deck = new Deck(settings.decks, settings.burns, settings.shuffleTimes);
        dealer = new BlackJackHand("DEALER");
        player = new Player("YOU", 1000);
        currentPlayerHand = player.getHand((byte)0);
        isBetting = true;

        setContentView(R.layout.activity_blackjack);

        Intent activity = new Intent(this, Dealer.class);
    }
}
