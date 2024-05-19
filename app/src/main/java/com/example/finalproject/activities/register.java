package com.example.finalproject.activities;

import static android.app.ProgressDialog.show;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.finalproject.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {

    EditText name, email, password;
    private FirebaseAuth auth;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        getSupportActionBar().hide();


        auth = FirebaseAuth.getInstance();
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        sharedPreferences = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);

        boolean isFirstTime = sharedPreferences.getBoolean("firstTime",true);

        if(isFirstTime){
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime",false);
            editor.commit();

            Intent intent = new Intent(register.this, OnBoardingActivity.class);
            startActivity(intent);
            finish();
        }
    }

    public void signUp(View view) {

        String user_name = name.getText().toString();
        String user_email = email.getText().toString();
        String user_password = password.getText().toString();
        if(TextUtils.isEmpty(user_name)){
            Toast.makeText(this, "Enter Name!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(user_email)){
            Toast.makeText(this, "Enter Username!!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(user_password)){
            Toast.makeText(this, "Enter Password!", Toast.LENGTH_SHORT).show();
            return;
        }
        if(user_password.length() < 6){
            Toast.makeText(this, "Password Too Short!, enter minimum 6 characters", Toast.LENGTH_SHORT).show();

            return;
        }

        auth.createUserWithEmailAndPassword(user_email, user_password)
                        .addOnCompleteListener(register.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(register.this, "Successfully Registered", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(register.this, SigninActivity.class));
                                } else {
                                    Toast.makeText(register.this, "Registration Failed" + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
    }

    public void signIn(View view) {

        startActivity(new Intent(register.this, SigninActivity.class));
    }
}