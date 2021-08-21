package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView timeText;
    SeekBar timerSeekBar;
    Boolean counterIsActive = false;
    Button button;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerSeekBar = findViewById(R.id.TimerSeekBar);
        timeText = findViewById(R.id.textView);
        button = findViewById(R.id.button);

        timerSeekBar.setMax(600);//in seconds
        timerSeekBar.setProgress(0);

        timerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public void btnClicked(View view){

        if(counterIsActive){

            resetTimer();

        }else {

            counterIsActive = true;
            timerSeekBar.setEnabled(false);
            button.setText("STOP");

            // use of 100 because it's late display of finish function
            //100 well allow the function to play sound at same time as the timer hits the 0

            countDownTimer = new CountDownTimer(timerSeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    Log.i("onfinish: ", "working");
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.ring);
                    mediaPlayer.start();
                    resetTimer();
                }
            }.start();
        }
    }

    public void updateTimer(int progress){
        int minutes = progress / 60;
        int seconds = progress - (minutes*60);
        //display time as 00:00
        String secondString = Integer.toString(seconds);
        String minutesString = Integer.toString(minutes) ;
        if(seconds <= 9) {
            secondString = "0" + secondString;
        }
        if(minutes < 10){
            minutesString = "0"+ minutesString;
        }
        //set time in textview
        timeText.setText(minutesString+":"+secondString);
    }

    public void  resetTimer(){
        timerSeekBar.setProgress(0);
        timerSeekBar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("START");
    }
}