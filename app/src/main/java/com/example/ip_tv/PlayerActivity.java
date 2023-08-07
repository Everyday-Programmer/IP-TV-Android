package com.example.ip_tv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.ui.StyledPlayerView;

import java.util.List;

public class PlayerActivity extends AppCompatActivity {
    StyledPlayerView playerView;
    ExoPlayer exoPlayer;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        url = getIntent().getStringExtra("url");

        playerView = findViewById(R.id.playerView);

        DefaultTrackSelector defaultTrackSelector = new DefaultTrackSelector(PlayerActivity.this);
        defaultTrackSelector.setParameters(defaultTrackSelector.buildUponParameters().setMaxVideoSize(200, 200));

        exoPlayer = new SimpleExoPlayer.Builder(this).setTrackSelector(defaultTrackSelector).build();
        MediaItem mediaItem = MediaItem.fromUri(url);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.setPlayWhenReady(true);
        playerView.setPlayer(exoPlayer);
    }
}