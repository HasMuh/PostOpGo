package com.example.postopgo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.webkit.WebView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class FaqRhino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_rhino);
        WebView view = (WebView)findViewById(R.id.fillable);
        view.setBackgroundColor(Color.TRANSPARENT);
        view.loadUrl("file:///android_asset/fillerhtml.html");
    }
}
