package com.example.postopgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


//18,103,153 RGB
//Should be first screen to open, contains login
public class Welcome_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__screen);
    }

    public void verify(View view)
    {
       EditText ed1 = (EditText)findViewById(R.id.editText);
        if(ed1.getText().toString().equals("rhino01"))
        {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if(ed1.getText().toString().equals("sinus"))
        {
            Intent intent = new Intent(this, Sinus.class);
            startActivity(intent);
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Invalid Password", Toast.LENGTH_SHORT).show();
        }
    }
}
