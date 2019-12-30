package com.example.saisurapi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainPage extends AppCompatActivity {

    public static final String NODE_USERS ="users";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

//        NotificationHelper.displayNotification(this,"tisa","toahtoa aaohtoan ajotanlmaot ha");



//
//        AlertDialog alertDialog = new AlertDialog.Builder(MainPage.this).create();
//        alertDialog.setTitle("Ohm Sai Ram");
//        alertDialog.setMessage("wel come "+ name);
//        alertDialog.show();




        mAuth = FirebaseAuth.getInstance();

        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
            @Override
            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                if (task.isSuccessful()){
                    String token = task.getResult().getToken();
                    saveToken(token);
                    Toast.makeText(MainPage.this, "YOU ARE IN", Toast.LENGTH_LONG).show();
                }else {

                }
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() == null){
            Intent intent = new Intent(this,MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    private void saveToken(String token) {

        Bundle extras = getIntent().getExtras();
        String name = extras.getString("name");

        User user = new User(name,token);

        DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference(NODE_USERS);

        dbUsers.child(mAuth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(MainPage.this, " Successfully enrolled ", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainPage.this, "Token Un Saved", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}