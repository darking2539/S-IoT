package com.tkc.smart_shimp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class ScreenActivity extends AppCompatActivity {
    Button btnLogout, btnTemparature, btnGPS;
    FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen);
        btnLogout = findViewById(R.id.logout);
        btnTemparature = findViewById(R.id.temparature);
        btnGPS = findViewById(R.id.GPS);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intToMain = new Intent(ScreenActivity.this, MainActivity.class);
                startActivity(intToMain);
            }
        });

        btnTemparature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScreenActivity.this,TemparatureActivity.class);
                startActivity(i);
            }
        });

        btnTemparature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ScreenActivity.this,GPS.class);
                startActivity(i);
            }
        });
    }
}