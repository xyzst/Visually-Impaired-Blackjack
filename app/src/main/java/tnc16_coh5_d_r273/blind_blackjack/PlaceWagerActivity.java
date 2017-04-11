package tnc16_coh5_d_r273.blind_blackjack;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class PlaceWagerActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "SharedBlackjackVars";

    private SeekBar seekBar;
    private Button buttonBetAmount;
    private TextView textViewDisplayFunds;
    private Integer betAmount = 5;
    private double money;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_wager);
        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        Bundle bundle = getIntent().getExtras();

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
}
