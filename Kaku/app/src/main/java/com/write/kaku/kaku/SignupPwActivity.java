package com.write.kaku.kaku;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.write.kaku.kaku.databinding.ActivitySignupPwBinding;

public class SignupPwActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    ActivitySignupPwBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_signup_pw);
        mAuth = FirebaseAuth.getInstance();
        binding.setActivity(this);

    }

    public void pwConfirmButtonCheck(View view) {
        Intent intent = getIntent();
        String email = intent.getExtras().getString("email");
        String password = binding.inputPw.getText().toString();


        createUserWithEmailAndPassword(email,password);



    }


    public void createUserWithEmailAndPassword(String email, String password) {

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(SignupPwActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignupPwActivity.this, SignupNnActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignupPwActivity.this, "Please write the password over 6 word", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

    }
}
