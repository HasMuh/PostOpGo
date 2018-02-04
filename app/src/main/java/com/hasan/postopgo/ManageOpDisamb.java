package com.hasan.postopgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ManageOpDisamb extends AppCompatActivity
{
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private String opCode;
    private TextView opName, opPasscode;
    private Intent intent;
    private String code;
    private final static String TAG = "ManageOpDisamb";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_op_disamb);

        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            opCode = extras.getString("CODE");
        }
        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        opName = (TextView) findViewById(R.id.ManageThisTitle);
        opPasscode = (TextView) findViewById(R.id.ManageThisCode);
        intent = new Intent(ManageOpDisamb.this, EditOpInfo.class);

        String userId = auth.getUid();
        mDatabase.child("Physicians").child(userId).child("operation_codes").child(opCode).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                String name = dataSnapshot.child("name").getValue(String.class);
                opName.setText(name);
                code = "Passcode: " + opCode;
                opPasscode.setText(code);
            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void DCPicked(View view)
    {
        String type = "dailyCare";
        Intent i = new Intent(ManageOpDisamb.this, EditOpInfo.class);
        i.putExtra("TYPE",type);
        i.putExtra("CODE",opCode);
        startActivity(i);
    }

    public void FAQPicked(View view)
    {
        String type = "FAQ";
        Intent i = new Intent(ManageOpDisamb.this, EditOpInfo.class);
        i.putExtra("TYPE",type);
        i.putExtra("CODE",opCode);
        startActivity(i);

    }

    public void MedsPicked(View view)
    {
        String type = "medications";
        Intent i = new Intent(ManageOpDisamb.this, EditOpInfo.class);
        i.putExtra("TYPE",type);
        i.putExtra("CODE",opCode);
        startActivity(i);
    }

    public void RestPicked(View view)
    {
        String type = "restrictions";
        Intent i = new Intent(ManageOpDisamb.this, EditOpInfo.class);
        i.putExtra("TYPE",type);
        i.putExtra("CODE", opCode);
        startActivity(i);
    }

    public void PreOpPicked(View view)
    {
        String type = "preOpCare";
        Intent i = new Intent(ManageOpDisamb.this, EditOpInfo.class);
        i.putExtra("TYPE",type);
        i.putExtra("CODE",opCode);
        startActivity(i);
    }

    public void back(View view)
    {
        Intent intent = new Intent(ManageOpDisamb.this, Doc_Menu.class);
        startActivity(intent);
    }
}
