package com.hasan.postopgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


//Contains login
public class Welcome_Screen extends AppCompatActivity
{
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private EditText passcodeEntry;
    private Button conf;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome__screen);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        passcodeEntry = (EditText) findViewById(R.id.editText);
        conf = (Button) findViewById(R.id.button);

        conf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String passcode = passcodeEntry.getText().toString().trim();
                if (TextUtils.isEmpty(passcode))
                {
                    Toast.makeText(getApplicationContext(), "Enter Passcode", Toast.LENGTH_SHORT).show();
                    return;
                }

                mDatabase.child("allCodes").child(passcode).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        if(dataSnapshot.exists())
                        {
                            String userId = (String) dataSnapshot.child("physicianId").getValue();
                            Intent i = new Intent(Welcome_Screen.this, MainActivity.class);
                            i.putExtra("CODE", passcode);
                            i.putExtra("UID", userId);
                            startActivity(i);
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(), "Invalid passcode", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError)
                    {
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
