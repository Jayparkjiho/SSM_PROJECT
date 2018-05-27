package com.write.kaku.kaku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button EmailConfirmButton = (Button)findViewById(R.id.EmailConfirmButton);
        final EditText inputEmail = (EditText)findViewById(R.id.inputEmail);

        EmailConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = inputEmail.getText().toString();
                boolean isVaildEamil = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();

                if (isVaildEamil){

                    //todo firebase 에서 이메일 체크

                    //email check boolean (임의설정)
                    boolean emailCheck = true;

                    if(emailCheck){
                        Intent intent = new Intent(LoginActivity.this, PasswordActivity.class );
                        intent.putExtra("email", email);
                        startActivity(intent);
                    } else {
                        //todo 빨리 끝내자 이거
                        // Email이 존재하는 경우
                    }
                } else {
                    //todo 빨리 끝내자 이거
                    // Email이 틀렸을 경우
                }
            }
        });


    }
}
