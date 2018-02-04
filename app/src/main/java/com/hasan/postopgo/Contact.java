package com.hasan.postopgo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class Contact extends AppCompatActivity
{
    private FirebaseAuth auth;
    private DatabaseReference mDatabase;
    private String uid, code;
    public static final int MY_PERMISSIONS_REQUEST_CALL_PHONE = 474;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            uid = extras.getString("UID");
            code = extras.getString("CODE");
        }
        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
    }

    public void email(View view)
    {
        mDatabase.child("Physicians").child(uid).child("info").child("email").addValueEventListener(new ValueEventListener()
        {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                String mail = dataSnapshot.getValue(String.class);
                Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.setType("message/rfc822");
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {mail});
                PackageManager packageManager = getPackageManager();
                List<ResolveInfo> activities = packageManager.queryIntentActivities(emailIntent, 0);
                boolean isIntentSafe = activities.size() > 0;

                // Start an activity if it's safe
                if (isIntentSafe)
                {
                    startActivity(emailIntent);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void phone(View view)
    {
        if (ContextCompat.checkSelfPermission(Contact.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(Contact.this, new String[]{Manifest.permission.CALL_PHONE}, MY_PERMISSIONS_REQUEST_CALL_PHONE);
        }
        else
        {
            mDatabase.child("Physicians").child(uid).child("info").child("phoneNumber").addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    String phone = dataSnapshot.getValue(String.class);
                    String phoneNum = "tel:" + phone;
                    Uri uri = Uri.parse(phoneNum);
                    Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
                    startActivity(callIntent);
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    mDatabase.child("Physicians").child(uid).child("info").child("phoneNumber").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            String phone = dataSnapshot.getValue(String.class);
                            String phoneNum = "tel:" + phone;
                            Uri uri = Uri.parse(phoneNum);
                            Intent callIntent = new Intent(Intent.ACTION_DIAL, uri);
                            startActivity(callIntent);
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else
                {

                    Toast.makeText(Contact.this, "Permission denied", Toast.LENGTH_SHORT).show();
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}
