package com.example.favoriteshoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initInfoButton();

        ShoeDataSource ds = new ShoeDataSource(this);
        ArrayList<Shoe> shoes;
        try {
            ds.open();
            shoes = ds.getShoes();
            ds.close();
            Toast.makeText(this, "shoe size = " + shoes.size(), Toast.LENGTH_LONG).show();
            SharedPreferences sharedPref = getSharedPreferences("MyShoeListPreferences",
                    Context.MODE_PRIVATE);
            RecyclerView shoeList = findViewById(R.id.rvShoes);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            shoeList.setLayoutManager(layoutManager);
            ShoeAdapter shoeAdapter = new ShoeAdapter(shoes);
            shoeAdapter.setOnClickListener(onClickListener);
            shoeList.setAdapter(shoeAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error retrieving shoes", Toast.LENGTH_LONG).show();
        }


    }

    private void initInfoButton() {
        Button infoButton = findViewById(R.id.infoButton);
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}