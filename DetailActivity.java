package com.example.traveldiary;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class DetailActivity extends AppCompatActivity {


    TextView detailDesc, detailTitle, detailLang;

    ImageView detailImage;

    FloatingActionButton deleteButton, editButton;

    String key = "";

    String imageUrl = "";


    private EditText commentInput;

    private ListView commentList;

    private ArrayList<String> comments;

    private ArrayAdapter<String> commentsAdapter;

    private boolean isImage = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail);



        detailDesc = findViewById(R.id.detailDesc);

        detailImage = findViewById(R.id.detailImage);

        detailTitle = findViewById(R.id.detailTitle);

        deleteButton = findViewById(R.id.deleteButton);

        editButton = findViewById(R.id.editButton);

        detailLang = findViewById(R.id.detailLang);



        Bundle bundle = getIntent().getExtras();


        if(bundle != null){


            detailDesc.setText(
                    bundle.getString("Description")
            );


            detailTitle.setText(
                    bundle.getString("Title")
            );


            detailLang.setText(
                    bundle.getString("Language")
            );


            key = bundle.getString("Key");


            imageUrl = bundle.getString("Image");


            Glide.with(this)
                    .load(bundle.getString("Image"))
                    .into(detailImage);

        }



        deleteButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                final DatabaseReference reference =
                        FirebaseDatabase.getInstance()
                                .getReference("Android Tutorials");


                FirebaseStorage storage =
                        FirebaseStorage.getInstance();


                StorageReference storageReference =
                        storage.getReferenceFromUrl(imageUrl);


                storageReference.delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {


                            @Override
                            public void onSuccess(Void unused) {


                                reference.child(key)
                                        .removeValue();


                                Toast.makeText(
                                        DetailActivity.this,
                                        "Deleted",
                                        Toast.LENGTH_SHORT
                                ).show();


                                startActivity(
                                        new Intent(
                                                getApplicationContext(),
                                                HomeActivity.class
                                        )
                                );


                                finish();

                            }

                        });

            }

        });