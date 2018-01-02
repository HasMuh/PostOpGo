package com.example.postopgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PhysInf extends AppCompatActivity
{
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private Button confirmInfo;
    private EditText emailEntry, phEntry, nameEntry;
    private final static String TAG = "PhysInf.class";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phys_inf);
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        confirmInfo = (Button) findViewById(R.id.NewInfConf);
        emailEntry = (EditText) findViewById(R.id.NewEmail);
        phEntry = (EditText) findViewById(R.id.NewPhone);
        nameEntry = (EditText) findViewById(R.id.NewName);

        String userId = auth.getUid();

        mDatabase.child(userId).addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {

                User user = dataSnapshot.getValue(User.class);
                String nameText = user.getName();
                String phText = user.getPhone();
                String emailText = user.getEmail();

                emailEntry.setText(emailText);
                phEntry.setText(phText);
                nameEntry.setText(nameText);
            }

            @Override
            public void onCancelled(DatabaseError error)
            {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        confirmInfo.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                final String mail = emailEntry.getText().toString().trim();
                final String phoneNumber = phEntry.getText().toString().trim();
                final String lastName = nameEntry.getText().toString().trim();
                String userId = auth.getUid();

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
                if (TextUtils.isEmpty(mail))
                {
                    Toast.makeText(getApplicationContext(), "Enter email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                user.updateEmail(mail).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(PhysInf.this, "Email update failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                mDatabase.child(userId).setValue(new User(lastName, mail, phoneNumber)).addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (!task.isSuccessful())
                        {
                            Toast.makeText(PhysInf.this, "Update failed", Toast.LENGTH_SHORT).show();
                        }

                        startActivity(new Intent(PhysInf.this, Doc_Menu.class));
                    }
                });
            }
        });
    }
}
