package com.write.kaku.kaku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.write.kaku.kaku.SSM_View_Util.SlideView;

public class ManualActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ManualActivity","onCreate");
        SlideView sv = new SlideView(this);

        View menual_view_1 = View.inflate(this, R.layout.menual_view_1, null);
        View menual_view_2 = View.inflate(this, R.layout.menual_view_2, null);
        View menual_view_3 = View.inflate(this, R.layout.menual_view_3, null);
        View menual_view_4 = View.inflate(this, R.layout.menual_view_4, null);
        View menual_view_5 = View.inflate(this, R.layout.menual_view_5, null);
        View menual_view_6 = View.inflate(this, R.layout.menual_view_6, null);

        sv.addView(menual_view_1);
        sv.addView(menual_view_2);
        sv.addView(menual_view_3);
        sv.addView(menual_view_4);
        sv.addView(menual_view_5);
        sv.addView(menual_view_6);

        setContentView(sv);


        Button signup_button = (Button)menual_view_6.findViewById(R.id.signupButton);
        Button login_button = (Button)menual_view_6.findViewById(R.id.loginButton);

        signup_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManualActivity.this, SignupIdActivity.class);
                startActivity(intent);
            }
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ManualActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
