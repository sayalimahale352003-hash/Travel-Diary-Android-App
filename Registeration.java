package com.example.traveldiary;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Registeration extends AppCompatActivity {

    TextInputEditText editTextEmail, editTextPassword, username;

    Button buttonReg;

    FirebaseAuth mAuth;

    ProgressBar progressBar;

    TextView textView;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_registeration);


        mAuth = FirebaseAuth.getInstance();


        editTextEmail = findViewById(R.id.email);

        editTextPassword = findViewById(R.id.password);

        buttonReg = findViewById(R.id.btn_Register);

        progressBar = findViewById(R.id.progressBar);

        textView = findViewById(R.id.loginNow);



        textView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                Intent intent = new Intent(
                        getApplicationContext(),
                        Login.class
                );

                startActivity(intent);

                finish();

            }
        });



        buttonReg.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);


                String email, password;


                email = String.valueOf(editTextEmail.getText());

                password = String.valueOf(editTextPassword.getText());



                if (TextUtils.isEmpty(email)) {

                    Toast.makeText(
                            Registeration.this,
                            "Enter email",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }



                if (TextUtils.isEmpty(password)) {

                    Toast.makeText(
                            Registeration.this,
                            "Enter password",
                            Toast.LENGTH_SHORT
                    ).show();

                    return;
                }



                mAuth.createUserWithEmailAndPassword(email, password)

                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {


                                progressBar.setVisibility(View.GONE);


                                if (task.isSuccessful()) {


                                    Toast.makeText(
                                            Registeration.this,
                                            "Account Created.",
                                            Toast.LENGTH_SHORT
                                    ).show();



                                    Intent intent = new Intent(
                                            getApplicationContext(),
                                            Login.class
                                    );


                                    startActivity(intent);

                                    finish();



                                } else {


                                    Toast.makeText(
                                            Registeration.this,
                                            "Authentication failed.",
                                            Toast.LENGTH_SHORT
                                    ).show();

                                }

                            }

                        });

            }

        });

    }

}