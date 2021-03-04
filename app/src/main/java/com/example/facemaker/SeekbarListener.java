package com.example.facemaker;

import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Class SeekbarListener
 *
 * This listens to one seekbar. I made three of these. One for each seekbar.
 *
 *  @author Anand Gogoi
 * @version March 2021
 */

public class SeekbarListener implements SeekBar.OnSeekBarChangeListener{
    TextView barProgress;//this displays to the user what the progress of the seekbar is
    int bp;//this is the actual value of the seekbar progress
    Face mainView;//this is the face that use sees

    //this constructor sets the bar's initial progress to 0. It sets barProgress to "0" and makes the face equal
    //the face
    public SeekbarListener(TextView Progress, SeekBar bar,Face mainView){
        this.barProgress = Progress;
        bp = 0;// (int)(Math.random() * (256));
        this.barProgress.setText(""+(bp)+"");

        this.mainView = mainView;
        //feature = -1;

    }

    //this method reflects the changes made when a user adjusts the bar. It changes bp and text view
    // to the bars's progress
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            barProgress.setText("" + progress + "");
            bp = progress;
            mainView.invalidate();



    }

    //I don't use this method. I put it here because the interface requires it.
    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    //I don't use this method. I put it here because the interface requires it.
    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

}
