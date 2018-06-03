package com.write.kaku.kaku;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignupPwActivity extends AppCompatActivity {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_pw);

        mAuth = FirebaseAuth.getInstance();

        Button pwConfirmButton = (Button) findViewById(R.id.pwConfirmButton);
        final EditText inputPw = (EditText) findViewById(R.id.inputPw);

        Intent intent = getIntent();
        final String email = intent.getExtras().getString("email");

        pwConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = inputPw.getText().toString();
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
        });
    }
}
