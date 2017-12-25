package com.example.my_lenovo.mp3musicplayer;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.marcinmoskala.arcseekbar.ArcSeekBar;


public class MainActivity extends AppCompatActivity {

    int i =0;
    ImageView Play;
    ImageView Favourite;

    /**
     *Variable declaration of ArcSeekBar
     * Start
     */
    ArcSeekBar gradientSeek;

    /**
     *Variable declaration of ArcSeekBar
     * End
     */
    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

        Play= (ImageView) findViewById(R.id.play_pause);
        final MediaPlayer mp = MediaPlayer.create(MainActivity.this,R.raw.swagseswagat);
        Play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mp.isPlaying()){
                    mp.pause();
                    Play.setBackgroundResource(R.drawable.ic_play_circle_outline_white_48dp);
                }else {
                    mp.start();
                    Play.setBackgroundResource(R.drawable.ic_pause_circle_outline_white_48dp);
                }

            }
        });

        Favourite = (ImageView) findViewById(R.id.favourite);
        Favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i==0){
                    Favourite.setBackgroundResource(R.drawable.ic_favorite_white_48dp);
                    i=1;
                }else{
                    Favourite.setBackgroundResource(R.drawable.ic_favorite_border_white_48dp);
                    i=0;
                }
            }
        });
    }


}
