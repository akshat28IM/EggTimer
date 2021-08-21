package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SeekBar timerSeekBar = findViewById(R.id.TimerSeekBar);
        TextView timeText = findViewById(R.id.textView);

        timerSeekBar.setMax(600);//in seconds
        timerSeekBar.setProgress(30);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                int minutes = progress / 60;
                int seconds = progress - (minutes*60);
                //display time as 00:00
                String secongString = Integer.toString(seconds);
                String minutesString = Integer.toString(minutes) ;
                if(secongString.equals("0")){
                    secongString = "00";


                }
                if(minutes < 10){
                    minutesString = "0"+Integer.toString(minutes);
                }
                //set time in textview
                timeText.setText(minutesString+":"+secongString);
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