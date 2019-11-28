package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int second =0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        runTimer();
    }

    public void onClickStart(View v){
        running = true;
    }

    public void onClickStop(View v){
        running = false;
    }

    public void onClickReset(View v){
        running = false;
        second = 0;
    }
    private void runTimer(){

        final TextView textView =(TextView)findViewById(R.id.TimeView);
        final Handler handler = new Handler();

        handler.post(new Runnable() {
            @Override
            public void run() {
                int hours = second / 3600;
                int minutes = (second % 3600) / 60;
                int secs = second % 60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs);
                textView.setText(time);

                if (running) {
                    second++;
                }

                handler.postDelayed(this, 1000);

            }
        });

    }

}


