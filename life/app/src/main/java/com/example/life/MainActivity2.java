package com.example.life;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;

public class MainActivity2 extends AppCompatActivity {
    private static final String TAG = "SecondActivity";
    Log log;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        log.i(TAG, "onSaveInstacestate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        log.i(TAG,"onStarted 2");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log.i(TAG,"onResumed 2");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log.i(TAG,"onPaused 2");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log.i(TAG,"onStoped 2");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log.i(TAG,"onRestarted 2");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log.i(TAG,"onDestroyed 2");
    }

}