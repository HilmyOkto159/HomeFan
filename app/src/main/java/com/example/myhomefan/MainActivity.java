package com.example.myhomefan;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    ImageView imageView;
    ObjectAnimator rotateAnimator;
    Switch switchButton;
    SeekBar seekBar;

    final int SPEED [] = {0, 5000, 3000, 1000};

    GradientDrawable gd = new GradientDrawable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toggleButton = findViewById(R.id.toggleButton);
        imageView = findViewById(R.id.imageView);
        switchButton = findViewById(R.id.switch1);
        seekBar = findViewById(R.id.seekBar);

        rotateAnimator = ObjectAnimator.ofFloat(imageView, "rotation", 0, 360);
        rotateAnimator.setDuration(1000);
        rotateAnimator.setRepeatCount(ValueAnimator.INFINITE);
        rotateAnimator.setInterpolator(new LinearInterpolator());

        if(toggleButton.isChecked() == true){
            rotateAnimator.setDuration(SPEED[1000]);
        }else{
            rotateAnimator.end();
        }

        //light
        gd.setShape(GradientDrawable.RECTANGLE);
        gd.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        gd.setGradientRadius(330);

        //turn on light
        switchButton = (Switch) findViewById(R.id.switch1);
        if(switchButton.isChecked() == true){
            gd.setColors(new int []{Color.YELLOW, Color.TRANSPARENT});
            imageView.setBackground(gd);
        }else{
            imageView.setBackgroundColor(Color.TRANSPARENT);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (toggleButton.isChecked()) {
                    rotateAnimator.setDuration(SPEED[progress]);
                    rotateAnimator.start();
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }
}