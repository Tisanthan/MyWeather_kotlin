package com.example.vadamar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.vadamar.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN = 1800;

    ConstraintLayout rootLayout;
    Animation top_animation, bottom_animation, fade;
    ImageView topImage, bottomImage;
    TextView dialog;


    //firebase Outh
    FirebaseAuth auth;
    FirebaseDatabase db;
    DatabaseReference users;

    public static final String NODE_USERS = "users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            startActivity(new Intent(MainActivity.this, Dashboard.class));
        }
        setContentView(R.layout.activity_main);

        //init Firebase
        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        users = db.getReference("User");


        //Animatiom
        top_animation = AnimationUtils.loadAnimation(this, R.anim.top_animation);
        bottom_animation = AnimationUtils.loadAnimation(this, R.anim.bottom_animation);
        fade = AnimationUtils.loadAnimation(this, R.anim.fate);

        topImage = findViewById(R.id.top);
        bottomImage = findViewById(R.id.bottom);
        dialog = findViewById(R.id.dialog);

        topImage.setAnimation(bottom_animation);
        bottomImage.setAnimation(top_animation);
        dialog.setAnimation(fade);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                final AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("SignIN");
                dialog.setMessage("Please use Your Name");
                LayoutInflater layoutInflater = LayoutInflater.from(MainActivity.this);
                View layout_signin = layoutInflater.inflate(R.layout.layout_signin, null);
                dialog.setView(layout_signin);

                final TextInputEditText editName = layout_signin.findViewById(R.id.edtName);

                dialog.setPositiveButton("Sign In", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();

                        if (TextUtils.isEmpty(editName.getText().toString())) {
                            Snackbar.make(rootLayout, "Please enter your Name", Snackbar.LENGTH_SHORT).show();
                            return;
                        }

                        auth.signInAnonymously()
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        FirebaseInstanceId.getInstance().getInstanceId().addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<InstanceIdResult> task) {
                                                if (task.isSuccessful()) {
                                                    String token = task.getResult().getToken();
                                                    String name = editName.getText().toString().trim();

                                                    saveToken(token, name);
                                                }
                                                startActivity(new Intent(MainActivity.this, Dashboard.class));
                                            }
                                        });
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Snackbar.make(rootLayout, "Sign in unsuccessful" + e.getMessage(), Snackbar.LENGTH_LONG).show();
                                    }
                                })
                        ;
                    }
                });

                dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        finish();

                    }
                });
                dialog.show();
            }
        }, SPLASH_SCREEN);

    }

    private void saveToken(String token, final String name) {

//        TextInputEditText editText = findViewById(R.id.edtName);
//        String name = editText.getText().toString();

        auth = FirebaseAuth.getInstance();

        User user = new User(name, token);

        DatabaseReference dbUsers = FirebaseDatabase.getInstance().getReference(NODE_USERS);

        dbUsers.child(auth.getCurrentUser().getUid()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, " Successfully enrolled "+name, Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Token Un Saved", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

}