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
import android.widget.TextView;

public class MenuActivity extends AppCompatActivity {

    ImageButton tv;
    ImageView back_levels;
    ImageView level_1;
    ImageView level_2;
    ImageView level_3;
    TextView records;
    ImageView levels_back;
    ViewGroup.LayoutParams settings;
    LinearLayout levels;
    Boolean endValue;
    CountriesClass countries_obj;
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
        endValue = PreferenceManager.getDefaultSharedPreferences(this)
                .getBoolean("endValue", false);
        Bundle arguments = getIntent().getExtras();

        levels = findViewById(R.id.levels);
        records = findViewById(R.id.records);
        levels_back = findViewById(R.id.levels_back);
        level_1 = findViewById(R.id.level_1);
        level_2 = findViewById(R.id.level_2);
        level_3 = findViewById(R.id.level_3);
        countries_obj = new CountriesClass();

        if (arguments != null) {
            endValue = arguments.getBoolean("endValue", false);
            if (level < arguments.getInt("level", 0)) {
                level = arguments.getInt("level", 0);
                gol = arguments.getInt("gol");
            } else gol = Math.max(gol, arguments.getInt("gol", 0));
            PreferenceManager.getDefaultSharedPreferences(MenuActivity.this)
                    .edit().putInt("level", level).putInt("gol", gol).putBoolean("end", endValue).apply();
        }
        if (!endValue)
            if (level == 0 && gol == 0)
                records.setText("");
            else records.setText("/" + String.valueOf(level) + "/" + "#" + String.valueOf(gol) +
                    ": " + countries_obj.getCountries(level)[gol]);
        else records.setText("The World is saved");


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

                level_1.setVisibility(View.VISIBLE);
                level_2.setVisibility(View.VISIBLE);
                if (level > 0)
                    level_2.setClickable(true);
                level_3.setVisibility(View.VISIBLE);
                if (level > 1)
                    level_3.setClickable(true);
                registerForContextMenu(level_1);
                Animation anim1 = AnimationUtils.loadAnimation(MenuActivity.this, R.anim.levels_alpha);
                level_1.startAnimation(anim1);
                registerForContextMenu(level_2);
                level_2.startAnimation(anim1);
                registerForContextMenu(level_3);
                level_3.startAnimation(anim1);
            }
        });


    }
}