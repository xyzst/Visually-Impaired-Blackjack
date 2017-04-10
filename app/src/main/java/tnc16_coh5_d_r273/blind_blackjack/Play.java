package tnc16_coh5_d_r273.blind_blackjack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Play extends AppCompatActivity {
    public static final String PREFS_NAME = "SharedBlackjackVars";

    int bet;
    int dealerScore;
    int userScore;
    double money;

    Deck deck;
    BlackjackHand userHand;
    BlackjackHand dealerHand;

    TextView dealerScoreTextView;
    TextView userScoreTextView;

    ArrayList<ImageView> dealerCardsImageViews;
    ArrayList<ImageView> userCardsImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        dealerScoreTextView = (TextView) findViewById(R.id.textViewDealerScore);
        userScoreTextView = (TextView) findViewById(R.id.textViewUserScore);
        deck = new Deck();
        userHand = new BlackjackHand();
        dealerHand = new BlackjackHand();
        Bundle bundle = getIntent().getExtras();
        bet = bundle.getInt("BET_AMOUNT"); // passed from PlaceWagerActivity ..
        money = bundle.getDouble("USER_MONEY");

        shuffleUpAndDeal();

//    public void play(View view) {
//        // set new view to game view/activity_field
//
//        Intent intent = new Intent(this, Play.class);
//        startActivity(intent);
//
//    }
//
//    public void dealerDrawingCards() {
//        Card newCard = deck.dealCard();
//        // update activity field with dealer cards
//        // uppdate dealer total
//        dealerHand.addCard(newCard);
//        if (dealerHand.getBlackjackValue() > 21) {
//            // Dealer has busted, update dealer, show a toast notif,
//            // return to betting activity
//        }
//    }
//
//    public void userBetting() {
//        // if money == 0, then end session, reset money value, go back to activity_main
//        // set view to betting
//        // have buttons/gestures to set bet
//        // ALWAYS if(bet > money), should not be able to get bet greater than current money
//        // onClick/onGesture accepted bet then proceed to next activity
//        // new thread with play();
//    }
//
//    public void userHitOrStand() {
//        // load hit or stand activity
//        // load buttons (hit or stand)
//        // capture gestures for hit or stand
//        // onClick of hitbutton or recognizedGesture for hit, add new card, recalculate user total
//        // and determine if user has bust, set the hasBust flag appropriately
//        // use this to return to the field activity (http://stackoverflow.com/questions/14848590/return-back-to-mainactivity-from-another-activity)
//        // this will apparently destroy the hit_stand_activity
//    }
//        deck = new Deck();
//        dealerHand = new BlackjackHand();
//        userHand = new BlackjackHand();
//
//        deck.shuffle();
//        dealerHand.addCard(deck.dealCard()); // should be delt facedown
//        userHand.addCard(deck.dealCard()); // alternate
//        dealerHand.addCard(deck.dealCard()); // faceup
//        userHand.addCard(deck.dealCard()); // last card dealt to user
//
//        // update view with cards^^
//
//        // check if either dealer and/or user has blackjack

//
//        // TODO implement ability to split cards
//
//        /*  If neither player has Blackjack, play the game.  First the user
//              gets a chance to draw cards (i.e., to "Hit").  The while loop ends
//              when the user chooses to "Stand".  If the user goes over 21,
//              the user loses immediately.
//        */
//
//        // update player/dealer total on view using userHand.getBlackjackValue() & dealerHand.getBlackJackValue()
//        // while user !hasBust && !stand ( // new Intent --> call hitOrStand() )
//        // figure out a way to update the view dynamically
//
//        /* At this point the user has Stood with 21 or less.  Now, it's
//             the dealer's chance to draw.  Dealer draws cards until the dealer's
//             total is > 16.  If dealer goes over 21, the dealer loses.
//
//         */
//
//        /*
//          while dealerHand.getBlackValue <= 16
//            dealerDrawingCards
//
//         */
//
//        /*
//        at this point, both dealer and user have <=21, compare result..
//
//         */
//        if (dealerHand.getBlackjackValue() == userHand.getBlackjackValue()) {
//            // TIE, push back money to user, no loss
//            // display toast notif
//            // call userBetting, switch activities
//        }
//        else if (dealerHand.getBlackjackValue() > userHand.getBlackjackValue()) {
//            // LOSS, dealer wins, adjust user's money
//            // display toast notif
//            // call userBetting/switch activ
//        }
//        else {
//            // WIN, user wins, money = money + bet
//            // display toast notif
//            // call userBetting/switch activ
//        }
}

    public void shuffleUpAndDeal() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        deck.shuffle();
        dealerHand.addCard(deck.dealCard());
        userHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
        userHand.addCard(deck.dealCard());
        dealerScore = /*dealerHand.getBlackjackValue(); TODO: REMOVE THIS COMMENT BLC*/ 21;
        userScore = /*userHand.getBlackjackValue();TODO: REMOVE THIS COMMENT BLC*/  20;
        dealerScoreTextView.setText("Dealer @ ".concat(Integer.toString(dealerScore)));
        userScoreTextView.setText("You @ ".concat(Integer.toString(userScore)));

        if (dealerScore == 21 && userScore == 21) { // TODO: passed test
            Intent tie = new Intent(this, PlaceWagerActivity.class);
            tie.putExtra("RESULT", money);
            startActivity(tie);
            // TODO: implement a delay to allow for a person to confirm
        }
        else if (dealerScore != 21 && userScore == 21) {
            Intent win = new Intent(this, PlaceWagerActivity.class);
            win.putExtra("RESULT", money + (bet * 1.5));
            startActivity(win);
        }
        else if (dealerScore == 21 && userScore != 21) {
            Intent lose = new Intent(this, PlaceWagerActivity.class);
            lose.putExtra("RESULT", money - bet);
            if ((money - bet) == 0) {
                edit.putBoolean("hasReachedZeroFunds", true);
                edit.apply();
            }
            startActivity(lose);
        }
    }
}
