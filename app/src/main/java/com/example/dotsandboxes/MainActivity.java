package com.example.dotsandboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView belcum, rotater, my_name;
    private int begin_sound;
    private MediaPlayer mPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        belcum = findViewById(R.id.welcome);
        my_name = findViewById(R.id.credentials);
        rotater = findViewById(R.id.rotate);
        new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long l) {
                rotater.setText(String.valueOf(l/1000 + 1));
            }

            @Override
            public void onFinish() {
                start_page();
            }
        }.start();
        begin();
        mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.intro);
        mPlayer.start();
    }

    private void begin() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
        belcum.startAnimation(animation);
        Animation animation1 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sample_anim);
        my_name.startAnimation(animation1);
        Animation animation2 = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        rotater.startAnimation(animation2);
    }

    private void start_page() {
        startActivity(new Intent(this, chosePage.class));
        finish();
    }
}
