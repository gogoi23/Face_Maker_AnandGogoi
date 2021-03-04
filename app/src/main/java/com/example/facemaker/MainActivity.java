package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Class MainActivity
 *
 * This is the main class for the program.  It initializes all
 * the surfaceviews,buttons, etc. and links them all together.
 * @author Anand Gogoi
 * @version March 2021
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);




        //This initializes the RADIO GROUP as well as it's listener
        RadioGroupListener featureListener = new RadioGroupListener();
        RadioGroup featurchooser = (RadioGroup)findViewById(R.id.featurechooser);
        featurchooser.setOnCheckedChangeListener(featureListener);

        //This initializes the SURFACE VIEW used to display the face.
        Face notePad = (Face)findViewById(R.id.notePad);
        notePad.setFeatureSelect(featureListener);
        featureListener.setFace(notePad);//sets the listener for feature Listener


        //This initializes the GREEN SEEKBAR used to edit the green color as well as TEXTView used to describe the
        //Seekbar's proggres. It also sets the green SEEKBAR listener.
        SeekBar greenslider = (SeekBar) findViewById(R.id.greenslider);
        TextView greennumber = (TextView)findViewById(R.id.greennumber);
        SeekbarListener greeninfo = new SeekbarListener(greennumber,greenslider,notePad);
        greenslider.setOnSeekBarChangeListener(greeninfo);
        notePad.setg(greenslider);

        //This initializes the RED SEEKBAR used to edit the green color as well as TEXTView used to describe the
        //Seekbar's proggres. It also sets the RED SEEKBAR listener.
        SeekBar redslider = (SeekBar) findViewById(R.id.redslider);
        TextView rednumber = (TextView)findViewById(R.id.rednumber);
        SeekbarListener redInfo = new SeekbarListener(rednumber,redslider,notePad);
        redslider.setOnSeekBarChangeListener(redInfo);
        notePad.setR(redslider);

        //This initializes the BLUE SEEKBAR used to edit the green color as well as TEXTView used to describe the
        //Seekbar's proggres. It also sets the BLUE SEEKBAR listener.
        SeekBar blueslider = (SeekBar) findViewById(R.id.blueslider);
        TextView bluenumber = (TextView)findViewById(R.id.bluenumber);
        SeekbarListener blueInfo = new SeekbarListener(bluenumber,blueslider,notePad);
        blueslider.setOnSeekBarChangeListener(blueInfo);
        notePad.setb(blueslider);


        //sets all the seekbars used for feature listener
        //I do this because featureListener needs acess to these seekbars
        featureListener.setSeekbars(redslider,greenslider,blueslider);

        //This textview will be used to indicate what feature the seekbars are representing
        TextView featureInfo = (TextView)findViewById(R.id.featurInfo);
        featureListener.setFeatureInfo(featureInfo);

        //Intitializes a SPINNER for the hair selction
        Spinner hairSelect = findViewById(R.id.hairSpinner);
        ArrayAdapter<CharSequence> hairAdapter = ArrayAdapter.createFromResource(this,R.array.styles,android.R.layout.simple_spinner_item);
        hairAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        hairSelect.setAdapter(hairAdapter);
        hairSelect.setOnItemSelectedListener(notePad);
        notePad.setHairSpinner(hairSelect);

        //Initializes the randomize button
        Button randomButton = (Button)findViewById(R.id.randomizeButton);
        randomButton.setOnClickListener(notePad);

    }


}