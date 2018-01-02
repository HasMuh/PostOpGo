package com.example.postopgo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class NewOp extends AppCompatActivity
{
    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private EditText passcodeEntry, opNameEntry;
    private Button confNewOp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_op);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        passcodeEntry = (EditText) findViewById(R.id.NewOpPw);
        opNameEntry = (EditText) findViewById(R.id.NewOpName);
        confNewOp = (Button) findViewById(R.id.ConfNewOp);

        confNewOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String opName = opNameEntry.getText().toString().trim();
                final String passcode = passcodeEntry.getText().toString().trim();

                if (TextUtils.isEmpty(opName))
                {
                    Toast.makeText(getApplicationContext(), "Enter Operation Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(passcode))
                {
                    Toast.makeText(getApplicationContext(), "Enter Passcode", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (passcode.length() != 8)
                {
                    Toast.makeText(getApplicationContext(), "Passcode must be 8-digits", Toast.LENGTH_SHORT).show();
                    return;
                }

                String UserId = auth.getUid();
                mDatabase.child(UserId).child(passcode).child("Operation Name").setValue(opName);
                String text = opName + " is now available in \"Manage Operations\"";
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(NewOp.this, Doc_Menu.class));
                finish();
            }
        });
    }
}
