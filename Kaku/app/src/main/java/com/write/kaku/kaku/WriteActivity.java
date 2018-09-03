package com.write.kaku.kaku;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class WriteActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write);
        TextView backText = (TextView)findViewById(R.id.back);
        backText.setOnClickListener(this);
        TextView confirmText = (TextView)findViewById(R.id.confirmbtn);
        confirmText.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.confirmbtn:
                onBackPressed();
                break;
        }
    }
}
