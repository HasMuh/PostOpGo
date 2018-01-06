package com.example.postopgo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class EditOpInfo extends AppCompatActivity
{

    private DatabaseReference mDatabase;
    private FirebaseAuth auth;
    private TextView title;
    private EditText entry;
    private Button conf;
    private String type;
    private String code;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_op_info);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        auth = FirebaseAuth.getInstance();
        title = (TextView) findViewById(R.id.EditThisTitle);
        entry = (EditText) findViewById(R.id.EditThisEntry);
        conf = (Button) findViewById(R.id.ConfEdit);

        entry.setMovementMethod(new ScrollingMovementMethod());
        final String userId = auth.getUid();
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            type = extras.getString("TYPE");
            code = extras.getString("CODE");
        }

        mDatabase.child("Physicians").child(userId).child("operation_codes").child(code).child("info").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String current = dataSnapshot.child(type).getValue(String.class);
                entry.setText(current);
                title.setText(type.toUpperCase());
            }

            @Override
            public void onCancelled(DatabaseError databaseError)
            {

            }
        });

        conf.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String advice = entry.getText().toString();
                mDatabase.child("Physicians").child(userId).child("operation_codes").child(code).child("info").child(type).setValue(advice);
                Toast.makeText(EditOpInfo.this, "Updated " + type, Toast.LENGTH_SHORT).show();
                Intent i = new Intent(EditOpInfo.this, ManageOpDisamb.class);
                i.putExtra("TYPE",type);
                i.putExtra("CODE",code);
                startActivity(i);
            }
        });
    }
}
