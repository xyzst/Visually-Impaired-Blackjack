package tnc16_coh5_d_r273.blind_blackjack;
import android.media.MediaPlayer;
import java.util.Random;
import android.content.Context;

/**
 * The Audio class serves as an instantiable object that can play audio feedback based on function
 * input. This class can interpret score values, money amounts, specific blackjack game events, and
 * output the correct speech interface.
 *
 * @author Colin Hughes
 */
public class Audio {

    private Context context;

    public Audio(Context c) {
        context = c;
    }

    /**
     * hearPoints receives information on both the user and dealer's score and speaks these values
     * to another function to be interpreted for audio output. This information can be delivered in
     * any combination such that [dealTypeID=1] only the user's score is spoken, [dealTypeId=2] only
     * the dealer's score is spoken, [dealTypeID=0] or both are spoken. This is dependent on the
     * dealType ID. Additionally, a random card drawn sound effect is played to simulate a real card
     * game.
     *
     * @param userPoints, the amount of card points that the user has.
     * @param dealerPoints, the total value of the dealer's cards
     * @param dealTypeID, whose points are read aloud? 1-user,2-dealer,0-both
     */
    public void hearPoints(int userPoints, int dealerPoints, int dealTypeID) {
        int points = 0;

        MediaPlayer mp = new MediaPlayer();

        Random cardSound = new Random();
        int randomDrawSound;

        for (int i = 1; i <= 2; i++) {
            if (i == 1 && dealTypeID != 2)
                points = userPoints;
            else if (dealTypeID == 2 || dealTypeID == 0) {
                points = dealerPoints;
                mp = MediaPlayer.create(context, R.raw.pausehalf);
                mp.start();
                while (mp.isPlaying()) {/** wait */}
                mp = MediaPlayer.create(context, R.raw.dealer);
                mp.start();
                while (mp.isPlaying()) {/** wait */}
            }

            randomDrawSound = cardSound.nextInt(3) + 1;

            while (mp.isPlaying()) {/** wait */}

            switch (randomDrawSound) {
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

            while (mp.isPlaying()) {/** wait */}

            mp = MediaPlayer.create(context, R.raw.pausehalf);
            mp.start();
            while (mp.isPlaying()) {/** wait */}

            soundInterpreter(points);

            if(dealTypeID == 1 || dealTypeID == 2)
                i=2;
        }
        while (mp.isPlaying()) {/** wait */}
    }

    /**
     * soundInterpreter receives an integer which it interprets in a switch structure and then
     * speaks to the user. Converts integers into spoken words for audio interface.
     *
     * @param soundValue, an int value that will be turned into speech output
     */
    public void soundInterpreter(int soundValue) {
        MediaPlayer mp = new MediaPlayer();

        switch (soundValue) {
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
                mp = MediaPlayer.create(context, R.raw.n30);
                mp.start();
                while (mp.isPlaying()) {/** wait */}
                mp = MediaPlayer.create(context, R.raw.n1);
                mp.start();
                break;
            case 40:
                mp = MediaPlayer.create(context, R.raw.n40);
                mp.start();
                break;
            case 50:
                mp = MediaPlayer.create(context, R.raw.n50);
                mp.start();
                break;
            case 60:
                mp = MediaPlayer.create(context, R.raw.n60);
                mp.start();
                break;
            case 70:
                mp = MediaPlayer.create(context, R.raw.n70);
                mp.start();
                break;
            case 80:
                mp = MediaPlayer.create(context, R.raw.n80);
                mp.start();
                break;
            case 90:
                mp = MediaPlayer.create(context, R.raw.n90);
                mp.start();
                break;
            default:
                mp = MediaPlayer.create(context, R.raw.silence);
                mp.start();
                break;
        }
        while (mp.isPlaying()) {/** wait */}
    }

    /**
     * hearResult speaks information about blackjack round outcomes to the user. Each outcome
     * scenario has a corresponding ID that is interpreted by a switch structure before output.
     *
     * @param resultID, corresponds to a victory/lost condition in the game
     */
    public void hearResult(int resultID) {
        MediaPlayer mp = new MediaPlayer();
        mp = MediaPlayer.create(context, R.raw.silence);

        switch (resultID) {
            case 1:
                mp = MediaPlayer.create(context, R.raw.push);
                break;
            case 2:
                mp = MediaPlayer.create(context, R.raw.victory);
                break;
            case 3:
                mp = MediaPlayer.create(context, R.raw.failure);
                break;
            case 4:
                mp = MediaPlayer.create(context, R.raw.bust);
                break;
            case 5:
                mp = MediaPlayer.create(context, R.raw.dealerbust);
                break;
            case 6:
                mp = MediaPlayer.create(context, R.raw.pushstand);
                break;
            case 7:
                mp = MediaPlayer.create(context, R.raw.failurestand);
                break;
            case 8:
                mp = MediaPlayer.create(context, R.raw.victorystand);
                break;
            default:
                mp = MediaPlayer.create(context, R.raw.silence);
                break;
        }
        mp.start();
        while (mp.isPlaying()) {/** wait */}
    }

    /**
     * The purpose of this function is to facilitate the speaking of the user's money value aloud.
     * hearMoney receives a money amount and parses it of its place values. These place values are
     * then sent off to be interpreted and spoken aloud to the user.
     * @param money, the amount of money that the user has. To be read aloud as speech output.
     */
    public void hearMoney(double money) {
        MediaPlayer mp = new MediaPlayer();
        mp = MediaPlayer.create(context, R.raw.youhave);
        mp.start();
        while (mp.isPlaying()) {/** wait */}

        int thousand = 0,
                hundred = 0,
                ten = 0,
                one = 0,
                moneyInt = (int) money;

        if (moneyInt >= 1000) {
            thousand = moneyInt / 1000;
            moneyInt = moneyInt - (thousand * 1000);
            soundInterpreter(thousand);
            mp = MediaPlayer.create(context, R.raw.n1000);
            mp.start();
            while (mp.isPlaying()) {/** wait */}
        }
        if (moneyInt >= 100) {
            hundred = moneyInt / 100;
            moneyInt = moneyInt - (hundred * 100);
            soundInterpreter(hundred);
            mp = MediaPlayer.create(context, R.raw.n100);
            mp.start();
            while (mp.isPlaying()) {/** wait */}
        }
        if(moneyInt - (moneyInt % 10) != 0)
            ten = moneyInt - (moneyInt % 10);
        else
            ten = 1;
        one = moneyInt;
        moneyInt = moneyInt % ten;
        if (moneyInt < 3)
            soundInterpreter(one);
        else {
            soundInterpreter(ten);
            soundInterpreter(moneyInt);
        }

        mp = MediaPlayer.create(context, R.raw.dollars);
        mp.start();
    }

    /**
     * hearBet provides audio feedback to the betting process. Contains multiple respective sound
     * effects for increasing and decreasing bets. Takes an input value to determine whether the
     * user is swiping up or down to increase (up) or lower (down) their bet.
     * @param upOrDown, swiping up to increase bet or down to lower? [upOrDown=1]->up 2->down
     */
    public void hearBet(int upOrDown){
        MediaPlayer mp = new MediaPlayer();
        mp = MediaPlayer.create(context, R.raw.youhave);

        Random moneySound = new Random();
        int randomMoneySound;

        randomMoneySound = moneySound.nextInt(3) + 1;

        switch (randomMoneySound) {
            case 1:
                if(upOrDown == 1)
                    mp = MediaPlayer.create(context, R.raw.more1);
                else
                    mp = MediaPlayer.create(context, R.raw.less1);
                mp.start();
                break;
            case 2:
                if(upOrDown == 1)
                    mp = MediaPlayer.create(context, R.raw.more2);
                else
                    mp = MediaPlayer.create(context, R.raw.less2);
                mp.start();
                break;
            case 3:
                if(upOrDown == 1)
                    mp = MediaPlayer.create(context, R.raw.more3);
                else
                    mp = MediaPlayer.create(context, R.raw.less3);
                mp.start();
                break;
        }
    }
}
