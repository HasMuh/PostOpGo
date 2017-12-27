package com.example.postopgo;

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
import com.google.firebase.auth.FirebaseAuth;

public class Doc_Forgot extends AppCompatActivity
{
    private EditText email;
    private Button send;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc__forgot);
        email = (EditText) findViewById(R.id.emailForg);
        send = (Button) findViewById(R.id.confirmForg);
        auth = FirebaseAuth.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String emaiL = email.getText().toString().trim();

                if (TextUtils.isEmpty(emaiL)) {
                    Toast.makeText(getApplication(), "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.sendPasswordResetEmail(emaiL).addOnCompleteListener(new OnCompleteListener<Void>()
                {
                    @Override
                    public void onComplete(@NonNull Task<Void> task)
                    {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(Doc_Forgot.this, "Instructions Sent!", Toast.LENGTH_SHORT).show();
                        }

                        else
                        {
                            Toast.makeText(Doc_Forgot.this, "Error sending email. Try again", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}
