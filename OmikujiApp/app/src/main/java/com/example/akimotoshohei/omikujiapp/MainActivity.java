package com.example.akimotoshohei.omikujiapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;
import java.util.StringTokenizer;

import static android.graphics.Color.*;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void getOmikuji(View view){
        // TextView取得
        TextView tv = (TextView) findViewById(R.id.omikujiTextView);
        String[] results = {
                "大吉",
                "吉",
                "凶"
        };
        // 乱数生成
        Random randomGenerator = new Random();
        // 配列の個数でランダム生成
        int num = randomGenerator.nextInt(results.length);
        // 結果表示
//        String result = Integer.toString(num);

        // 大吉の時だけ赤で表示
        if(num ==0){
            tv.setTextColor(RED);
        } else {
            tv.setTextColor(BLACK);
        }

        tv.setText(results[num]);
    }
}
