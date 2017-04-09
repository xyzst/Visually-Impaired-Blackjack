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

        Intent intent = getIntent();
        String message = intent.getStringExtra(Blackjack.EXTRA_MESSAGE);

        TextView x = (TextView) findViewById(R.id.textView2);
        x.setText(message);
    }
}
