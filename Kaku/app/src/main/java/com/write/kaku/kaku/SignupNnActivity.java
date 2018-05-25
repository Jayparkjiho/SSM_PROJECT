package com.write.kaku.kaku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupNnActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_nn);

        Button NnConfirmButton = (Button)findViewById(R.id.NnConfirmButton);
        final EditText inputNn = (EditText)findViewById(R.id.inputNn);

        Intent intent = getIntent();
        String email = intent.getExtras().getString("email");
        String password = intent.getExtras().getString("password");

        NnConfirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = inputNn.getText().toString();

                //todo firebase에서 닉네임 중복 체크
                boolean nicknameCheck = true;

                if(nicknameCheck){

                    //todo firebase 에서 유저 생성 후 메인 화면으로 이동



                }else{
                    //todo 닉네임이 중복 되는 경우 에러 메세지 표시
                }
            }
        });

    }
}
