package com.example.oldtvproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintProperties;

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
import android.widget.TextView;
import android.widget.VideoView;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Boolean endValue;
    int gol = 10;
    int level = 0;
    double buff;
    int level_Count;
    int static_gol;
    String[] countries;
    Thread time_thread;
    Thread video_thread;
    Thread end_thread;
    Thread next_thread;
    CountriesClass country_obj;
    LinearLayout golsList;
    TextView colourOp;
    TextView timer_score;
    TextView golOut;
    VideoView videoground;
    TextView channel;
    Timer timer;
    Timer timer2;
    float alpha = 0.f;
    int _timer;
    TextView country;
    ImageButton agree;
    ImageView reverse;
    ImageView end_ground;
    Boolean truth = false;
    Boolean timer_plus = false;
    ConstraintProperties params;
    ImageButton disagree;
    ConstraintLayout mainxml;
    ImageView videoalp;
    int timer_plus_vis = 0;
    double[] coefs;
    int memeconds;
    Map<String, Integer> Colours = new HashMap<String, Integer>();
    Map<Integer, Object[]> ColoursToTake = new HashMap<Integer, Object[]>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        golsList = findViewById(R.id.golsList);
        end_ground = findViewById(R.id.end_ground);
        timer_score = findViewById(R.id.timer_score);
        reverse = findViewById(R.id.reverse);
        videoalp = findViewById(R.id.videoalp);
        videoground = findViewById(R.id.videoground);
        country = findViewById(R.id.country);
        golOut = findViewById(R.id.golOut);
        mainxml = findViewById(R.id.mainxml);
        channel = findViewById(R.id.channel);
        colourOp = findViewById(R.id.colourOp);
        agree = findViewById(R.id.agree);
        disagree = findViewById(R.id.disagree);
        params = new ConstraintProperties(mainxml);
        Bundle arguments = getIntent().getExtras();
        level = arguments.getInt("level", 0);

        // Video
        videoground.setVideoPath("android.resource://" + getPackageName() + "/" + R.raw.videoplayback);

        end_thread = new Thread(()->{
            timer.cancel();
            time_thread.interrupt();
            video_thread.interrupt();
            end_ground.setImageResource(R.drawable.black_square);
            try {
                Thread.sleep(1500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent intent = new Intent(MainActivity.this, MenuActivity.class);
            intent.putExtra("endValue", endValue);
            intent.putExtra("level", level);
            intent.putExtra("gol", static_gol - gol);
            startActivity(intent);
        });

        next_thread = new Thread(()->{
            timer.cancel();
            time_thread.interrupt();
            video_thread.interrupt();
            int[] grounds = {R.drawable.next_back_1, R.drawable.next_back_2, R.drawable.next_back_3};

            for (int j = 0; j < 2; j++)
                for (int i = 0; i < 3; i++) {
                    end_ground.setImageResource(grounds[i]);
                    try {
                        Thread.sleep(300);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            end_ground.setVisibility(View.INVISIBLE);
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            intent.putExtra("level", level);
            startActivity(intent);
        });

        time_thread = new Thread(()->{
            memeconds = _timer * 100;
            final String[] time = {""};
            timer = new Timer();
            timer.schedule((TimerTask) (new TimerTask() {
                public void run() {
                    runOnUiThread((Runnable) (new Runnable() {
                        public final void run() {
                            if (timer_plus_vis > 0)
                                timer_plus_vis -= 2;
                            memeconds --;
                            int millisecond = memeconds % 100;
                            int second = (int) memeconds / 100;
                            time[0] += (second < 10) ? "0" + String.valueOf(second) : String.valueOf(second);
                            time[0] += ":";
                            time[0] += (millisecond < 10) ? "0" + String.valueOf(millisecond) : String.valueOf(millisecond);
                            if (timer_plus) {
                                timer_plus = false;
                                memeconds += (int) buff*100;
                                timer_plus_vis = 254;
                                timer_score.setTextColor(Color.rgb(0, timer_plus_vis, 0));
                            }
                            timer_score.setTextColor(Color.rgb(0, timer_plus_vis, 0));
                            videoalp.setAlpha(1 - alpha);
                            System.out.println(time[0]);
                            timer_score.setText(time[0]);
                            if (memeconds == 0) {
                                agree.setEnabled(false);
                                disagree.setEnabled(false);
                                end_ground.setVisibility(View.VISIBLE);
                                endValue = false;
                                end_thread.start();
                            }
                            time[0] = "";
                        }
                    }));
                }
            }), 0L, 10L);
        });

        video_thread = new Thread(()-> {
            videoground.start();
            timer2 = new Timer();
            timer2.schedule((TimerTask) (new TimerTask() {
                public void run() {
                    runOnUiThread((Runnable) (new Runnable() {
                        public final void run() {
                            if (!videoground.isPlaying())
                                videoground.start();
                            // ot 0 do 6
                            if (memeconds <= 600)
                                alpha = (600 - (memeconds)) / 600.f;
                            else alpha = 0.f;
                        }
                    }));
                }
            }), 0L, 100L);
        });

        channel.setText("CHANNEL #" + String.valueOf(level) + ":");

        // ???????????????? ???????????? ??????????????
        country_obj = new CountriesClass();
        level_Count = country_obj.getLevel_Count();
        buff = country_obj.getBuff(level);
        _timer = country_obj.getTime(level);
        static_gol = gol = country_obj.getSize(level);
        countries = country_obj.getCountries(level);
        coefs = country_obj.getCoefs(level);

        if (gol < 10)
            drawSquares();

        Colours.put("??????????????", -16711681);
        Colours.put("??????????", -16776961);
        Colours.put("????????????", -256);
        Colours.put("??????????????", -16711936);
        Colours.put("??????????????", -65536);
        Colours.put("??????????????", -65281);

        int io = 0;
        for (Map.Entry<String, Integer> entry : Colours.entrySet()) {
            ColoursToTake.put(io, new Object[]{entry.getKey(), entry.getValue()});
            io++;
        }

        CalculateAndDraw();
        time_thread.start();
        video_thread.start();
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickInside(true);
            }
        });
        disagree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickInside(false);
            }
        });
    }
    private void squaresRemove() {
        if (gol != 0) {
            golsList.removeViewAt(0);
            golOut.setText(String.valueOf(gol));
        }
    }

    private void drawSquares() {
        golOut.setText(String.valueOf(gol));
        ViewGroup.LayoutParams settings = new ViewGroup.LayoutParams(50, 50);
        for(int i = 0; i < gol; i++) {
            ImageView square = new ImageView(this);
            square.setPadding(0,0,10,0);
            square.setImageResource(R.drawable.black_square);
            golsList.addView(square, settings);
        }
    }

    private void CalculateAndDraw() {
        truth = true;
        // ?????? ???? ??????????
        reverse.setVisibility(View.INVISIBLE);
        params.rotationX(0);
        // ?????????????? ????????????????????
        System.out.println(countries[0]);
        System.out.println(static_gol);
        System.out.println(gol);
        country.setText("#" + String.valueOf(static_gol - gol) + " " + countries[static_gol - gol]);
        // ???????????????? ?????????? ???????? ??????????
        int index_color = ((int) (Math.random() * ColoursToTake.size())) % ColoursToTake.size();
        // ???????????????????????????? ??????????????????
        String strToSend = (String) ColoursToTake.get(index_color)[0];
        int intToSend = (int) ColoursToTake.get(index_color)[1];
        // ???????????????? ????????????, ?? ?????????????? ?????????? ????????????????
        double rand = Math.random();
        // ?????????????????????? ??????????
        if ((rand * 1000) % 1 < coefs[3]) {
            params.rotationX(180);
        }
        // ?????????? ???? ?????????????? ??????????
        if ((rand * 1) % 1 < coefs[0]) {
            int index_color_1 = ((int) (Math.random() * ColoursToTake.size())) % ColoursToTake.size();
            index_color_1 = (index_color_1 == index_color)? ((index_color_1 == 0)? 1: index_color_1 - 1): index_color_1;
            intToSend = (int) ColoursToTake.get(index_color_1)[1];
            truth = false;
        }
        // ???????????????????????? ???? ????????????
        if ((rand * 10) % 1 < coefs[1]) {
            strToSend = "\u202E" + strToSend;
        }
        // ?????????????????? ???? ????????????
        if ((rand * 100) % 1 < coefs[2]) {
            truth = !truth;
            reverse.setVisibility(View.VISIBLE);
        }
        colourOp.setText(strToSend);
        colourOp.setTextColor(intToSend);

        registerForContextMenu(colourOp);
        Animation anim1 = AnimationUtils.loadAnimation(this, R.anim.zoom);
        colourOp.startAnimation(anim1);
        Animation anim2 = AnimationUtils.loadAnimation(this, R.anim.movealittle);
        mainxml.startAnimation(anim2);
    }
    private void clickInside(Boolean choose) {
        if (truth == choose) {
            gol--;
            timer_plus = true;
            if (gol < 10)
                squaresRemove();
            else if (gol == 10)
                drawSquares();
            if (gol == 0)
                if (level + 1 < level_Count) {
                    // go next level
                    level++;
                    agree.setEnabled(false);
                    disagree.setEnabled(false);
                    end_ground.setVisibility(View.VISIBLE);
                    end_ground.setImageResource(R.drawable.next_back_1);
                    next_thread.start();
                } else {
                    // game won all
                    agree.setEnabled(false);
                    disagree.setEnabled(false);
                    end_ground.setVisibility(View.VISIBLE);
                    end_ground.setImageResource(R.drawable.black_square);
                    endValue = true;
                    end_thread.start();
                }
            else CalculateAndDraw();
        } else {
            // game over
            agree.setEnabled(false);
            disagree.setEnabled(false);
            end_ground.setVisibility(View.VISIBLE);
            end_ground.setImageResource(R.drawable.black_square);
            end_thread.start();
        }
    }

}