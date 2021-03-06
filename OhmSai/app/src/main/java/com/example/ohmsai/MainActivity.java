package com.example.ohmsai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class MainActivity extends AppCompatActivity {

    //Notification Channel
    //Notification Builder
    //Notification Maneger

    public static final String CHANNEL_ID = "Simplified CodeId";
    private static final String CHANNEL_Name = "Simplified CodeName";
    private static final String CHANNEL_DESC = "Simplified CodeDescription";

    //    private TextView textView;
    public EditText editTextName;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){

            NotificationChannel channel = new NotificationChannel(CHANNEL_ID,CHANNEL_Name, NotificationManager.IMPORTANCE_DEFAULT);
            channel.setDescription(CHANNEL_DESC);
            NotificationManager managerCompat = getSystemService(NotificationManager.class);
            managerCompat.createNotificationChannel(channel);

        }
        progressBar =findViewById(R.id.progress);
        progressBar.setVisibility(View.INVISIBLE);
        editTextName = findViewById(R.id.Name);
//        editTextPass = findViewById(R.id.Password);

        findViewById(R.id.signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

    }

    private void createUser() {
        final String name = editTextName.getText().toString().trim();
//        final String password = editTextPass.getText().toString().trim();

        if (name.isEmpty()) {
            editTextName.setError("name Required");
            editTextName.requestFocus();
            return;
        }
//        if (password.isEmpty()) {
//            editTextPass.setError("password needed");
//            editTextPass.requestFocus();
//            return;
//        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.signInAnonymously().addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startProfileActivity();
                }else {
                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
                        userLogin(name);
                    }else {
                        progressBar.setVisibility(View.INVISIBLE);
//                    Log.w(TAG, "signInAnonymously:failure", task.getException());
                        Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

//        mAuth.createUserWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    startProfileActivity();
//                }else{
//                    if(task.getException() instanceof FirebaseAuthUserCollisionException){
//                        userLogin(name,password);
//                    }else {
//                        progressBar.setVisibility(View.INVISIBLE);
//                        Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
//                    }
//                }
//            }
//        });

    }
    private void userLogin(String name){
        mAuth.signInAnonymously().addOnCompleteListener(    new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startProfileActivity();
                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(MainActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
//    private void userLogin(String name,String password){
//
//        mAuth.signInWithEmailAndPassword(name,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                if(task.isSuccessful()){
//                    startProfileActivity();
//                }else {
//                    progressBar.setVisibility(View.INVISIBLE);
//                    Toast.makeText(MainActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
//                }
//            }
//        });
//
//    }
    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() != null){
            startProfileActivity();
        }
    }

    private void startProfileActivity(){
        Intent intent = new Intent(this,LoginPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }


}
