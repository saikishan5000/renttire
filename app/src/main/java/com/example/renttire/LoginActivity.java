package com.example.renttire;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText username,password;
    private TextView signupText;
    private Button loginbutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        auth = FirebaseAuth.getInstance();
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        loginbutton = findViewById(R.id.loginbutton);
        signupText = findViewById(R.id.signuptext);

        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = username.getText().toString();
                String pass = password.getText().toString();

                if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    if(!pass.isEmpty()){
                        auth.signInWithEmailAndPassword(email,pass)
                                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                        finish();
                                    }
                                });
//                                .addOnFailureListener(new OnFailureListener() {
//                                    @Override
//                                    public void onFailure(@NonNull Exception e) {
//                                        Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
                    }else{
                        password.setError("Password cannot be empty!");
                    }
                } else if(email.isEmpty()){
                    username.setError("Email cannot be empty!");
                } else{
                    username.setError("Please enter valid email");
                }
            }
        });

        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
                finish();
            }
        });
    }
}