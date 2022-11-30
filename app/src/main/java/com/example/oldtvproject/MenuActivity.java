package com.example.oldtvproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MenuActivity extends AppCompatActivity {

    ImageButton tv;
    ImageView back_levels;
    ImageView level_1;
    ImageView level_2;
    ImageView level_3;
    ImageView levels_back;
    ViewGroup.LayoutParams settings;
    LinearLayout levels;
    Boolean endValue;
    int level;
    int gol;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        level = PreferenceManager.getDefaultSharedPreferences(this)
                .getInt("level", 0);
        gol = PreferenceManager.getDefaultSharedPreferences(this)
                .getInt("gol", 0);
        System.out.println(level);
        System.out.println(gol);
        endValue = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("endValue", false);
        Bundle arguments = getIntent().getExtras();
        levels = findViewById(R.id.levels);
        levels_back = findViewById(R.id.levels_back);
        level_1 = findViewById(R.id.level_1);
        level_2 = findViewById(R.id.level_2);
        level_3 = findViewById(R.id.level_3);

        System.out.println(arguments);
        if (arguments != null) {
            endValue = arguments.getBoolean("endValue");
            level = Math.max(level, arguments.getInt("level"));
            gol = Math.max(gol, arguments.getInt("gol"));
            System.out.println(gol);
            PreferenceManager.getDefaultSharedPreferences(MenuActivity.this)
                    .edit().putInt("level", level).putInt("gol", gol).putBoolean("end", endValue).apply();
        }

        level_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra("level", 0);
                startActivity(intent);
            }
        });

        level_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra("level", 1);
                startActivity(intent);
            }
        });

        level_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                intent.putExtra("level", 2);
                startActivity(intent);
            }
        });

        tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tv.setEnabled(false);

                registerForContextMenu(levels_back);
                Animation anim2 = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.levels);
                levels_back.setVisibility(View.VISIBLE);
                levels_back.startAnimation(anim2);
                registerForContextMenu(level_1);
                Animation anim1 = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.levels_alpha);
                level_1.startAnimation(anim1);
                level_1.setEnabled(false);
                registerForContextMenu(level_2);
                level_2.startAnimation(anim1);
                level_2.setEnabled(false);
                registerForContextMenu(level_3);
                level_3.startAnimation(anim1);
                level_3.setEnabled(false);
            }
        });


    }
}