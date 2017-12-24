package com.example.my_lenovo.mp3musicplayer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.marcinmoskala.arcseekbar.ArcSeekBar;

public class MainActivity extends AppCompatActivity {


    /**
     *Variable declaration of ArcSeekBar
     * Start
     */
    ArcSeekBar gradientSeek;

    /**
     *Variable declaration of ArcSeekBar
     * End
     */
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



    }



}
