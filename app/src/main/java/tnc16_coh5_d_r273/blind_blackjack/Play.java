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
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Play extends AppCompatActivity {
    public static final String PREFS_NAME = "SharedBlackjackVars";
    public static final String TAG = "PlayActivity";

    private final int SECS_DELAY = 5000;
    private int bet;
    private int dealerScore;
    private int userScore;
    private int dealerCardNumber;
    private int userCardNumber;
    private double money;

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
            Drawable d = Drawable.createFromStream(stream, null);
            userCardsImageViews.get(index).setImageDrawable(d);
        } catch (IOException x) {
            x.printStackTrace();
        }
    }

    private void displayAppropriateDealerImageViewCard(String file, int index) {
        try {
            String filename = file.toLowerCase();
            InputStream stream = getAssets().open(filename);
            Drawable d = Drawable.createFromStream(stream, null);
            dealerCardsImageViews.get(index).setImageDrawable(d);
        } catch (IOException x) {
            x.printStackTrace();
        }
    }
// test
    public void shuffleUpAndDeal() {
        final SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS_NAME,
                MODE_PRIVATE);
        final SharedPreferences.Editor edit = pref.edit();

        deck.shuffle();
        dealerHand.addCard(deck.dealCard());
        userHand.addCard(deck.dealCard());
        userHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());

        displayAppropriateDealerImageViewCard("face_down.png", dealerCardNumber++);
        displayAppropriateUserImageViewCard(userHand.getCard(userCardNumber).toString()+".png",
                userCardNumber++);
        displayAppropriateDealerImageViewCard(dealerHand.getCard(dealerCardNumber).toString()+".png",
                dealerCardNumber++);
        displayAppropriateUserImageViewCard(userHand.getCard(userCardNumber).toString()+".png",
                userCardNumber++);

        dealerScore = dealerHand.getBlackjackValue();
        userScore = userHand.getBlackjackValue();
        dealerScoreTextView.setText("Shown: ".concat(dealerHand.getCard(1).getValueAsString()));
        userScoreTextView.setText("You @ ".concat(Integer.toString(userScore)));

        if (dealerScore == 21 && userScore == 21) {
            //TODO: implement audio feedback
            Toast.makeText(getApplicationContext(), "Dealer & User have Blackjack! Result = push",
                    Toast.LENGTH_LONG).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent tie = new Intent(Play.this, PlaceWagerActivity.class);
                    tie.putExtra("RESULT", money);
                    startActivity(tie);
                }
            }, SECS_DELAY);
        }
        else if (dealerScore != 21 && userScore == 21) {
            //TODO: implement audio feedback
            Toast.makeText(getApplicationContext(), "User has blackjack! Result = win",
                    Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent win = new Intent(Play.this, PlaceWagerActivity.class);
                    win.putExtra("RESULT", money + (bet * 1.5));
                    startActivity(win);
                }
            }, SECS_DELAY);
        }
        else if (dealerScore == 21) {
            //TODO: implement audio feedback
            Toast.makeText(getApplicationContext(), "Dealer has blackjack! Result = loss",
                    Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent lose = new Intent(Play.this, PlaceWagerActivity.class);
                    lose.putExtra("RESULT", money - bet);
                    if (Math.floor((money - bet)) == 0) {
                        edit.putBoolean("hasReachedZeroFunds", true);
                        edit.apply();
                    }
                    startActivity(lose);
                }
            }, SECS_DELAY);
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
                else if (userScore == 21) { // No need to query user to hit, already at 21!
                    userHasPressedStandContinueToDealCardsToDealer();
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
        final SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS_NAME,
                MODE_PRIVATE);
        final SharedPreferences.Editor edit = pref.edit();
        //TODO: implement audio feedback
        Toast.makeText(getApplicationContext(), "User has busted! Result = loss",
                Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent busted = new Intent(Play.this, PlaceWagerActivity.class);
                busted.putExtra("RESULT", money - bet);
                if (Math.floor((money - bet)) == 0) {
                    edit.putBoolean("hasReachedZeroFunds", true);
                    edit.apply();
                }
                startActivity(busted);
            }
        }, SECS_DELAY);
    }

    private void userHasPressedStandContinueToDealCardsToDealer() {
        // TODO: grey out the hit and stand buttons ?? make them inaccessible or just undraw them?
        final SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS_NAME,
                MODE_PRIVATE);
        final SharedPreferences.Editor edit = pref.edit();

        dealerScoreTextView.setText("Dealer @ ".concat(Integer.toString(dealerHand.getBlackjackValue())));
        displayAppropriateDealerImageViewCard(dealerHand.getCard(0).toString()+".png", 0);
        while (dealerHand.getBlackjackValue() <= 16) {
            Card newCard = deck.dealCard();
            dealerHand.addCard(newCard);
            dealerScore = dealerHand.getBlackjackValue();
            dealerScoreTextView.setText("Dealer @ ".concat(Integer.toString(dealerScore)));
            displayAppropriateDealerImageViewCard(dealerHand.getCard(dealerCardNumber).toString()+".png",
                            dealerCardNumber++); // FIXME: May access out of bounds if number of cards exceeds 10, fix with dynamic ImageView on XML, use RecyclerView??

            if (dealerScore > 21) {
                // TODO: implement audio feedback
                Toast.makeText(getApplicationContext(), "Dealer has busted! Result = win",
                        Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent win = new Intent(Play.this, PlaceWagerActivity.class);
                        win.putExtra("RESULT", money + bet);
                        startActivity(win);
                    }
                }, SECS_DELAY);
            }
        }

        if (dealerScore == userScore) {
            // TODO: implement sound feedback
            Toast.makeText(getApplicationContext(), "Dealer hand == User hand! Result == push",
                    Toast.LENGTH_SHORT).show();
             new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent tie = new Intent(Play.this, PlaceWagerActivity.class);
                    tie.putExtra("RESULT", money);
                    startActivity(tie);
                }
            }, SECS_DELAY);
        } else if ((dealerScore <= 21) && (dealerScore > userScore)) {
            // TODO: implement sound feedback
            Toast.makeText(getApplicationContext(), "Dealer hand > User hand! Result == loss",
                    Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Log.i(TAG, "Dealer Score == " + dealerScore + " User Score == " + userScore);
                    Intent loss = new Intent(Play.this, PlaceWagerActivity.class);
                    loss.putExtra("RESULT", money - bet);
                    if (Math.floor(money - bet) == 0) {
                        edit.putBoolean("hasReachedZeroFunds", true);
                        edit.apply();
                    }
                    startActivity(loss);
                }
            }, SECS_DELAY);
        } else {
            // TODO implement sound feedback
            Toast.makeText(getApplicationContext(), "User hand > dealer hand! Result == win",
                    Toast.LENGTH_SHORT).show();
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent win = new Intent(Play.this, PlaceWagerActivity.class);
                    win.putExtra("RESULT", money + bet);
                    startActivity(win);
                }
            }, SECS_DELAY);
        }
    }
}
