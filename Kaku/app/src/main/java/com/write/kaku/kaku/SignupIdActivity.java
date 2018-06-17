package com.write.kaku.kaku;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.ProviderQueryResult;
import com.write.kaku.kaku.databinding.ActivitySignupIdBinding;

public class SignupIdActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    ActivitySignupIdBinding binding;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_signup_id);
        mAuth = FirebaseAuth.getInstance();
        binding.setActivity(this);
    }

    public void EmailConfirmButtonCheck(View view) {
        email = binding.inputEmail.getText().toString();
        Log.d("email",email);
        boolean isVaildEamil = !TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches();

        if (isVaildEamil){

            //firebase에서 email 확인
            mAuth.fetchProvidersForEmail(email).addOnCompleteListener(new OnCompleteListener<ProviderQueryResult>() {
                @Override
                public void onComplete(@NonNull Task<ProviderQueryResult> task) {
                    if (task.isSuccessful()){
                        Intent intent = new Intent(SignupIdActivity.this, SignupPwActivity.class );
                        intent.putExtra("email", email);
                        startActivity(intent);
                    }else{
                        Toast.makeText(SignupIdActivity.this, "regiested email", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            Toast.makeText(SignupIdActivity.this, "Please check your email again", Toast.LENGTH_SHORT).show();
        }
    }
}
