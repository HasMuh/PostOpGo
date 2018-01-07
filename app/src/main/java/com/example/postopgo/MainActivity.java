package com.example.postopgo;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public class MainActivity extends AppCompatActivity
{
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private String type, code, uid;
    private TextView title, office;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            uid = extras.getString("UID");
            code = extras.getString("CODE");
        }
        title = (TextView) findViewById(R.id.MainTitle);
        office = (TextView) findViewById(R.id.OfficeNameClient);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();

        mDatabase.child("Physicians").child(uid).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String officeN = dataSnapshot.child("practiceName").getValue(String.class);
                String opName = dataSnapshot.child("operation_codes").child(code).child("name").getValue(String.class);
                office.setText(officeN);
                title.setText(opName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });


    }

    public void toFAQ(View view)
    {
        Intent intent = new Intent(this, Advice.class);
        intent.putExtra("CODE", code);
        intent.putExtra("UID", uid);
        intent.putExtra("TYPE", "FAQ");
        startActivity(intent);
    }

    public void toDailyCare(View view)
    {
        Intent intent = new Intent(this, Advice.class);
        intent.putExtra("CODE", code);
        intent.putExtra("UID", uid);
        intent.putExtra("TYPE", "dailyCare");
        startActivity(intent);
    }

    public void toContact(View view)
    {
        Intent intent = new Intent(this, Advice.class);
        intent.putExtra("CODE", code);
        intent.putExtra("UID", uid);
        startActivity(intent);
    }

    public void toRestrictions(View view)
    {
        Intent intent = new Intent(this, Advice.class);
        intent.putExtra("CODE", code);
        intent.putExtra("UID", uid);
        intent.putExtra("TYPE", "restrictions");
        startActivity(intent);
    }

    public void toMeds(View view)
    {
        Intent intent = new Intent(this, Advice.class);
        intent.putExtra("CODE", code);
        intent.putExtra("UID", uid);
        intent.putExtra("TYPE", "medications");
        startActivity(intent);
    }
}

