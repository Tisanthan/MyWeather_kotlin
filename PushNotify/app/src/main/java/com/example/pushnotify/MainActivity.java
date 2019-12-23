package com.example.pushnotify;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    //Notification Channel
    //Notification Builder
    //Notification Maneger

    private static final String CHANNEL_ID = "Simplified CodeId";
    private static final String CHANNEL_Name = "Simplified CodeName";
    private static final String CHANNEL_DESC = "Simplified CodeDescription";

//    private TextView textView;
    private EditText editTextName, editTextPass;
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
        editTextName = findViewById(R.id.name);
        editTextPass = findViewById(R.id.passwold);

        findViewById(R.id.signUp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful()){
                    String token = task.getResult().getToken();
//                    textView.setText("Token : " + token);
                }else {
//                    textView.setText(task.getException().getMessage());

                }
            }
        });

//        findViewById(R.id.buttonNotify).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                displayNotification();
//            }
//        });

    }
    private void createUser(){
        String email = editTextName.getText().toString().trim();
        String password = editTextPass.getText().toString().trim();

        if(email.isEmpty()){
            editTextName.setError("email Required");
            editTextName.requestFocus();
            return;
        }
        if (password.isEmpty()){
            editTextPass.setError("password needed");
            editTextPass.requestFocus();
            return;
        }
        if (password.length()<6){
            editTextPass.setError("password must up to 6 charactor");
            editTextPass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){

                }
            }
        });
    }
    private void startProfileActivity(){
        Intent intent = new Intent(this,Main2Activity.class);
        
    }

    private void displayNotification(){
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this,CHANNEL_ID).setSmallIcon(R.drawable.ic_bell).setContentTitle("its Done!.....").setContentText("Your first notification").setPriority(NotificationCompat.PRIORITY_DEFAULT);

        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(this);
        notificationManagerCompat.notify(1,mBuilder.build());
    }
}
