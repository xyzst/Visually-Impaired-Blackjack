package tnc16_coh5_d_r273.blind_blackjack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Play extends AppCompatActivity {
    public static final String PREFS_NAME = "SharedBlackjackVars";
    private static final String TAG = "PlayActivity";

    int SECS_DELAY = 5000;
    int bet;
    int dealerScore;
    int userScore;
    int dealerCardNumber;
    int userCardNumber;
    double money;

    Deck deck;
    BlackjackHand userHand;
    BlackjackHand dealerHand;

    TextView dealerScoreTextView;
    TextView userScoreTextView;

    Button buttonHit;
    Button buttonStand;

    ArrayList<ImageView> dealerCardsImageViews = new ArrayList<>();
    ArrayList<ImageView> userCardsImageViews = new ArrayList<>();

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
        dealerCardNumber = 0;
        userCardNumber = 0;

        /* Begin initializing ImageViews, 10 refers to the number of ImgViews in the associated
         * activity_play.xml file. This could change if I could figure out how to dynamically
         * add ImageViews to the xml file
         */
        for (int i = 0; i < 10; ++i) {
            ImageView imgViewDealer = new ImageView(this);
            ImageView imgViewUser = new ImageView(this);

            dealerCardsImageViews.add(imgViewDealer);
            userCardsImageViews.add(imgViewUser);

            dealerCardsImageViews.set(i,
                    (ImageView) findViewById(getResources().getIdentifier("imageViewDealerCard"+i,
                            "id", getPackageName())));
            userCardsImageViews.set(i,
                    (ImageView) findViewById(getResources().getIdentifier("imageViewUserCard"+i,
                            "id", getPackageName())));
        }

        shuffleUpAndDeal();
    }

    private void displayAppropriateUserImageViewCard(String file, int index) {
        try {
            String filename = file.toLowerCase();
            InputStream stream = getAssets().open(filename);
            Drawable d = Drawable.createFromStream(stream,null);
            userCardsImageViews.get(index).setImageDrawable(d);
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    private void displayAppropriateDealerImageViewCard(String file, int index) {
        try {
            String filename = file.toLowerCase();
            InputStream stream = getAssets().open(filename);
            Drawable d = Drawable.createFromStream(stream,null);
            dealerCardsImageViews.get(index).setImageDrawable(d);
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    public void shuffleUpAndDeal() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();

        deck.shuffle();
        dealerHand.addCard(deck.dealCard());
        userHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
        userHand.addCard(deck.dealCard());

        displayAppropriateDealerImageViewCard("face_down.png", dealerCardNumber++);
        displayAppropriateUserImageViewCard(userHand.getCard(userCardNumber).toString()+".png",
                userCardNumber++);
        displayAppropriateDealerImageViewCard(dealerHand.getCard(dealerCardNumber).toString()+".png",
                dealerCardNumber++);
        displayAppropriateUserImageViewCard(userHand.getCard(userCardNumber).toString()+".png",
                userCardNumber++);

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
        else if (dealerScore == 21) {
            Intent lose = new Intent(this, PlaceWagerActivity.class);
            lose.putExtra("RESULT", money - bet);
            if ((money - bet) == 0) {
                edit.putBoolean("hasReachedZeroFunds", true);
                edit.apply();
            }
            //  TODO: implement a delay
            startActivity(lose);
        }

        /*  If neither player has Blackjack, play the game. */

        buttonHit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userHand.addCard(deck.dealCard());
                userScore = userHand.getBlackjackValue();
                userScoreTextView.setText("You @ ".concat(Integer.toString(userScore)));
                displayAppropriateUserImageViewCard(userHand.getCard(userCardNumber).toString()+".png",
                        userCardNumber++);
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
        // TODO: grey out the hit and stand buttons ?? make them inaccessible or just undraw them?

        while (dealerHand.getBlackjackValue() <= 16) {
            Card newCard = deck.dealCard();
            dealerHand.addCard(newCard);
            dealerScore = dealerHand.getBlackjackValue();
            dealerScoreTextView.setText("Dealer @ ".concat(Integer.toString(dealerScore)));
            //TODO: implement delay
            displayAppropriateDealerImageViewCard(dealerHand.getCard(dealerCardNumber).toString()+".png",
                    dealerCardNumber++); // FIXME: May access out of bounds if number of cards exceeds 10, fix with dynamic ImageView on XML, use RecyclerView??
            Log.i(TAG, "Dealer Score == "+dealerScore+" User Score == " +userScore);

            if (dealerScore > 21) {
                Intent win = new Intent(this, PlaceWagerActivity.class);
                win.putExtra("RESULT", money + bet);
                //TODO: implement delay
                startActivity(win);
            }
        }

        if (dealerScore == userScore) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent tie = new Intent(Play.this, PlaceWagerActivity.class);
                    tie.putExtra("RESULT", money);
                    // TODO implement delay
                    startActivity(tie);
                }
            }, SECS_DELAY);
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
