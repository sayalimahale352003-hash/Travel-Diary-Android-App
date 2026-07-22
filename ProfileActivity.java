package com.example.traveldiary;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class ProfileActivity extends AppCompatActivity {


    TextView textView11;

    FirebaseAuth auth;

    FirebaseUser user;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);



        textView11 = findViewById(R.id.textView11);



        auth = FirebaseAuth.getInstance();


        user = auth.getCurrentUser();



        textView11.setText(user.getEmail());

    }

}