package com.example.kidcourseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;



public class LoginActivity extends AppCompatActivity {

    String TAG = "LoginActivity";

    private EditText idText;
    private EditText passwordText;

    private String stidText;
    private String stpasswordText;

    public FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // login
        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);


        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        // (1)화면전환 "로그인 버튼"
        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stidText = idText.getText().toString();
                stpasswordText = passwordText.getText().toString();

                Log.d("비밀번호",stpasswordText);
                Log.d("아이디",stidText);

                userLogin("hello","helll22");

                Intent registerIntent = new Intent(LoginActivity.this, MainActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        // (2)화면전환 "학부모 로그인"
        Button loginButton_parent = findViewById(R.id.loginButton_parent);
        loginButton_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this, ParentMainActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });

        // (3)화면전환 "회원가입 버튼"
        TextView registerButton = findViewById(R.id.resgisterText);
        registerButton.setOnClickListener(new View.OnClickListener() {
        @Override
        // 클릭할 경우 전환
        public void onClick(View view) {
            Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
            LoginActivity.this.startActivity(registerIntent);
        }
    });

    }

    public void userLogin(String stiTdext, String stpasswordTdext){
        mAuth.signInWithEmailAndPassword(stiTdext, stpasswordTdext)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }
}
