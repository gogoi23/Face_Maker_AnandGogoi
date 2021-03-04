package com.example.facemaker;

import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Class RadioGroupListener
 *
 * This indicates when a radio button has been clicked
 *
 *  @author Anand Gogoi
 * @version March 2021
 */

public class RadioGroupListener implements RadioGroup.OnCheckedChangeListener{


    SeekBar red,blue,green;//these are the three seekbars that determin the color
    TextView featureInfo;//displays to the use what feature is being edited
    int feature;//determins which feature has been selected
    Face face;// the face view

    //constructor. Sets feature to one to indicate that no button has been clicked at the beginning of the code
    public RadioGroupListener(){
        feature = -1;
    }

    //changes featureInfor, feature, and seekbars based on which radio button has been clicked
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if(checkedId == R.id.eyeradiobutton){
            feature = 1;
            featureInfo.setText("These sliders determine the color of the eyes");
            red.setProgress(face.eyeR);
            green.setProgress(face.eyeG);
            blue.setProgress(face.eyeB);
        }
        else if(checkedId == R.id.skinradiobutton){
            feature = 2;
            featureInfo.setText("These sliders determine the color of the skin");
            red.setProgress(face.skinr);
            blue.setProgress(face.skinb);
            green.setProgress(face.sking);
        }
        else if (checkedId == R.id.hairradiobutton){
            feature = 3;
            featureInfo.setText("These sliders determine the color of the hair");
            red.setProgress(face.hairR);
            green.setProgress(face.hairG);
            blue.setProgress(face.hairB);
        }
    }

    //sets the textView
    public void setFeatureInfo(TextView featureInfo){
        this.featureInfo = featureInfo;
    }

    //sets the face
    public void setFace(Face face){
        this.face = face;
    }

    //sets the seekBars
    public void setSeekbars(SeekBar red,SeekBar green,SeekBar blue){
        this.red = red;
        this.green = green;
        this.blue = blue;
    }


}
