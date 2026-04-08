package com.example.cameragalleryapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.ArrayList;

public class GalleryActivity extends AppCompatActivity {

    ArrayList<String> imagePaths = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        GridView grid = findViewById(R.id.gridView);

        File folder = new File(Environment.getExternalStorageDirectory(), "MyPhotos");

        if (folder.exists()) {
            for (File file : folder.listFiles()) {
                imagePaths.add(file.getAbsolutePath());
            }
        }

        ImageAdapter adapter = new ImageAdapter(this, imagePaths);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener((parent, view, position, id) -> {
            Intent i = new Intent(this, ImageDetailActivity.class);
            i.putExtra("path", imagePaths.get(position));
            startActivity(i);
        });
    }
}