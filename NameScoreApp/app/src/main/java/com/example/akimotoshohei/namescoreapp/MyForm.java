package com.example.akimotoshohei.namescoreapp;

        import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.view.View;
        import android.widget.EditText;



public class MyForm extends AppCompatActivity {

    // 定数を使う時は名前が被らないようにパッケージ名を入れる
    public final static String EXTRA_NAME = "com.example.akimotoshohei.namescoreapp.NAME";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_form);
    }

    public void getScore(View view){
        // EditTextを取得
        EditText yourName = (EditText) findViewById(R.id.yourName);

        // EditTextの中身を取得
        // EditText型からString型に変換, trim()で前後の空白を取り除く
        String name = yourName.getText().toString().trim();

        // 中身を見て条件分岐
        if(name.equals("")){
            // 空白でエラー処理
            yourName.setError("Please enter your name!");
        } else {
            // 中身あったらOK
            Intent intent = new Intent(this, ScoreResult.class);
            intent.putExtra(EXTRA_NAME, name);
//            intent.putExtra(EXTRA_YOUR_NAME, yourName);
            startActivity(intent);
        }
    }
}
