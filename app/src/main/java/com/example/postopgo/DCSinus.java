package com.example.postopgo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class DCSinus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcsinus);
        WebView view = (WebView)findViewById(R.id.fillableSin);
        view.setBackgroundColor(Color.TRANSPARENT);
        view.loadUrl("file:///android_asset/Sinus Surgery_DailyCare");
    }
}
