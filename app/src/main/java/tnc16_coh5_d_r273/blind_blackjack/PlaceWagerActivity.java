package tnc16_coh5_d_r273.blind_blackjack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * PlaceWagerActivity allows the user to place an appropriate bet/wager before proceeding
 * to play Blackjack. The user is started out at 100 and if the user reaches 0, the funds
 * will be replenished. A user may arrive at this activity either from the Play Activity or
 * Main Activity.
 *
 * This activity captures all gestures however only utilizes onLongPress & onFling. The long
 * press gesture will confirm the player's bet and the onFling gesture allows the user to
 * incrementally increase or decrease the bet by flinging up or down, respectively.
 *
 * @author Darren Rambaud
 */
public class PlaceWagerActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {
    public static final String PREFS_NAME = "SharedBlackjackVars";

    private SeekBar seekBar;
    private Button buttonBetAmount;
    private TextView textViewDisplayFunds;
    private Integer betAmount = 5;
    private double money;

    private static final int SWIPE_MIN_DISTANCE = 80;
    private static final int SWIPE_THRESHOLD_VELOCITY = 50;
    private static final String DEBUG_TAG = "GESTURES";
    private GestureDetectorCompat mDetector;
    private Audio feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_wager);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        Bundle bundle = getIntent().getExtras();
//test
        mDetector = new GestureDetectorCompat(this, this);
        mDetector.setOnDoubleTapListener(this);
        feedback = new Audio(getApplicationContext());
        try {
            if (pref.getBoolean("hasReachedZeroFunds", false)) {
                money = 100;
                edit.putBoolean("hasReachedZeroFunds", false);
                edit.apply();
            }
            else {
                money = bundle.getDouble("RESULT");
            }
        }
        catch (NullPointerException x) {
            x.printStackTrace();
            money = 100;
        }

        seekBar = (SeekBar) findViewById(R.id.seekBarBetAmount);
        buttonBetAmount = (Button) findViewById(R.id.buttonConfirmBet);
        textViewDisplayFunds = (TextView) findViewById(R.id.textViewAvailableFunds);
        textViewDisplayFunds.setText("AVAILABLE FUNDS: ".concat(Double.toString(money)));
        buttonBetAmount.setText("CONFIRM ($5)");
        feedback.hearMoney(money);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            String disp;
            Integer prog = 5; // set minimum bet value = 5

            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                seekBar.setMax((int)money);
                prog = 5 + i;
                if (prog > money) {
                    prog = (int) money;
                }
                disp = "CONFIRM ($".concat(prog.toString().concat(")"));
                buttonBetAmount.setText(disp);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                betAmount = prog; // Re-adjust after user stops adjusting seekbar to actual amount
            }
        });
    }

    public void confirmWager(View v) {
        Intent wager = new Intent(this, Play.class);
        wager.putExtra("BET_AMOUNT", betAmount);
        wager.putExtra("USER_MONEY", money);
        startActivity(wager);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        this.mDetector.onTouchEvent(event);
        // Be sure to call the superclass implementation
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        Log.d(DEBUG_TAG,"onDown: " + event.toString());
        return true;
    }

    /**
     * Upon notification of the onFling gesture, if the fling is from bottom to top then
     * the bet is increased by increments of 25. If the fling is from top to bottom, then the bet
     * is decreased by 25. Sensitivity can be adjusted by modifying the constants.
     * @param event1 a MotionEvent, the start of the onFling
     * @param event2 a MotionEvent, the end of the onFling
     * @param velocityX a float, refers to the speed of the fling event
     * @param velocityY a MotionEvent, not used in this method
     * @return a boolean
     */
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        if (event1.getY() - event2.getY() > SWIPE_MIN_DISTANCE &&
                Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            if ((betAmount += 25) > money) {
                betAmount = (int) money;
            }
        } // Fling from bottom to top
        else if (event2.getY() - event1.getY() > SWIPE_MIN_DISTANCE &&
                Math.abs(velocityX) > SWIPE_THRESHOLD_VELOCITY) {
            if ((betAmount -= 25) < 0) {
                betAmount = 5;
            }
        } // Fling from top to bottom

        buttonBetAmount.setText("CONFIRM ($"+betAmount+")");
        return true;
    }

    /**
     * Upon notification of the long press gesture, the user confirms the bet amount and initiates
     * the next Intent to the Play activity.
     * @param event a MotionEvent, not used
     */
    @Override
    public void onLongPress(MotionEvent event) {
        Intent wager = new Intent(this, Play.class);
        wager.putExtra("BET_AMOUNT", betAmount);
        wager.putExtra("USER_MONEY", money);
        startActivity(wager);
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
                            float distanceY) {
        Log.d(DEBUG_TAG, "onScroll: " + e1.toString()+e2.toString());
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        Log.d(DEBUG_TAG, "onShowPress: " + event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapUp: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTap: " + event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        Log.d(DEBUG_TAG, "onDoubleTapEvent: " + event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        Log.d(DEBUG_TAG, "onSingleTapConfirmed: " + event.toString());
        return true;
    }
}
