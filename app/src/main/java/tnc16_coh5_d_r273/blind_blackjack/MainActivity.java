package tnc16_coh5_d_r273.blind_blackjack;

import android.content.Intent;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/**
 * MainActivity class refers to the Activity where the player is given the option to choose
 * between loading the Tutorial Activity or Play Activity.
 *
 * Gestures are captured in this class, specifically the onFling and onLongPress gestures.
 *
 * @author Darren Rambaud
 */
public class MainActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private static final String DEBUG_TAG = "GESTURES";
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDetector = new GestureDetectorCompat(this, this);
        mDetector.setOnDoubleTapListener(this);
        Toast.makeText(getApplicationContext(),
                "Welcome to Visually Impaired Blackjack!", Toast.LENGTH_LONG).show();
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
     * Upon notification of the onFling gesture (flick up gesture), the application will create
     * a new Intent to initiate the Tutorial activity
     * @param event1 not used
     * @param event2 not used
     * @param velocityX not used
     * @param velocityY not used
     * @return a boolean value
     */
    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2,
                           float velocityX, float velocityY) {
        Intent tutorial = new Intent(this, Tutorial.class);
        startActivity(tutorial);
        return true;
    }

    /**
     * Upon notification of the onLongPress gesture, the application will create a new Intent
     * to initiate the PlaceWagerActivity activity
     * @param event
     */
    @Override
    public void onLongPress(MotionEvent event) {
        Intent playBlackjack = new Intent(this, PlaceWagerActivity.class);
        startActivity(playBlackjack);
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

    /**
     * startTutorial method will create a new Intent and upon invocation will initiate the
     * Tutorial Activity. This method is linked to the "loadTutorial" button in the xml file
     * @param v
     */
    public void startTutorial(View v) {
        Intent tutorial = new Intent(this, Tutorial.class);
        startActivity(tutorial);
    }

    /**
     * startBlackjack method will create a new Intent and upon invocation will initiate the
     * PlaceWagerActivity. Method is linked to the "playBlackjack" button in the xml file
     * @param v
     */
    public void startBlackjack(View v) {
        Intent playBlackjack = new Intent(this, PlaceWagerActivity.class);
        startActivity(playBlackjack);
    }
}
