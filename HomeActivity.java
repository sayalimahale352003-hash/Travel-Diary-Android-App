package com.example.traveldiary;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class HomeActivity extends AppCompatActivity {

    FloatingActionButton fab;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;

    RecyclerView recyclerView;

    List<DataClass> dataList;

    MyAdapter adapter;

    SearchView searchView;

    ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        recyclerView = findViewById(R.id.recyclerView);

        fab = findViewById(R.id.fab);

        searchView = findViewById(R.id.search);

        searchView.clearFocus();

        imageView = findViewById(R.id.imageView5);



        GridLayoutManager gridLayoutManager =
                new GridLayoutManager(HomeActivity.this, 1);


        recyclerView.setLayoutManager(gridLayoutManager);



        AlertDialog.Builder builder =
                new AlertDialog.Builder(HomeActivity.this);


        builder.setCancelable(false);

        AlertDialog dialog = builder.create();

        dialog.show();



        dataList = new ArrayList<>();


        adapter = new MyAdapter(HomeActivity.this, dataList);


        recyclerView.setAdapter(adapter);



        databaseReference =
                FirebaseDatabase.getInstance()
                        .getReference("Android Tutorials");



        eventListener = databaseReference.addValueEventListener(
                new ValueEventListener() {


                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {


                        dataList.clear();


                        for (DataSnapshot itemSnapshot : snapshot.getChildren()) {


                            DataClass dataClass =
                                    itemSnapshot.getValue(DataClass.class);


                            dataClass.setKey(itemSnapshot.getKey());


                            dataList.add(dataClass);

                        }


                        adapter.notifyDataSetChanged();

                        dialog.dismiss();

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {


                        dialog.dismiss();

                    }

                });



        searchView.setOnQueryTextListener(
                new SearchView.OnQueryTextListener() {


                    @Override
                    public boolean onQueryTextSubmit(String query) {

                        return false;

                    }


                    @Override
                    public boolean onQueryTextChange(String newText) {


                        searchList(newText);

                        return true;

                    }

                });



        fab.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {


                Intent intent =
                        new Intent(HomeActivity.this,
                                UploadActivity.class);


                startActivity(intent);

            }

        });



        imageView.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent =
                        new Intent(HomeActivity.this,
                                MainActivity.class);


                startActivity(intent);

            }

        });



        ImageView imageVie = findViewById(R.id.imageView);


        imageVie.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {


                Intent intent =
                        new Intent(HomeActivity.this,
                                ProfileActivity.class);


                startActivity(intent);

            }

        });


    }



    public void searchList(String text) {


        ArrayList<DataClass> searchList = new ArrayList<>();


        for (DataClass dataClass : dataList) {


            if (dataClass.getDataTitle()
                    .toLowerCase()
                    .contains(text.toLowerCase())) {


                searchList.add(dataClass);

            }

        }


        adapter.searchDataList(searchList);

    }

}