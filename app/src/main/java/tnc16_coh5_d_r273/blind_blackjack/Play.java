package tnc16_coh5_d_r273.blind_blackjack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Play extends AppCompatActivity {
    public static final String PREFS_NAME = "SharedBlackjackVars";
    private static final String TAG = "PlayActivity";

    int bet;
    int dealerScore;
    int userScore;
    double money;

    Deck deck;
    BlackjackHand userHand;
    BlackjackHand dealerHand;

    TextView dealerScoreTextView;
    TextView userScoreTextView;

    Button buttonHit;
    Button buttonStand;

    ArrayList<ImageView> dealerCardsImageViews;
    ArrayList<ImageView> userCardsImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        dealerScoreTextView = (TextView) findViewById(R.id.textViewDealerScore);
        userScoreTextView = (TextView) findViewById(R.id.textViewUserScore);
        buttonHit = (Button) findViewById(R.id.buttonHit);
        buttonStand = (Button) findViewById(R.id.buttonStand);

        deck = new Deck();
        userHand = new BlackjackHand();
        dealerHand = new BlackjackHand();
        Bundle bundle = getIntent().getExtras();
        bet = bundle.getInt("BET_AMOUNT"); // passed from PlaceWagerActivity ..
        money = bundle.getDouble("USER_MONEY");

        shuffleUpAndDeal();
}

    public void shuffleUpAndDeal() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        deck.shuffle();
        dealerHand.addCard(deck.dealCard());
        userHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
        userHand.addCard(deck.dealCard());
        dealerScore = dealerHand.getBlackjackValue();
        userScore = userHand.getBlackjackValue();
        dealerScoreTextView.setText("Dealer @ ".concat(Integer.toString(dealerScore)));
        userScoreTextView.setText("You @ ".concat(Integer.toString(userScore)));

        if (dealerScore == 21 && userScore == 21) {
            Intent tie = new Intent(this, PlaceWagerActivity.class);
            tie.putExtra("RESULT", money);
            startActivity(tie);
            // TODO: implement a delay to allow for a person to confirm
        }
        else if (dealerScore != 21 && userScore == 21) {
            Intent win = new Intent(this, PlaceWagerActivity.class);
            win.putExtra("RESULT", money + (bet * 1.5));
            startActivity(win);
            //TODO: implement a delay to allow
        }
        else if (dealerScore == 21 && userScore != 21) {
            Intent lose = new Intent(this, PlaceWagerActivity.class);
            lose.putExtra("RESULT", money - bet);
            if ((money - bet) == 0) {
                edit.putBoolean("hasReachedZeroFunds", true);
                edit.apply();
            }
            //  TODO: implement a delay
            startActivity(lose);
        }

        /*  If neither player has Blackjack, play the game.  First the user
              gets a chance to draw cards (i.e., to "Hit").  The while loop ends
              when the user chooses to "Stand".  If the user goes over 21,
              the user loses immediately.

              At this point, you do not need a loop, just draw buttons for hit/stand
              keep track
          */

        buttonHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userHand.addCard(deck.dealCard());
                userScore = userHand.getBlackjackValue();
                userScoreTextView.setText("You @ ".concat(Integer.toString(userScore)));
                //Update ImageViewer..
                if ((userScore) > 21) {
                    userHasBusted();
                }
            }
        });

        buttonStand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userHasPressedStandContinueToDealCardsToDealer();
            }
        });
    }

    private void userHasBusted() {
        Intent busted = new Intent(this, PlaceWagerActivity.class);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        busted.putExtra("RESULT", money - bet);
        if ((money - bet) == 0) {
            edit.putBoolean("hasReachedZeroFunds", true);
            edit.apply();
        }
        // TODO: implement delay
        startActivity(busted);
    }

    private void userHasPressedStandContinueToDealCardsToDealer() {
        while (dealerHand.getBlackjackValue() <= 16) {
            Card newCard = deck.dealCard();
            dealerHand.addCard(newCard);
            dealerScore = dealerHand.getBlackjackValue();
            dealerScoreTextView.setText("Dealer @ ".concat(Integer.toString(dealerScore)));
            //TODO: implement delay
            //TODO: implement imageview updates for dealer here

            Log.i(TAG, "Dealer Score == "+dealerScore+" User Score == " +userScore);

            if (dealerScore > 21) {
                Intent win = new Intent(this, PlaceWagerActivity.class);
                win.putExtra("RESULT", money + bet);
                //TODO: implement delay
                startActivity(win);
            }
        }

        if (dealerScore == userScore) {
            Intent tie = new Intent(this, PlaceWagerActivity.class);
            tie.putExtra("RESULT", money);
            // TODO implement delay
            startActivity(tie);
        } else if ((dealerScore <= 21) && dealerScore > userScore) {
            Log.i(TAG, "Dealer Score == " + dealerScore + " User Score == " + userScore);
            Intent loss = new Intent(this, PlaceWagerActivity.class);
            loss.putExtra("RESULT", money - bet);
            // TODO implement delay
            startActivity(loss);
        } else {
            Intent win = new Intent(this, PlaceWagerActivity.class);
            win.putExtra("RESULT", money + bet);
            // TODO implement delay
            startActivity(win);
        }
    }
}
