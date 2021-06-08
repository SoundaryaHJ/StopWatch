package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity
{
    private static  final long START_TIME_IN_MILLIS = 600000;

    private TextView time;
    private Button start_pause;
    private Button reset;

    private CountDownTimer countDownTimer;
    private boolean TimerRunning;
    private  long TimeLeftMills = START_TIME_IN_MILLIS;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        time = findViewById(R.id.time);
        start_pause = findViewById(R.id.start_pause);
        reset = findViewById(R.id.reset);

        start_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(TimerRunning)
                {
                    pauseTimer();
                }
                else
                {
                    startTimer();
                }

            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                resetTimer();

            }
        });

        updateCountDownText();
    }

    private void startTimer()
    {
        countDownTimer = new CountDownTimer(TimeLeftMills, 1000) {
            @Override
            public void onTick(long millisUnilFinished)
            {
                TimeLeftMills = millisUnilFinished;
                updateCountDownText();

            }

            @Override
            public void onFinish()
            {
                TimerRunning = false;
                start_pause.setText("start");
                start_pause.setVisibility(View.INVISIBLE);
                reset.setVisibility(View.VISIBLE);

            }
        }.start();

        TimerRunning = true;
        start_pause.setText("Pause");
        reset.setVisibility(View.INVISIBLE);

    }

    private void pauseTimer()
    {
        countDownTimer.cancel();
        TimerRunning = false;
        start_pause.setText("start");
        reset.setVisibility(View.VISIBLE);

    }

    private void resetTimer()
    {
        TimeLeftMills = START_TIME_IN_MILLIS;
        updateCountDownText();
        reset.setVisibility(View.INVISIBLE);
        start_pause.setVisibility(View.VISIBLE);

    }

    private void updateCountDownText()
    {
        int minutes = (int) (TimeLeftMills / 1000) / 60;
        int seconds = (int) (TimeLeftMills / 1000) % 60;

        String TimeLeft = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        time.setText(TimeLeft);
    }
}