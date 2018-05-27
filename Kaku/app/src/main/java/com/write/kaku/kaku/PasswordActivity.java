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

                //todo password 체크 글자수 및 형식

                // 임의 패스워드 체크 상태
                boolean passwordCheck = true;

                if (passwordCheck){

                    logInWithEmailAndPassword(email, password);

                } else {
                    //todo 빨리 끝내자 이거
                    // passwordCheck 틀렸을 경우
                }
            }
        });
    }

    public Task<AuthResult> logInWithEmailAndPassword(String email, String password){

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

        return null;
    }
}
