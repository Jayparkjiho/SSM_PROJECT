package com.write.kaku.kaku;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabItem;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MyStoryActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystory);

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        //Toolbar 타이틀이름제거
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //만든 타이틀이름 지정
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getToday());

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view_myStory);
        navigationView.setNavigationItemSelectedListener(this);
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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_writing) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_topic) {

        } else if (id == R.id.nav_mypost) {
            Intent intent = new Intent(this, MyStoryActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_read) {

        } else if (id == R.id.nav_share) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_myStory);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Toolbar용 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    public String getToday(){
        String today = "";

        Date d = new Date();

        SimpleDateFormat dToday = new SimpleDateFormat("yyyy年 MM月 dd日 aa", Locale.JAPAN);

        today = dToday.format(d);

        return today;
    }
    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Toolbar용 >>>>>>>>>>>>>>>>>>>>>>>>>>>>>




}
