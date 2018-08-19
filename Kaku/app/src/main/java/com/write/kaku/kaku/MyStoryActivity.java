package com.write.kaku.kaku;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MyStoryActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystory);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        //왼쪽 drawer메뉴바 사용
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menu_32);
        //Toolbar 타이틀이름제거
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        Toolbar secondToolbar = (Toolbar) findViewById(R.id.second_toolbar);
//        secondToolbar.setTitle(getResources().getString(R.string.app_name));
        TabLayout tabLayout = findViewById(R.id.tablayout);
        TabItem tabMyStory = findViewById(R.id.tab_mystory);
        TabItem tabSetStory = findViewById(R.id.tab_myset);
        ViewPager viewPager = findViewById(R.id.viewPager);

    }
    //<<<<<<<<<<<<<<<z<<<<<<<<<<<<<<<<<<<<<<<Toolbar용 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home :
                Toast.makeText(getApplicationContext(), "홈 버튼 클릭됨", Toast.LENGTH_LONG).show();
                break;

            case R.id.action_search :
                Toast.makeText(getApplicationContext(), "검색 버튼 클릭됨", Toast.LENGTH_LONG).show();
                break;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);

        }

        return super.onOptionsItemSelected(item);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Toolbar용 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>




}
