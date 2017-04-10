package tnc16_coh5_d_r273.blind_blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Play extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);


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
//        if (dealerHand.getBlackjackValue() == 21 && userHand.getBlackjackValue() == 21) {
//            //push, start over
//            //money value does not change
//            // call betting()
//
//        }
//        else if (dealerHand.getBlackjackValue() != 21 && userHand.getBlackjackValue() == 21) {
//            // user wins, money = bet * 1.5
//            // start over, call betting()
//        }
//        else if (dealerHand.getBlackjackValue() == 21 && userHand.getBlackjackValue() != 21) {
//            // dealer wins, money = money - bet
//            // start over, call betting()
//        }
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
}
