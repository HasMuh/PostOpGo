package com.example.postopgo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class FaqSinus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_sinus);
        WebView view = (WebView)findViewById(R.id.fillableFaqSin);
        view.setBackgroundColor(Color.TRANSPARENT);
        view.loadUrl("file:///android_asset/Sinus Surgery_FAQ");
    }
}
