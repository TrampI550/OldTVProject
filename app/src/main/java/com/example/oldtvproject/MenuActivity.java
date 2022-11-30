package com.example.oldtvproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
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
    ImageView levels_back;
    ViewGroup.LayoutParams settings;
    LinearLayout levels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        levels = findViewById(R.id.levels);
        levels_back = findViewById(R.id.levels_back);
        level_1 = findViewById(R.id.level_1);
        level_2 = findViewById(R.id.level_2);

        level_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        level_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MenuActivity.this, MainActivity.class);
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
                registerForContextMenu(level_2);
                Animation anim3 = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.levels_alpha);
                level_2.startAnimation(anim3);
            }
        });


        Bundle arguments = getIntent().getExtras();
    }
}