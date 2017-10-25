package com.example.postopgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toFAQ(View view)
    {
        Intent intent = new Intent(this, FaqRhino.class);
        startActivity(intent);
    }

    public void toDailyCare(View view)
    {
        Intent intent = new Intent(this, DCRhino.class);
        startActivity(intent);
    }

    public void toContact(View view)
    {

    }

    public void toRestrictions(View view)
    {

    }

    public void toMeds(View view)
    {

    }
}

