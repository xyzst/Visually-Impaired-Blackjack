package tnc16_coh5_d_r273.blind_blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toast.makeText(getApplicationContext(),
                "Welcome to Visually Impaired Blackjack!", Toast.LENGTH_LONG).show();
    }

    public void startTutorial(View v) {
        Intent tutorial = new Intent(this, Tutorial.class);
        startActivity(tutorial);
    }

    public void startBlackjack(View v) {
        Intent playBlackjack = new Intent(this, PlaceWagerActivity.class);
        startActivity(playBlackjack);
    }
}
