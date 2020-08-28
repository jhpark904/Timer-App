package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    boolean flag;
    int defaultSec = 30;
    int sec = 30;
    int min = 0;
    CountDownTimer timer;
    Button button;
    SeekBar seekBar;
    int Maxmin = 5;
    int progress;

    public void click(View view) {
        if (flag) {
            timer.cancel();
            timer = timer = new CountDownTimer(defaultSec * 1000, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    long numSec = millisUntilFinished / 1000;
                    int min = (int) numSec / 60;
                    int sec = (int) numSec % 60;
                    text.setText(String.format("%02d", min) + " : " + String.format("%02d",sec));
                    seekBar.setProgress((int) millisUntilFinished);

                }
                @Override
                public void onFinish() {
                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                }
            };
            timer.onTick(defaultSec * 1000);
            flag = false;
            button.setText("Start!");
        } else {
            timer.start();
            flag = true;
            button.setText("Stop!");
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.button10);
        button.setText("Start!");

        text = findViewById(R.id.textView);
        text.setText(String.format("%02d", min) + " : " + String.format("%02d",defaultSec));

        seekBar = findViewById(R.id.seekBar);
        seekBar.setMax(Maxmin * 60 * 1000);
        seekBar.setProgress(sec * 1000);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                MainActivity.this.progress = progress;
                timer.onTick(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                timer.cancel();

            }

            @Override
            public void onStopTrackingTouch(final SeekBar seekBar) {
                timer = new CountDownTimer(progress, 1000) {
                    @Override
                    public void onTick(long millisUntilFinished) {
                        long numSec = millisUntilFinished / 1000;
                        int min = (int) numSec / 60;
                        int sec = (int) numSec % 60;
                        text.setText(String.format("%02d", min) + " : " + String.format("%02d",sec));
                        seekBar.setProgress((int) millisUntilFinished);

                    }
                    @Override
                    public void onFinish() {
                        MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                        mediaPlayer.start();
                    }
                };
                flag = false;
                button.setText("Start!");

            }
        });

        timer = new CountDownTimer(defaultSec * 1000, 1000) {

            @Override
            public void onTick(long millisUntilFinished) {
               long numSec = millisUntilFinished / 1000;
               int min = (int) numSec / 60;
               int sec = (int) numSec % 60;
                text.setText(String.format("%02d", min) + " : " + String.format("%02d",sec));
               seekBar.setProgress((int) millisUntilFinished);

            }

            @Override
            public void onFinish() {
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);

                mediaPlayer.start();

            }
        };

    }
}