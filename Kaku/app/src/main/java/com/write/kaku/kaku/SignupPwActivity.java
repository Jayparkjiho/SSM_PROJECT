package com.write.kaku.kaku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupPwActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_pw);

        Button pwConfirmButton = (Button)findViewById(R.id.pwConfirmButton);
        final EditText inputPw = (EditText)findViewById(R.id.inputPw);

        Intent intent = getIntent();
        final String email = intent.getExtras().getString("email");

        pwConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String password = inputPw.getText().toString();

                //todo 비밀번호 유효숫자 및 입력 방식 체크
                boolean passwordCheck = true;
                if(passwordCheck){

                    Intent intent = new Intent(SignupPwActivity.this, SignupNnActivity.class );
                    intent.putExtra("email", email);
                    intent.putExtra("password", password);
                    startActivity(intent);

                } else{

                    //todo 패스워드가 틀렸을 경우
                }
            }
        });

    }
}
