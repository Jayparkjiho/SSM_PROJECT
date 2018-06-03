package com.write.kaku.kaku;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class PasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_view);

        Button passwordConfirmButton = (Button)findViewById(R.id.passwordConfirmButton);
        final EditText inputPassword = (EditText)findViewById(R.id.inputPassword);

        passwordConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String email = getIntent().getExtras().getString("email");
                String password = inputPassword.getText().toString();

                logInWithEmailAndPassword(email, password);
            }
        });
    }

    public void logInWithEmailAndPassword(String email, String password){

        LoadingActivity.mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(PasswordActivity.this, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    Intent intent = new Intent(PasswordActivity.this, MainActivity.class );
                    startActivity(intent);
                }else{
                    Toast.makeText(PasswordActivity.this, "login fail", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
