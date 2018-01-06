package com.example.postopgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.*;
import com.google.firebase.auth.*;

public class Doc_Menu extends AppCompatActivity
{
    private TextView welcome;
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private User user;
    private static final String TAG = "Doc_Menu";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc__menu);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        welcome = findViewById(R.id.titleDocMenu);
        String userId = auth.getUid();

        mDatabase.child("Physicians").child(userId).child("info").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                user = dataSnapshot.getValue(User.class);
                String text = "Welcome, Dr. " + user.getName();
                welcome.setText(text);
                //Log.d(TAG, "User name: " + user.getName() + ", email " + user.getEmail());
            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void toPhysInf(View view)
    {
        Intent intent = new Intent(this, PhysInf.class);
        startActivity(intent);
    }

    public void toNewOp(View view)
    {
        Intent intent = new Intent(Doc_Menu.this, NewOp.class);
        startActivity(intent);
    }

    public void toManageOps(View view)
    {
        Intent intent = new Intent(Doc_Menu.this, ManageOps.class);
        startActivity(intent);
    }
}
