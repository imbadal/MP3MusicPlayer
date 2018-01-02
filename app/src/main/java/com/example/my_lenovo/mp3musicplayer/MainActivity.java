package com.example.my_lenovo.mp3musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.marcinmoskala.arcseekbar.ArcSeekBar;


public class MainActivity extends AppCompatActivity {
    private static final int CODE_DRAW_OVER_OTHER_APP_PERMISSION = 2084;

    int i = 0;
    ImageView Play;
    ImageView Favourite;

    /**
     * Variable declaration of ArcSeekBar
     * Start
     */
    ArcSeekBar gradientSeek;

    /**
     * Variable declaration of ArcSeekBar
     * End
     */
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Check if the application has draw over other apps permission or not?
        //This permission is by default available for API<23. But for API > 23
        //you have to ask for the permission in runtime.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !Settings.canDrawOverlays(this)) {


            //If the draw over permission is not available open the settings screen
            //to grant the permission.
            Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                    Uri.parse("package:" + getPackageName()));
            startActivityForResult(intent, CODE_DRAW_OVER_OTHER_APP_PERMISSION);
        } else {
            initializeView();
        }

        /**
         *ArcSeekBar Code
         * Start
         */

        gradientSeek = (ArcSeekBar) findViewById(R.id.gradientSeekbar);

        int[] colorArray = getResources().getIntArray(R.array.gradient);
        gradientSeek.setProgressGradient(colorArray);

        /**
         *ArcSeekBar Code
         * End
         */

        Play = (ImageView) findViewById(R.id.play_pause);
        final MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.swagseswagat);
        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()) {
                    mp.pause();
                    Play.setBackgroundResource(R.drawable.ic_play_circle_outline_white_48dp);
                } else {
                    mp.start();
                    Play.setBackgroundResource(R.drawable.ic_pause_circle_outline_white_48dp);
                }

            }
        });

        Favourite = (ImageView) findViewById(R.id.favourite);
        Favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == 0) {
                    Favourite.setBackgroundResource(R.drawable.ic_favorite_white_48dp);
                    i = 1;
                } else {
                    Favourite.setBackgroundResource(R.drawable.ic_favorite_border_white_48dp);
                    i = 0;
                }
            }
        });
    }

    private void initializeView() {
        findViewById(R.id.minimize).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService(new Intent(MainActivity.this, FloatingViewService.class));
                finish();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CODE_DRAW_OVER_OTHER_APP_PERMISSION) {
            //Check if the permission is granted or not.
            if (resultCode == RESULT_OK) {
                initializeView();
            } else { //Permission is not available
                Toast.makeText(this,
                        "Draw over other app permission not available. Closing the application",
                        Toast.LENGTH_SHORT).show();

                finish();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
