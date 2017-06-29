package com.example.akimotoshohei.mybrowserapp;

import android.annotation.SuppressLint;
import android.os.PatternMatcher;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private WebView myWebView;
    private EditText urlText;

    private static final String INITIAL_WEBSITE = "https://www.google.co.jp";

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myWebView = (WebView) findViewById(R.id.myWebView);
        urlText = (EditText) findViewById(R.id.urlText);

        // ウェブサイトをこのアプリ内で開く
        myWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                getSupportActionBar().setSubtitle(view.getTitle());
                urlText.setText(url);
            }
        });
        // JavaScript 有効化
        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.loadUrl(INITIAL_WEBSITE);
    }

    // urlボックスタップすると元あったものが消えるよ！
    public void clearUrl(View view){
        urlText.setText("");
    }

    // url入力できるよ！
    public void showWebsite(View view){
        String url = urlText.getText().toString().trim();

        // urlの妥当性をチェックするよ！マッチしないとエラーだよ！
        if(!Patterns.WEB_URL.matcher(url).matches()) {
            urlText.setError("Invalid URL");
        } else {
            // http://から始まらなかったらつけてあげるよ！
            if(!url.startsWith("http://") && !url.startsWith("https://")) {
                url = "http://" +url;
            }
            myWebView.loadUrl(url);
        }

    }

    // 端末のバックボタンがブラウザの戻る機能になるよ！
    @Override
    public void onBackPressed() {
        if (myWebView.canGoBack()) {
            myWebView.goBack();
            return;
        }
        super.onBackPressed();
    }

    // アクティビティのメモリ管理用だよ！
    @Override
    protected void onDestroy(){
        super.onDestroy();
        if (myWebView != null) {
            myWebView.stopLoading();
            myWebView.setWebViewClient(null);
            myWebView.destroy();
        }
        myWebView = null;
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu){
        MenuItem forwardItem = (MenuItem) menu.findItem(R.id.action_forward);
        MenuItem backItem = (MenuItem) menu.findItem(R.id.action_back);

        forwardItem.setEnabled(myWebView.canGoForward());
        backItem.setEnabled(myWebView.canGoBack());
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        switch (id) {
            case R.id.action_reload:
                myWebView.reload();
                return true;
            case R.id.action_forward:
                myWebView.goForward();
                return true;
            case R.id.action_back:
                myWebView.goBack();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

}
