package com.example.finalproject.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class SigninActivity extends AppCompatActivity {

    EditText email, password;
    private FirebaseAuth auth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signin);


        auth = FirebaseAuth.getInstance();
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
    }

    public void signIn(View view) {
        String user_email = email.getText().toString();
        String user_password = password.getText().toString();

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

        auth.signInWithEmailAndPassword(user_email, user_password)
                        .addOnCompleteListener(SigninActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(SigninActivity.this,"Sign in Successfully", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(SigninActivity.this, OnBoardingActivity.class));
                                } else {
                                    Toast.makeText(SigninActivity.this,"Error: " + task.getException(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
    }

    public void signUp(View view) {
        startActivity(new Intent(SigninActivity.this, register.class));
    }
}