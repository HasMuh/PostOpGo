package com.example.postopgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class DCRhino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dcrhino);
        TextView view = (TextView)findViewById(R.id.fillableDC);
        String line = "";

        try
        {
            InputStream is = getAssets().open("filler2.txt");
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
