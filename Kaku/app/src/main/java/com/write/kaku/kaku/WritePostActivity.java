package com.write.kaku.kaku;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.write.kaku.kaku.SSM_Model.KeyWord;
import com.write.kaku.kaku.SSM_Model.Post;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class WritePostActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private EditText textbody;
    private TextView headkeyword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        TextView backText = (TextView)findViewById(R.id.back);
        backText.setOnClickListener(this);
        TextView confirmText = (TextView)findViewById(R.id.confirmbtn);
        confirmText.setOnClickListener(this);
        textbody = (EditText)findViewById(R.id.textbody);
        headkeyword = (TextView)findViewById(R.id.keyword);

        Intent intent = getIntent();
        headkeyword.setText(intent.getExtras().getString("Keyword"));

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back:
                onBackPressed();
                break;
            case R.id.confirmbtn:
                String content = textbody.getText().toString();
                String keyword = headkeyword.getText().toString();
                String userUId = mAuth.getCurrentUser().getUid();

                writeNewPost(userUId, keyword, content);
                onBackPressed();
                break;
        }
    }

    private void writeNewPost(String userUId, String keyWord, String contents) {
        String key = mDatabase.child("posts").push().getKey();
        String now = getToday();
        Post post = new Post(key, contents, now, now, now, keyWord, userUId, 0);

        Log.i("postData", post.toMap().toString());

        Map<String, Object> postValues = post.toMap();

        Map<String, Object> chlidUpdates = new HashMap<>();

        if(mAuth.getCurrentUser().getEmail().equals("admin@admin.com")){
            KeyWord k1 = new KeyWord(contents, getToday());
            chlidUpdates.put("/keywords/" + getToday(), k1);
        }else{
            chlidUpdates.put("/posts/" + key, postValues);
            chlidUpdates.put("/user-posts/" + userUId + "/" + key, postValues);
        }

        mDatabase.updateChildren(chlidUpdates);
    }

    public String getToday(){
        String today = "";
        Date d = new Date();
        SimpleDateFormat dToday = new SimpleDateFormat("yyyy年 MM月 dd日 aa", Locale.JAPAN);
        today = dToday.format(d);
        return today;
    }

}
