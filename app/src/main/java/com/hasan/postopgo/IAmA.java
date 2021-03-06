package com.hasan.postopgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class IAmA extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iam);
    }

    public void toPatient(View view)
    {
        Intent intent = new Intent(this,Welcome_Screen.class);
        startActivity(intent);
    }

    public void toDocScreen(View view)
    {
        Intent intent = new Intent(this, Doc_Screen.class);
        startActivity(intent);
    }

    public void toPrivacy(View view)
    {
        Intent intent = new Intent(this, Legal.class);
        intent.putExtra("DocType", "Privacy Policy");
        startActivity(intent);
    }

    public void toDisclaimer(View view)
    {
        Intent intent = new Intent(this, Legal.class);
        intent.putExtra("DocType", "Disclaimer");
        startActivity(intent);
    }

    public void toEULA(View view)
    {
        Intent intent = new Intent(this, Legal.class);
        intent.putExtra("DocType", "EULA");
        startActivity(intent);
    }
}
