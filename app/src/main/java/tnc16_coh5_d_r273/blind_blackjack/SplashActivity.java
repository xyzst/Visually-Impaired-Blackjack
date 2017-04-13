package tnc16_coh5_d_r273.blind_blackjack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * SplashActivity is the de-facto loading screen for this application. The amount of time
 * the loading screen is displayed is dependent on how long it takes for the user's phone
 * to load up the application. When loading is completed, the activity switches to the
 * MainActivity class.
 *
 * @author Darren Rambaud
 */

public class SplashActivity extends AppCompatActivity {
    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
