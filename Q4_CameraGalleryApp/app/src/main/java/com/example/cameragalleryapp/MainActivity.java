package com.example.cameragalleryapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE = 1;
    String currentPhotoPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button capture = findViewById(R.id.btnCapture);
        Button gallery = findViewById(R.id.btnGallery);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        capture.setOnClickListener(v -> openCamera());

        gallery.setOnClickListener(v ->
                startActivity(new Intent(this, GalleryActivity.class)));
    }

    private void openCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (intent.resolveActivity(getPackageManager()) != null) {
            File photoFile = null;

            try {
                photoFile = createImageFile();
            } catch (IOException e) { }

            if (photoFile != null) {
                Uri uri = FileProvider.getUriForFile(this,
                        getPackageName() + ".provider", photoFile);

                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
                startActivityForResult(intent, REQUEST_IMAGE);
            }
        }
    }

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());

        File storageDir = new File(Environment.getExternalStorageDirectory(), "MyPhotos");
        if (!storageDir.exists()) storageDir.mkdirs();

        File image = File.createTempFile("IMG_" + timeStamp, ".jpg", storageDir);
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }
}