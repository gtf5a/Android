package com.example.akimotoshohei.stopwatchapp;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private Button startButton;
    private Button stopButton;
    private Button resetButton;

    private long startTime;
    private TextView timerLabel;

    private Handler handler = new Handler();
    private Runnable updateTimer;

    private long elapsedTime = 0l;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton = (Button) findViewById(R.id.buttonStart);
        stopButton = (Button) findViewById(R.id.buttonStop);
        resetButton = (Button) findViewById(R.id.buttonReset);

        timerLabel = (TextView) findViewById(R.id.timerLabel);

        startButton.setEnabled(true);
        stopButton.setEnabled(false);
        resetButton.setEnabled(false);

    }

    public void setButtonState(boolean start, boolean stop, boolean reset){
        startButton.setEnabled(start);
        stopButton.setEnabled(stop);
        resetButton.setEnabled(reset);
    }

    public void startTimer(View view){
        // startTimeの取得
        startTime= SystemClock.elapsedRealtime(); // システムが起動してからの経過時間（ms）

        // 一定時間ごとに現在の経過時間を表示
        // Handler -> Runnable(処理) -> UI
        updateTimer = new Runnable() {
            @Override
            public void run() {
                long t = SystemClock.elapsedRealtime() - startTime + elapsedTime; // ms
                SimpleDateFormat sdf = new SimpleDateFormat("mm:ss.SSS", Locale.US);
                timerLabel.setText(sdf.format(t));
                handler.removeCallbacks(updateTimer); // 質問
                handler.postDelayed(updateTimer, 10);
            }
        };
        handler.postDelayed(updateTimer, 10);

        // ボタンの操作
        setButtonState(false, true, false);
    }

    public void stopTimer(View view){
        elapsedTime += SystemClock.elapsedRealtime() - startTime;
        handler.removeCallbacks(updateTimer);
        setButtonState(true, false, true);
    }

    public void resetTimer(View view){
        elapsedTime = 0l;
        timerLabel.setText(R.string.timer_label);
        setButtonState(true, false, false);
    }

}
