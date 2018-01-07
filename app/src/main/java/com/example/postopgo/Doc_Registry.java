package com.example.postopgo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class Doc_Registry extends AppCompatActivity {

    private EditText pw, pwVerify, email, phone, name, office;
    private Button reg;
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc__registry);
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        email = (EditText) findViewById(R.id.emailReg);
        pw = (EditText) findViewById(R.id.pwReg);
        pwVerify = (EditText) findViewById(R.id.verifyReg);
        phone = (EditText) findViewById(R.id.phoneNumber);
        name = (EditText) findViewById(R.id.nameDoc);
        reg = (Button) findViewById(R.id.confirmReg);
        office = (EditText) findViewById(R.id.Office);

        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String mail = email.getText().toString().trim();
                String password = pw.getText().toString().trim();
                String passwordCheck = pwVerify.getText().toString().trim();
                final String phoneNumber = phone.getText().toString().trim();
                final String lastName = name.getText().toString().trim();
                final String officeName = office.getText().toString().trim();

                if (TextUtils.isEmpty(lastName))
                {
                    Toast.makeText(getApplicationContext(), "Enter Last Name", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(phoneNumber))
                {
                    Toast.makeText(getApplicationContext(), "Enter phone number", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(mail)) {
                    Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(passwordCheck))
                {
                    Toast.makeText(getApplicationContext(), "Re-enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(officeName))
                {
                    Toast.makeText(getApplicationContext(), "Enter office name", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!(password.equals(passwordCheck)))
                {
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(Doc_Registry.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        Toast.makeText(Doc_Registry.this, "Registration complete", Toast.LENGTH_SHORT).show();
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(Doc_Registry.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            User user = new User(lastName, mail, phoneNumber);
                            String userId = auth.getUid();
                            mDatabase.child("Physicians").child(userId).child("info").setValue(user);
                            mDatabase.child("Physicians").child(userId).child("info").child("practiceName").setValue(officeName);
                            startActivity(new Intent(Doc_Registry.this, Doc_Screen.class));
                            finish();
                        }
                    }
                });
            }
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
    }
}
