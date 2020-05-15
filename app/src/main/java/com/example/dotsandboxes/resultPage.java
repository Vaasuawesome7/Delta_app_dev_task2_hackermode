package com.example.dotsandboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

public class resultPage extends AppCompatActivity {

    private String[] names;
    private int[] scores;
    private SoundPool sound_effects;
    private Vector<Integer> winners;
    private int player_no, max, button_sound_int;
    private TextView result;
    private ImageView my_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        scores = new int[6];
        result = findViewById(R.id.result1);
        winners = new Vector<>();
        my_image = findViewById(R.id.myImage);
        if (getIntent().getExtras()!= null) {
            System.out.println("ENTERED");
            scores = getIntent().getExtras().getIntArray("scores");
            names = getIntent().getExtras().getStringArray("names");
            player_no = getIntent().getExtras().getInt("players");
        }
        get_max();
        get_winners();
        update_text();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();
            sound_effects = new SoundPool.Builder()
                    .setMaxStreams(1)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else {
            sound_effects = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        }
        button_sound_int = sound_effects.load(this, R.raw.button, 1);
        animate_image();
    }

    private void get_winners() {
        for (int i = 0; i < player_no; i++) {
            if (scores[i] == scores[max])
                winners.add(i);
        }
    }

    private void get_max() {
        max = 0;
        for (int i = 0; i < player_no; i++) {
            if (scores[i]>scores[max])
                max = i;
        }
    }

    private void update_text() {
        if (winners.size() == 1)
            result.setText(names[winners.get(0)]);
        else {
            String text = "";
            for(int i:winners) {
                text = text + names[i] + "\n";
            }
            result.setText(text);
        }
    }

    public void restart(View view) {
        sound_effects.play(button_sound_int,1,1,0,0,1);
        startActivity(new Intent(getApplicationContext(), chosePage.class));
        finish();
    }

    private void animate_image() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bottom_to_top);
        my_image.startAnimation(animation);
    }
}
