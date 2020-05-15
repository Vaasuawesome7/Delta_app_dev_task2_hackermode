package com.example.dotsandboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class chosePage extends AppCompatActivity {

    private EditText nameET, sizeET, numET;
    private Button confirm_player, confirm_names, confirm_size, start;
    private TextView player_counter;
    private SoundPool sound_effects;
    private int button_sound_int;
    private String[] names;
    private int grid_size,k;
    private int number_of_players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chose_page);
        names = new String[6];
        player_counter = findViewById(R.id.player_name);
        confirm_player = findViewById(R.id.Confirm);
        confirm_names = findViewById(R.id.confirm_name);
        confirm_size = findViewById(R.id.confirm_grid_size);
        nameET = findViewById(R.id.name_ET);
        sizeET = findViewById(R.id.size_grid);
        numET = findViewById(R.id.editText);
        start = findViewById(R.id.start);
        k = 0;

        //
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
    }

    public void confirm_player(View view) {
        String text = numET.getText().toString();
        if (!text.equals("")) {
            sound_effects.play(button_sound_int,1,1,0,0,1);
            number_of_players = Integer.parseInt(text);
            if (number_of_players <= 6 && number_of_players>=2) {
                confirm_player.setVisibility(View.INVISIBLE);
                numET.setVisibility(View.INVISIBLE);
                confirm_names.setVisibility(View.VISIBLE);
                nameET.setVisibility(View.VISIBLE);
                player_counter.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(this, "Please enter a value between 2-6", Toast.LENGTH_SHORT).show();
                numET.setText("");
            }
        }
        else
            Toast.makeText(this, "Please enter a value between 2-6", Toast.LENGTH_SHORT).show();
    }

    public void confirm_name(View view){
        String text = nameET.getText().toString();
        if (text.equals("")) {
            Toast.makeText(this, "Please enter a name and then confirm", Toast.LENGTH_SHORT).show();
            return;
        }
        if (k < number_of_players) {
            sound_effects.play(button_sound_int,1,1,0,0,1);
            names[k] = text;
            nameET.setText("");
            k++;
            update_text(k+1);
            if (k==number_of_players) {
                confirm_names.setVisibility(View.INVISIBLE);
                nameET.setVisibility(View.INVISIBLE);
                sizeET.setVisibility(View.VISIBLE);
                confirm_size.setVisibility(View.VISIBLE);
                player_counter.setVisibility(View.INVISIBLE);
            }
        }
    }

    public void confirm_grid_size(View view) {
        String text = sizeET.getText().toString();
        if (!text.equals("")) {
            sound_effects.play(button_sound_int,1,1,0,0,1);
            grid_size = Integer.parseInt(text);
            if (grid_size>=3 && grid_size<=12) {
                confirm_player.setVisibility(View.INVISIBLE);
                sizeET.setVisibility(View.INVISIBLE);
                confirm_size.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);
            }
            else {
                Toast.makeText(this, "Please enter a value between 3-12", Toast.LENGTH_SHORT).show();
                sizeET.setText("");
            }
        }
    }

    private void update_text(int n) {
        player_counter.setText("Player " + n + " name:-");
    }

    public void start_game(View view) {
        sound_effects.play(button_sound_int,1,1,0,0,1);
        Intent i = new Intent(getApplicationContext(), gamePage.class);
        i.putExtra("names", names);
        i.putExtra("grid", grid_size);
        i.putExtra("players", number_of_players);
        startActivity(i);
        finish();
    }
}
