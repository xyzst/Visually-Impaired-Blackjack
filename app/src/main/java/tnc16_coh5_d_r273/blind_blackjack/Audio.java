package tnc16_coh5_d_r273.blind_blackjack;
import android.media.MediaPlayer;
import android.media.AudioManager;
import java.util.Random;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.app.Application;
import android.content.Context;


/**
 * Audio class serves as an instantiable object that will play audio feedback based on function
 * input. This class can interpret score values and specific blackjack game events and output the
 * correct audio response.
 *
 * Gestures are captured in this class, specifically the onFling and onLongPress gestures.
 *
 * @author Colin Hughes
 */

public class Audio {

    private Context context;

    public Audio(Context c){
        context = c;
    }

    /**
     * hearPoints receives information on both the user and dealer's score and speaks these values
     * to the user via audio output. This information can be delivered in any combination such that
     * 1 only the user's score is spoken, 2 only the dealer's score is spoken, 0 or both are spoken.
     * This is dependent on the dealType ID. A switch structure is used to interpret this output.
     * @param userPoints
     * @param dealerPoints
     * @param dealTypeID
     */
    public void hearPoints(int userPoints, int dealerPoints, int dealTypeID) {
        int points = 0;

        MediaPlayer mp=new MediaPlayer();
        MediaPlayer mp2=new MediaPlayer();
        mp2= MediaPlayer.create(context, R.raw.silence);

        Random cardSound = new Random();
        int  randomDrawSound;

        for(int i = 1; i<=2; i++) {
            if(i == 1 && dealTypeID != 2)
                points = userPoints;
            else if(i == 1)
                i++;
            else if(dealTypeID != 1 && dealTypeID != 3){
                points = dealerPoints;
                mp = MediaPlayer.create(context, R.raw.pausehalf);
                mp.start();
                while (mp.isPlaying()) {/** wait */}
                mp = MediaPlayer.create(context, R.raw.dealer);
                mp.start();
                while (mp.isPlaying()) {/** wait */}
            }

            randomDrawSound = cardSound.nextInt(3) + 1;

            while(mp.isPlaying()) {/** wait */}

            switch(randomDrawSound) {
                case 1:
                    mp = MediaPlayer.create(context, R.raw.draw1);
                    mp.start();
                    break;
                case 2:
                    mp = MediaPlayer.create(context, R.raw.draw2);
                    mp.start();
                    break;
                case 3:
                    mp = MediaPlayer.create(context, R.raw.draw3);
                    mp.start();
                    break;
            }

            while(mp.isPlaying()) {/** wait */}

            switch (points) {
                case 1:
                    mp = MediaPlayer.create(context, R.raw.n1);
                    mp.start();
                    break;
                case 2:
                    mp = MediaPlayer.create(context, R.raw.n2);
                    mp.start();
                    break;
                case 3:
                    mp = MediaPlayer.create(context, R.raw.n3);
                    mp.start();
                    break;
                case 4:
                    mp = MediaPlayer.create(context, R.raw.n4);
                    mp.start();
                    break;
                case 5:
                    mp = MediaPlayer.create(context, R.raw.n5);
                    mp.start();
                    break;
                case 6:
                    mp = MediaPlayer.create(context, R.raw.n6);
                    mp.start();
                    break;
                case 7:
                    mp = MediaPlayer.create(context, R.raw.n7);
                    mp.start();
                    break;
                case 8:
                    mp = MediaPlayer.create(context, R.raw.n8);
                    mp.start();
                    break;
                case 9:
                    mp = MediaPlayer.create(context, R.raw.n9);
                    mp.start();
                    break;
                case 10:
                    mp = MediaPlayer.create(context, R.raw.n10);
                    mp.start();
                    break;
                case 11:
                    mp = MediaPlayer.create(context, R.raw.n11);
                    mp.start();
                    break;
                case 12:
                    mp = MediaPlayer.create(context, R.raw.n12);
                    mp.start();
                    break;
                case 13:
                    mp = MediaPlayer.create(context, R.raw.n13);
                    mp.start();
                    break;
                case 14:
                    mp = MediaPlayer.create(context, R.raw.n14);
                    mp.start();
                    break;
                case 15:
                    mp = MediaPlayer.create(context, R.raw.n15);
                    mp.start();
                    break;
                case 16:
                    mp = MediaPlayer.create(context, R.raw.n16);
                    mp.start();
                    break;
                case 17:
                    mp = MediaPlayer.create(context, R.raw.n17);
                    mp.start();
                    break;
                case 18:
                    mp = MediaPlayer.create(context, R.raw.n18);
                    mp.start();
                    break;
                    case 19:
                    mp = MediaPlayer.create(context, R.raw.n19);
                    mp.start();
                    break;
                case 20:
                    mp = MediaPlayer.create(context, R.raw.n20);
                    mp.start();
                    break;
                case 21:
                    mp = MediaPlayer.create(context, R.raw.n20);
                    mp.start();
                    while (mp.isPlaying()) {/** wait */}
                    mp = MediaPlayer.create(context, R.raw.n1);
                    mp.start();
                    break;
                case 22:
                    mp = MediaPlayer.create(context, R.raw.n20);
                    mp.start();
                    while (mp.isPlaying()) {/** wait */}
                    mp = MediaPlayer.create(context, R.raw.n2);
                    mp.start();
                    break;
                case 23:
                    mp = MediaPlayer.create(context, R.raw.n20);
                    mp.start();
                    while (mp.isPlaying()) {/** wait */}
                    mp = MediaPlayer.create(context, R.raw.n3);
                    mp.start();
                    break;
                case 24:
                    mp = MediaPlayer.create(context, R.raw.n20);
                    mp.start();
                    while (mp.isPlaying()) {/** wait */}
                    mp = MediaPlayer.create(context, R.raw.n4);
                    mp.start();
                    break;
                case 25:
                mp = MediaPlayer.create(context, R.raw.n20);
                    mp.start();
                    while (mp.isPlaying()) {/** wait */}
                    mp = MediaPlayer.create(context, R.raw.n5);
                    mp.start();
                    break;
                case 26:
                    mp = MediaPlayer.create(context, R.raw.n20);
                    mp.start();
                    while (mp.isPlaying()) {/** wait */}
                    mp = MediaPlayer.create(context, R.raw.n6);
                    mp.start();
                    break;
                case 27:
                    mp = MediaPlayer.create(context, R.raw.n20);
                    mp.start();
                    while (mp.isPlaying()) {/** wait */}
                    mp = MediaPlayer.create(context, R.raw.n7);
                    mp.start();
                    break;
                case 28:
                    mp = MediaPlayer.create(context, R.raw.n20);
                    mp.start();
                    while (mp.isPlaying()) {/** wait */}
                    mp = MediaPlayer.create(context, R.raw.n8);
                    mp.start();
                    break;
                case 29:
                    mp = MediaPlayer.create(context, R.raw.n20);
                    mp.start();
                    while (mp.isPlaying()) {/** wait */}
                    mp = MediaPlayer.create(context, R.raw.n9);
                    mp.start();
                    break;
                case 30:
                    mp = MediaPlayer.create(context, R.raw.n30);
                    mp.start();
                    break;
                case 31:
                mp = MediaPlayer.create(context, R.raw.n20);
                    mp.start();
                    while (mp.isPlaying()) {/** wait */}
                    mp = MediaPlayer.create(context, R.raw.n1);
                    mp.start();
                    break;
                default:
                    mp = MediaPlayer.create(context, R.raw.silence);
                    mp.start();
                    break;
            }

            while(mp.isPlaying()) {/** wait */}
        }
        while(mp.isPlaying()) {/** wait */}
    }

    /**
     * hearResult speaks information about blackjack round outcomes to the user. Each outcome
     * scenario has a corresponding ID that is interpreted by a switch structure before output.
     * @param resultID
     */
    public void hearResult(int resultID){
        MediaPlayer mp=new MediaPlayer();
        mp= MediaPlayer.create(context, R.raw.silence);
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);

        switch(resultID){
            case 1: mp= MediaPlayer.create(context, R.raw.push);
                break;
            case 2: mp= MediaPlayer.create(context, R.raw.victory);
                break;
            case 3: mp= MediaPlayer.create(context, R.raw.failure);
                break;
            case 4: mp= MediaPlayer.create(context, R.raw.bust);
                break;
            case 5: mp= MediaPlayer.create(context, R.raw.dealerbust);
                break;
                 case 6: mp= MediaPlayer.create(context, R.raw.pushstand);
                break;
            case 7: mp= MediaPlayer.create(context, R.raw.failurestand);
                break;
            case 8: mp= MediaPlayer.create(context, R.raw.victorystand);
                break;
            default:
                mp= MediaPlayer.create(context, R.raw.silence);
                break;
        }
        mp.start();
        while(mp.isPlaying()) {/** wait */}
    }
}
