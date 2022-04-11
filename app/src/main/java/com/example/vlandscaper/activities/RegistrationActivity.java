package com.example.vlandscaper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
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
import com.google.firebase.auth.FirebaseUser;

import java.util.*;

public class RegistrationActivity extends AppCompatActivity {

    //Declaration of objects
    private EditText edtName, edtEmail, edtPass, edtRePass;
    private Button signUp;
    private TextView txtSignIn;
    private FirebaseAuth firebaseAuth;

    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        //Action bar hiding
        getSupportActionBar().hide();

        //Calling objects
        edtName = findViewById(R.id.edtNameRegister);
        edtEmail = findViewById(R.id.edtEmailRegister);
        edtPass = findViewById(R.id.edtPasswordRegister);
        edtRePass = findViewById(R.id.edtRePasswordRegister);
        signUp = findViewById(R.id.btnSignUp);
        txtSignIn = findViewById(R.id.txtSignInRegister);
        firebaseAuth = FirebaseAuth.getInstance();

        /*
        If user is using application first time
        The application will show a slider
        We are using sharedPreferences for this purpose
         */
        sharedPreferences = getSharedPreferences("onBoardingScreen", MODE_PRIVATE);

        boolean isFirstTime = sharedPreferences.getBoolean("firstTime", true);
        if(isFirstTime){

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("firstTime", false);
            editor.commit();

            Intent intent = new Intent(RegistrationActivity.this, OnBoardingActivity.class);
            startActivity(intent);
            finish();
        }


    }

    /*
    SignUp after
    Name, Email, Password, ReEnter Password validations
     */

    public void signUp(View view) {
        if(TextUtils.isEmpty(edtName.getText().toString()) && TextUtils.isEmpty(edtEmail.getText().toString()) && TextUtils.isEmpty(edtPass.getText().toString()) && TextUtils.isEmpty(edtRePass.getText().toString())){
            Toast.makeText(RegistrationActivity.this, "Please Fill All Fields", Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(edtEmail.getText().toString()) || !Patterns.EMAIL_ADDRESS.matcher(edtEmail.getText().toString()).matches())
        {
            Toast.makeText(RegistrationActivity.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
            edtEmail.requestFocus();
            edtEmail.setError("Email");
        }
        else if(TextUtils.isEmpty(edtPass.getText().toString()))
        {
            Toast.makeText(RegistrationActivity.this, "Please Set Password", Toast.LENGTH_SHORT).show();
            edtPass.requestFocus();
            edtPass.setError("Password");
        }
        else if(edtPass.length() < 6)
        {
            Toast.makeText(RegistrationActivity.this, "Please Set Strong Password", Toast.LENGTH_SHORT).show();
            edtRePass.requestFocus();
            edtPass.setError("Password");
        }
        else if(!(edtPass.getText().toString().equals(edtRePass.getText().toString())))
        {
            Toast.makeText(RegistrationActivity.this, "Password Does Not Match !!!", Toast.LENGTH_SHORT).show();
        }
        else{
            /*
            Authenticating user with firebase
             */
            firebaseAuth.createUserWithEmailAndPassword(edtEmail.getText().toString(), edtPass.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                firebaseAuth.getCurrentUser().sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if(task.isSuccessful())
                                                {
                                                    Toast.makeText(RegistrationActivity.this, "Please verify email... Email has been send successfully", Toast.LENGTH_SHORT).show();
                                                    startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
                                                    finish();
                                                }
                                                else
                                                {
                                                    Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        });
                            }
                            else
                            {
                                Toast.makeText(RegistrationActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    /*
    SignIn function calls the login activity
     */
    public void signIn(View view){
        startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser != null) {
            Toast.makeText(this, "Welcome to V LandScaper", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            finish();
        }
    }
}