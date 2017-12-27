package com.example.postopgo;


import android.app.UiModeManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Doc_Screen extends AppCompatActivity {
    private FirebaseAuth auth;
    private Button signIn;
    private EditText emailEntry;
    private EditText pwEntry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        auth = FirebaseAuth.getInstance();

        /**if(auth.getCurrentUser() != null)
        {
            startActivity(new Intent(Doc_Screen.this, Doc_Menu.class));
            finish();
        }*/

        setContentView(R.layout.activity_doc__screen);
        signIn = (Button) findViewById(R.id.Login);
        emailEntry = (EditText) findViewById(R.id.email);
        pwEntry = (EditText) findViewById(R.id.password);

        signIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String email = emailEntry.getText().toString().trim();
                final String pw = pwEntry.getText().toString().trim();

                if(TextUtils.isEmpty(email))
                {
                    Toast.makeText(getApplicationContext(), "Enter your email address", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(pw))
                {
                    Toast.makeText(getApplicationContext(), "Enter your password", Toast.LENGTH_SHORT).show();
                    return;
                }

                auth.signInWithEmailAndPassword(email,pw)
                        .addOnCompleteListener(Doc_Screen.this, new OnCompleteListener<AuthResult>()
                {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task)
                    {
                        //Toast.makeText(Doc_Screen.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        if(!task.isSuccessful())
                        {
                            Toast.makeText(Doc_Screen.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            startActivity(new Intent(Doc_Screen.this, Doc_Menu.class));
                            finish();
                        }
                    }
                });
            }
        });
    }

    public void toRegister(View view)
    {
        Intent intent = new Intent(this, Doc_Registry.class);
        startActivity(intent);
    }

    public void toForgot(View view)
    {
        Intent intent = new Intent(this, Doc_Forgot.class);
        startActivity(intent);
    }
}
