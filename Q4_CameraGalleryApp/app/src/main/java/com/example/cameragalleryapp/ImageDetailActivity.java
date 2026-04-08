package com.example.cameragalleryapp;

import android.app.AlertDialog;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.*;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageDetailActivity extends AppCompatActivity {

    String path;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        ImageView image = findViewById(R.id.imageView);
        TextView details = findViewById(R.id.details);
        Button delete = findViewById(R.id.deleteBtn);

        path = getIntent().getStringExtra("path");
        assert path != null;
        File file = new File(path);

        image.setImageBitmap(BitmapFactory.decodeFile(path));

        String info = "Name: " + file.getName() +
                "\nPath: " + file.getAbsolutePath() +
                "\nSize: " + file.length()/1024 + " KB" +
                "\nDate: " + new SimpleDateFormat("dd-MM-yyyy HH:mm")
                .format(new Date(file.lastModified()));

        details.setText(info);

        delete.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Delete")
                    .setMessage("Are you sure?")
                    .setPositiveButton("Yes", (d, w) -> {
                        file.delete();
                        Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });
    }
}