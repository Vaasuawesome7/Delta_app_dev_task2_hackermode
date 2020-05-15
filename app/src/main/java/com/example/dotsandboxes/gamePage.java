package com.example.dotsandboxes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dotsandboxes.DABLayoutView.DABLayout;

public class gamePage extends AppCompatActivity {

    private DABLayout DAB;
    private int grid_size, number_of_players;
    private String[] names;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        DAB = findViewById(R.id.dab);
        if (getIntent().getExtras() != null) {
            grid_size = getIntent().getExtras().getInt("grid");
            number_of_players = getIntent().getExtras().getInt("players");
            names = getIntent().getExtras().getStringArray("names");
        }
        DAB.initN(grid_size, number_of_players, names);
    }

    public void undo(View view) {
        DAB.undo();
    }
}
