package com.example.mediaplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;
    MediaPlayer mediaPlayer;
    EditText urlInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button openFile = findViewById(R.id.openFile);
        Button openUrl = findViewById(R.id.openUrl);
        Button play = findViewById(R.id.play);
        Button pause = findViewById(R.id.pause);
        Button stop = findViewById(R.id.stop);
        Button restart = findViewById(R.id.restart);

        videoView = findViewById(R.id.videoView);
        urlInput = findViewById(R.id.urlInput);

        // Open Audio File
        openFile.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(intent, 1);
        });

        // Load Video from URL
        openUrl.setOnClickListener(v -> {
            String url = urlInput.getText().toString();
            videoView.setVideoURI(Uri.parse(url));
            videoView.requestFocus();
            Toast.makeText(this, "Video Loaded", Toast.LENGTH_SHORT).show();
        });

        // Controls
        play.setOnClickListener(v -> videoView.start());

        pause.setOnClickListener(v -> videoView.pause());

        stop.setOnClickListener(v -> videoView.stopPlayback());

        restart.setOnClickListener(v -> {
            videoView.seekTo(0);
            videoView.start();
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK) {
            Uri audioUri = data.getData();

            if (mediaPlayer != null) {
                mediaPlayer.release();
            }

            mediaPlayer = MediaPlayer.create(this, audioUri);
            mediaPlayer.start();
        }
    }
}