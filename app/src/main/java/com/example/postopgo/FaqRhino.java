package com.example.postopgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public class FaqRhino extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq_rhino);
        TextView view = (TextView)findViewById(R.id.fillable);
        final InputStream stream = getResources().openRawResource(R.raw.fillerhtml);
        final String text;
        try
        {
            text = IOUtils.toString(stream, "UTF-8");
        }
        catch(IOException e) {
            throw new RuntimeException(e);
        }

        view.setText(Html.fromHtml(text));
        view.setMovementMethod(new ScrollingMovementMethod());
    }
}
