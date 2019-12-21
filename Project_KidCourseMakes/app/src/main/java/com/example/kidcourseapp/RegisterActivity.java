package com.example.kidcourseapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner spinner;

    String TAG = "LoginActivity";
    EditText idText;
    EditText passwordText;
   private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        spinner = (Spinner) findViewById(R.id.majorSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.major, android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        idText = findViewById(R.id.idText);
        passwordText = findViewById(R.id.passwordText);

//    mAuth = FirebaseAuth.getInstance();
//        mAuthListener = new FirebaseAuth.AuthStateListener() {
//            @Override
//            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
//                FirebaseUser user = firebaseAuth.getCurrentUser();
//                if( user != null ){
//                    Log.d(TAG,"mAuthListener:signed_in:" + user.getUid());
//                }
//                else {
//                    Log.d(TAG,"onmAuthStateChanged:signed_out");
//                }
//                // ...
//            }
//        };

        // (2)화면전환 "회원가입 누를 시 로그인 창으로"
        Button registerButton = findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String stidText = idText.getText().toString();
                String stpasswordText = passwordText.getText().toString();

                userRegiseter( stidText, stpasswordText );

                Intent registerIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(registerIntent);
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        mAuth.addAuthStateListener(mAuthListener);
    }

    public void onStop() {
        super.onStop();
        if( mAuthListener != null ) {
           // mAuth.addAuthStateListener(mAuthListener);
        }
    }

    public void userRegiseter(String idText, String passwordText ) {
        mAuth.createUserWithEmailAndPassword(idText, passwordText)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "회원가입이 완료되었습니다.");

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "회원가입에 실패하셨습니다.", task.getException());
                            Toast.makeText(RegisterActivity.this, "인증에 실패했습니다.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });

    }

}
