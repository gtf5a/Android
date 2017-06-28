package com.example.akimotoshohei.sample2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void changeHello(View view){
        TextView tv = (TextView) findViewById(R.id.textViewHello);
        String[] results = {
                "Hello!",
                "Hola!",
                "你好!",
                "アニョハセヨ!",
                "Xin chào!",
                "Bonjour!",
                "Ciao!",
                "Guten tag!",
                "Olá!",
                "Moi!"
        };

        Random randomGenerator = new Random();
        int num = randomGenerator.nextInt(results.length);

        tv.setText(results[num]);
    }
}
