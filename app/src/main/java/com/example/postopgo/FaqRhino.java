package com.example.postopgo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class FaqRhino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_rhino);
        TextView view = (TextView)findViewById(R.id.fillable);
        String line = "";

        try
        {
            InputStream is = getAssets().open("appFiller.txt");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            line = new String(buffer);

        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }
        view.setText(line);
        view.setMovementMethod(new ScrollingMovementMethod());
    }
}
