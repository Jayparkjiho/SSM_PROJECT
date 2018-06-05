package com.write.kaku.kaku;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;

public class LoginActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    static String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        Button EmailConfirmButton = (Button)findViewById(R.id.EmailConfirmButton);
        final EditText input_email = (EditText)findViewById(R.id.inputEmail);

        EmailConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = input_email.getText().toString();
                boolean isVaildEamil = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();

                if (isVaildEamil) {

                    // firebase 에서 이메일 체크
                    mAuth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                        @Override
                        public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(LoginActivity.this, PasswordActivity.class);
                            intent.putExtra("email", LoginActivity.email);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "email is not exist", Toast.LENGTH_SHORT).show();
                        }
                        }
                    });
                }
            }
        });
    }
}