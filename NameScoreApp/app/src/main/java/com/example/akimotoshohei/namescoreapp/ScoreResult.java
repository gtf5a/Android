package com.example.akimotoshohei.namescoreapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Random;

public class ScoreResult extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_result);

        Intent intent = getIntent();
        String name = intent.getStringExtra(MyForm.EXTRA_NAME);
        TextView nameLabel = (TextView) findViewById(R.id.nameLabel);
        nameLabel.setText(name + "の点数は…");

        Random randomGenerator = new Random();
        // 0-100のスコアを表示
        int score = randomGenerator.nextInt(101);
        TextView scoreLabel = (TextView) findViewById(R.id.scoreLabel);
        scoreLabel.setText(String.valueOf(score) + "点です！！");
    }
}
