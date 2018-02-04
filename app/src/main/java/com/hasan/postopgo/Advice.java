package com.hasan.postopgo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Advice extends AppCompatActivity
{
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private TextView title, text;
    private String uid, code, type;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            uid = extras.getString("UID");
            code = extras.getString("CODE");
            type = extras.getString("TYPE");
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        title = findViewById(R.id.AdviceTitle);
        text = findViewById(R.id.AdviceText);
        text.setMovementMethod(new ScrollingMovementMethod());

        mDatabase.child("Physicians").child(uid).child("operation_codes").child(code).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String opName = dataSnapshot.child("name").getValue(String.class);
                String advice = dataSnapshot.child("info").child(type).getValue(String.class);
                title.setText(opName);
                text.setText(advice);
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });
    }
}
