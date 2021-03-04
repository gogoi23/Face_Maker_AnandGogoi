package com.example.facemaker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;

/**
 * Class Face.java
 *
 * This extends surface view. I use it to draw the actual face.
 * It also acts as a radiogroup listener and a button listener
 *
 *  @author Anand Gogoi
 * @version March 2021
 */


public class Face extends SurfaceView implements AdapterView.OnItemSelectedListener,
View.OnClickListener{

    SeekBar r,g,b;//these are the three seekbars used to edit the color

    //below are the skin, eye, and hair color. skinr represents the red value of the skin color
    //skinColor represents the whole color. The same logic applies for all three of these lines
    int skinr,sking,skinb,skinColor;
    int eyeR,eyeG,eyeB,eyeColor;
    int hairR,hairG,hairB,hairColor;

    int hairStyle = -1;//used to indicate which hairstyle.
    Spinner hairSpinner;// the spinner used to select hair
    RadioGroupListener featureSelect;//the radiogroup used to determine which feature to edit
    boolean random = true;//determines if the random button has been activated
    boolean first = true;//determines if is the first turn

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        setWillNotDraw(false);
        randomize();//selects random colors for the face

    }
    // stop here stop here stop here stop here stop here stop here stop here
    public void onDraw(Canvas canvas){

        //thisse if statements determine color based on the slider values if
        // the random button has not been activated
        if (random == false) {
            //sets the appropriate feauture's color based on the clicked radio button based on the sliders
           //skin
            if (featureSelect.feature == 2) {
                sking = g.getProgress();
                skinr = r.getProgress();
                skinb = b.getProgress();
                skinColor = Color.rgb(r.getProgress(), g.getProgress(), b.getProgress());
            }
            //eyes
            else if (featureSelect.feature == 1) {
                eyeR = r.getProgress();
                eyeB = b.getProgress();
                eyeG = g.getProgress();
                eyeColor = Color.rgb(eyeR, eyeG, eyeB);
            }
            //hairs
            else if (featureSelect.feature == 3) {
                hairR = r.getProgress();
                hairB = b.getProgress();
                hairG = g.getProgress();
                hairColor = Color.rgb(hairR, hairG, hairB);
            }
        }

        else{
            //chooses random values instead if the random button has been clicked
            randomize();
        }

        //paints the skin
        Paint skinPaint = new Paint();
        skinPaint.setColor(skinColor);
        canvas.drawOval(560,800,960,200,skinPaint);

        //paints the eyes
        Paint eyePaint = new Paint();
        eyePaint.setColor(0xFFffffff);
        canvas.drawCircle(660,440,80,eyePaint);
        canvas.drawCircle(860,440,80,eyePaint);

        //paints the iris
        Paint irisPaint = new Paint();
        irisPaint.setColor(eyeColor);
        canvas.drawCircle(660,440,40,irisPaint);
        canvas.drawCircle(860,440,40,irisPaint);

        //paints the pupil
        Paint pupilPaint = new Paint();
        pupilPaint.setColor(0xFF000000);
        canvas.drawCircle(660,440,15,pupilPaint);
        canvas.drawCircle(860,440,15,pupilPaint);

        //paints the mouth
        Paint mouthPaint = new Paint();
        mouthPaint.setColor(Color.RED);
        canvas.drawOval(660,650,860,700,mouthPaint);
        canvas.drawRect(660,650,860,675,mouthPaint);

        //paints the nose
        Paint nosePaint = new Paint();
        nosePaint.setColor(0xFFb5883f);
        canvas.drawOval(730,500,790,625,nosePaint);

        //paint the hair
        Paint hairPaint = new Paint();
        hairPaint.setColor(hairColor);


        //If its the first turn or random has been clicked its sets hairstyle
        //to a random value between 0 and 2
        if(hairStyle == -1 || random){
            hairStyle = (int) (Math.random() * (3));
            hairSpinner.setSelection(hairStyle);
        }


        //draws mohawk
        if (hairStyle == 2 ) {
            canvas.drawOval(710, 200, 810, 400, hairPaint);
        }
        //draws afro
        else if (hairStyle == 0) {
            canvas.drawCircle(750, 200, 140, hairPaint);
        }
        //draws middlepart
        else if (hairStyle == 1) {
            canvas.drawOval(630, 170, 760, 260, hairPaint);
            canvas.drawOval(890, 170, 760, 260, hairPaint);
        }

        //sets random back to false and makes true false
        random = false;
        first = false;

    }

    //these methods set the seekbars. I do this because this class needs to get info from the seekabars
    public void setR(SeekBar r){
        this.r = r;
    }
    public void setg(SeekBar g){
        this.g = g;
    }
    public void setb(SeekBar b){
        this.b = b;
    }

    //sets the radiogroup listener
    public void setFeatureSelect(RadioGroupListener featureSelect){
        this.featureSelect = featureSelect;
    }

    //This method changes the hair style based one the used selects in the spinner
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String style = parent.getItemAtPosition(position).toString();
        if(style.equals("Afro")){
            hairStyle = 0;
        }
        else if(style.equals("Middle Part")){
            hairStyle = 1;
        }
        else if(style.equals("Mohawk")){
            hairStyle = 2;
        }
        invalidate();
    }

    //I just made this because the implemented interface needed it
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    //Sets the appropriate hair spinner
    public void setHairSpinner(Spinner hairSpinner){
        this.hairSpinner = hairSpinner;
    }

    //indicates the random button has been clicked
    @Override
    public void onClick(View v) {
        random = true;
        invalidate();
    }

    //activates if the ranodmize button has been clicked
    public void randomize() {
        //determines the rgb values for skin
        skinr = (int) (Math.random() * (256));
        sking = (int) (Math.random() * (256));
        skinb = (int) (Math.random() * (256));
        //determines the skin color based on these values
        skinColor = Color.rgb(skinr, sking, skinb);

        //does not do anything to the seekbars. This is the first time making the face
        //there is no radio button selected so no need to adjust the seekbar values
        //without this if statement the code crashes because the seekbars have not been
        //initialized yet
        if (!first) {

            //sets the seek bars to the feature if the feautre's radio button has been clicked
            if (featureSelect.feature == 2) {
                r.setProgress(skinr);
                g.setProgress(sking);
                b.setProgress(skinb);
            }
        }
        //determines the rgb values for eyes
        eyeR = (int) (Math.random() * (256));
        eyeG = (int) (Math.random() * (256));
        eyeB = (int) (Math.random() * (256));
        //determines the eye color based on these values
        eyeColor = Color.rgb(eyeR, eyeG, eyeB);

        if (!first) {

            //sets the seek bars to the feature if the feautre's radio button has been clicked
            if (featureSelect.feature == 1) {
                r.setProgress(eyeR);
                g.setProgress(eyeG);
                b.setProgress(eyeB);
            }
        }

        ////determines the rgb values for the haircolor
        hairR = (int) (Math.random() * (256));
        hairG = (int) (Math.random() * (256));
        hairB = (int) (Math.random() * (256));
        //determines the hair color based on these values
        hairColor = Color.rgb(hairR, hairG, hairB);

        if (!first)

            //sets the seek bars to the feature if the feautre's radio button has been clicked
            if (featureSelect.feature == 3) {
                r.setProgress(hairR);
                g.setProgress(hairG);
                b.setProgress(hairB);
            }
         }
}


//old code used to select random values. I do not want to delete becuase I might switch back to this
/*//skinColor
        skinr = (int) (Math.random() * (256));
        sking = (int) (Math.random() * (256));
        skinb = (int) (Math.random() * (256));
        skinColor = Color.rgb(skinr, sking, skinb);

        //eyecolor
        eyeR = (int) (Math.random() * (256));
        eyeG = (int) (Math.random() * (256));
        eyeB = (int) (Math.random() * (256));
        eyeColor = Color.rgb(eyeR,eyeG,eyeB);

        //haircolor
        hairR = (int) (Math.random() * (256));
        hairG = (int) (Math.random() * (256));
        hairB = (int) (Math.random() * (256));
        hairColor = Color.rgb(hairR,hairG,hairB);

         */

//skingColor
            /*skinr = (int) (Math.random() * (256));
            sking = (int) (Math.random() * (256));
            skinb = (int) (Math.random() * (256));
            skinColor = Color.rgb(skinr, sking, skinb);
            if(featureSelect.feature == 2){
                r.setProgress(skinr);
                g.setProgress(sking);
                b.setProgress(skinb);
            }

            //eyecolor
            eyeR = (int) (Math.random() * (256));
            eyeG = (int) (Math.random() * (256));
            eyeB = (int) (Math.random() * (256));
            eyeColor = Color.rgb(eyeR,eyeG,eyeB);
            if(featureSelect.feature == 1){
                r.setProgress(eyeR);
                g.setProgress(eyeG);
                b.setProgress(eyeB);
            }

            //haircolor
            hairR = (int) (Math.random() * (256));
            hairG = (int) (Math.random() * (256));
            hairB = (int) (Math.random() * (256));
            hairColor = Color.rgb(hairR,hairG,hairB);
            if(featureSelect.feature == 3){
                r.setProgress(hairR);
                g.setProgress(hairG);
                b.setProgress(hairB);
            }
            */
