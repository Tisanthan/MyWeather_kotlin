package com.example.lifecycle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Log log;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(TAG,"onCreated");

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(intent);

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        log.i(TAG,"onStarted");
    }

    @Override
    protected void onResume() {
        super.onResume();
        log.i(TAG,"onResumed");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log.i(TAG,"onPaused");
    }

    @Override
    protected void onStop() {
        super.onStop();
        log.i(TAG,"onStoped");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        log.i(TAG,"onRestarted");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log.i(TAG,"onDestroyed");
    }








//    @Override
//    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
//        super.onPostCreate(savedInstanceState);
//        log.i(TAG,"onPostCreated");
//    }
//
//    @Override
//    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
//        super.onPostCreate(savedInstanceState, persistentState);
//        log.i(TAG,"onPostCreated");
//    }
}
