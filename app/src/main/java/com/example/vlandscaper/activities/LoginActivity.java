package com.example.vlandscaper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vlandscaper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    /*
    Declaration of elements used in Login Activity
     */
    private EditText edtEmail, edtPassword;
    private Button btnSignIn;
    private FirebaseAuth firebaseAuth;
    private TextView txtForget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Hiding Action Bar
        getSupportActionBar().hide();

        /*
        Getting UI elements reference
         */
        edtEmail = findViewById(R.id.edtEmailSignIn);
        edtPassword = findViewById(R.id.edtPasswordSignIn);
        btnSignIn = findViewById(R.id.btnSignIn);
        txtForget = findViewById(R.id.txtForget);
        firebaseAuth = FirebaseAuth.getInstance();

        /*
        Code for Resetting Password
        Forget Password functionalities
        */
        txtForget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(LoginActivity.this, "Forget Password??", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
            }
        });
    }

    /*
    Implementing SignIn functionalities & validations
     */
    public void signIn(View view) {
        if(TextUtils.isEmpty(edtEmail.getText().toString()) && TextUtils.isEmpty(edtPassword.getText().toString()))
        {
            Toast.makeText(LoginActivity.this, "All Fields are missing", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(edtEmail.getText().toString()))
        {
            Toast.makeText(LoginActivity.this, "Empty or Invalid Email!!!", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(edtPassword.getText().toString()))
        {
            Toast.makeText(LoginActivity.this, "Invalid Password!!!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            /*
            Login Authentication
             */
            firebaseAuth.signInWithEmailAndPassword(edtEmail.getText().toString(), edtPassword.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                if(firebaseAuth.getCurrentUser().isEmailVerified())
                                {
                                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                    Toast.makeText(LoginActivity.this, "Welcome", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, "Please Verify your email", Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }

    }

    /*
    Function sending user to signUp page
     */
    public void signUp(View view){
        startActivity(new Intent(LoginActivity.this, RegistrationActivity.class));
    }
}