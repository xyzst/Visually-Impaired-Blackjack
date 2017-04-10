package tnc16_coh5_d_r273.blind_blackjack;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class PlaceWagerActivity extends AppCompatActivity {
    private SeekBar seekBar;
    private Button textViewBetAmount;
    private Integer betAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_wager);
        seekBar = (SeekBar) findViewById(R.id.seekBarBetAmount);
        textViewBetAmount = (Button) findViewById(R.id.buttonConfirmBet);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                betAmount = i;
                String x = "CONFIRM ($".concat(betAmount.toString().concat(")"));
                textViewBetAmount.setText(x);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void confirmWager(View v) {
        Intent wager = new Intent(this, Play.class);
        wager.putExtra("BET_AMOUNT", betAmount);
        startActivity(wager);
    }
}
