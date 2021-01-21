package com.tkc.smart_shimp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tkc.smart_shimp.R;
import com.tkc.smart_shimp.ShowgraphActivity;

public class TemparatureActivity extends AppCompatActivity {

    Button btnShowgraph,btnShowlist;
    String value,temp,day;
    int n,n1;
    FirebaseDatabase database;
    DatabaseReference myRef;
    TextView Temparature,Date;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temparature);


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference();

        Temparature = findViewById(R.id.Temparature);
        Date = findViewById(R.id.Date);
        btnShowgraph = findViewById(R.id.Showgraph);
        btnShowlist = findViewById(R.id.Showlist);

        myRef.child("temp").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                // org String value = dataSnapshot.getValue().toString();
                value = dataSnapshot.getValue().toString();
                n = value.indexOf("Temperature Sensor");
                n1 = value.indexOf("Time=");

                temp = value.substring(n+36,n+40);
                day =  value.substring(n1+16,n1+24) + "   " + value.substring(n1+5,n1+15) ;

                Temparature.setText(temp + " °C");
                Date.setText(day);

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                value = dataSnapshot.getValue().toString();
                Log.d("data change", value);
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
                value = dataSnapshot.getValue().toString();
                Log.d("data delete", value);
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                value = dataSnapshot.getValue().toString();
                Log.d("data move", value);
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnShowgraph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TemparatureActivity.this, ShowgraphActivity.class);
                startActivity(i);
            }
        });

        btnShowlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(TemparatureActivity.this, ShowlistActivity.class);
                startActivity(i);
            }
        });
    }


}
