package com.write.kaku.kaku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class LoadingActivity extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loding_view);

        /* animation call */
        Animation anim = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.alpha);

        TextView mtxt = (TextView)findViewById(R.id.loading_txt);
        ImageView mImg = (ImageView)findViewById(R.id.loading_image);

        mtxt.setAnimation(anim);
        mImg.setAnimation(anim);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent mainIntent = new Intent(LoadingActivity.this, ManualActivity.class);
                LoadingActivity.this.startActivity(mainIntent);
                LoadingActivity.this.finish();
            }
        }, 3000);
    }
}
