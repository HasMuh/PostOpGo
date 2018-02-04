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

public class ManageOps extends AppCompatActivity
{

    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private EditText opNameEntry;
    private Button confOp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_ops);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        opNameEntry = (EditText) findViewById(R.id.ManageOpEntry);
        confOp = (Button) findViewById(R.id.ConfManageOps);

        confOp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                final String opName = opNameEntry.getText().toString().trim();
                if (TextUtils.isEmpty(opName))
                {
                    Toast.makeText(getApplicationContext(), "Enter Operation Name", Toast.LENGTH_SHORT).show();
                    return;
                }

                String userId = auth.getUid();
                mDatabase.child("Physicians").child(userId).child("operation_codes").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot)
                    {
                        for (DataSnapshot d : dataSnapshot.getChildren())
                        {
                            if(d.exists())
                            {
                                if (((String)d.child("name").getValue()).equalsIgnoreCase(opName))
                                {
                                    String name = d.getKey();
                                    Intent i = new Intent(ManageOps.this, ManageOpDisamb.class);
                                    Toast.makeText(getApplicationContext(), "     Operation found     ", Toast.LENGTH_SHORT).show();
                                    i.putExtra("CODE",name);
                                    startActivity(i);
                                    break;
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(), "Operation not found", Toast.LENGTH_SHORT).show();
                                }
                            }
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
