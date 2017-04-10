package tnc16_coh5_d_r273.blind_blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startTutorial(View v) {
        Intent tutorial = new Intent(this, Tutorial.class);
        startActivity(tutorial);
    }

    public void startBlackjack(View v) {
        Intent playBlackjack = new Intent(this, Play.class);
        startActivity(playBlackjack);
    }
}
