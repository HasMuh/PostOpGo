package com.hasan.postopgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class Legal extends AppCompatActivity
{
    private WebView dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_legal);
        dialog = (WebView)findViewById(R.id.legalView);

        String docType = "";
        Bundle extras = getIntent().getExtras();
        if(extras != null)
        {
            docType = extras.getString("DocType");
        }

        if(docType.equals("Privacy Policy"))
        {
            dialog.loadUrl("file:///android_asset/PostOpGo Privacy Policy.html");
        }

        if(docType.equals("Disclaimer"))
        {
            dialog.loadUrl("file:///android_asset/PostOpGo Disclaimer.html");
        }

        if(docType.equals("EULA"))
        {
            dialog.loadUrl("file:///android_asset/EULA - PostOpGo.html");
        }
    }
}
