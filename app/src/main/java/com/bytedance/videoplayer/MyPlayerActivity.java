package com.bytedance.videoplayer;

import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

public class MyPlayerActivity extends AppCompatActivity {
    private VideoView myPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_player);
        myPlayer=findViewById(R.id.myPlayer);
        Uri uri=getIntent().getData();
        if(uri!=null){
            Log.d("Uri",uri.toString());
            myPlayer.setVideoURI(uri);
        }else {
            myPlayer.setVideoPath(getVideoPath(R.raw.bytedance));
        }
        myPlayer.setMediaController(new MediaController(this));
        myPlayer.start();
    }

    private String getVideoPath(int resId) {
        return "android.resource://" + this.getPackageName() + "/" + resId;
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {// 横屏
            getSupportActionBar().hide();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {// 竖屏
            getSupportActionBar().show();
        }
    }
}
