package com.example.vlandscaper.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vlandscaper.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPasswordActivity extends AppCompatActivity {

    private EditText edtEmailForget;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        
        edtEmailForget = findViewById(R.id.edtEmailForget);
        firebaseAuth = FirebaseAuth.getInstance();
    }

    /*
    Resetting Password 
     */
    public void forgetPass(View view) {
        if(TextUtils.isEmpty(edtEmailForget.getText().toString()) || !Patterns.EMAIL_ADDRESS.matcher(edtEmailForget.getText().toString()).matches())
        {
            Toast.makeText(ForgetPasswordActivity.this, "Please Enter Valid Email", Toast.LENGTH_SHORT).show();
        }
        else
        {
            firebaseAuth.sendPasswordResetEmail(edtEmailForget.getText().toString())
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(ForgetPasswordActivity.this, "Check Your Email to Reset Password", Toast.LENGTH_SHORT).show();
                            }
                            else
                            {
                                Toast.makeText(ForgetPasswordActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
            finish();
        }
    }
}