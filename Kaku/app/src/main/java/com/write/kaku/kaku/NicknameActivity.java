package com.write.kaku.kaku;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Hashtable;

public class NicknameActivity extends AppCompatActivity {
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);

        Button btnnick = (Button)findViewById(R.id.btnnickname);
        final EditText edtnick = (EditText)findViewById(R.id.edtnickname);

        btnnick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nickname = edtnick.getText().toString();

                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                if(user != null){
                    email = user.getEmail().toString();
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference myRef = database.getReference("nickname").child(user.getUid());

                    Hashtable<String, String> writer = new Hashtable<String, String>();
                    writer.put("email", email);
                    writer.put("nickname", nickname);
                    myRef.setValue(writer);

                    updateUI(user);
                }
            }
        });
    }
    public void updateUI(FirebaseUser user){
        Intent in = new Intent(NicknameActivity.this, MainlistActivity.class);
        startActivity(in);
    }
}
