package com.write.kaku.kaku;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.write.kaku.kaku.SSM_View_Fragements.MyPostFragment;
import com.write.kaku.kaku.SSM_View_Fragements.ReadFragment;
import com.write.kaku.kaku.SSM_View_Fragements.ShareFragment;
import com.write.kaku.kaku.SSM_View_Fragements.TopicFragment;
import com.write.kaku.kaku.SSM_View_Fragements.WriteFragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainViewActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_view);
        Log.d("MainViewActivity","onCreate");

        //fragment test

        Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(mainToolbar);
        //Toolbar 타이틀이름제거
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        //만든 타이틀이름 지정
        TextView toolbarTitle = findViewById(R.id.toolbar_title);
        toolbarTitle.setText(getToday());

        //Drawer refer
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, mainToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        //navi refer
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //select writing by default
        //navigationView.setCheckedItem(R.id.nav_writing);
        Fragment fragment = new WriteFragment();
        displaySelectedFragment(fragment);

        //original source

        /*Toolbar mainToolbar = (Toolbar) findViewById(R.id.main_toolbar);
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

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);*/

        /*mAuth = FirebaseAuth.getInstance();

        Button logout_button = (Button)findViewById(R.id.logout_button);

        logout_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent intent = new Intent(MainViewActivity.this, ManualActivity.class);
                startActivity(intent);
            }
        });*/

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }*/

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (item.getItemId()) {

            /*case android.R.id.home :
                Toast.makeText(getApplicationContext(), "홈 버튼 클릭됨", Toast.LENGTH_LONG).show();
                break;*/

            case R.id.action_search :
                Toast.makeText(getApplicationContext(), "검색 버튼 클릭됨", Toast.LENGTH_LONG).show();
                break;


            /*default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                Toast.makeText(getApplicationContext(), "나머지 버튼 클릭됨", Toast.LENGTH_LONG).show();
                return super.onOptionsItemSelected(item);*/

        }
        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        //item.setChecked(false);
        int id = item.getItemId();
        Fragment fragment = null;

        if (id == R.id.nav_writing) {
            fragment = new WriteFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_topic) {
            fragment = new TopicFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_mypost) {
            fragment = new MyPostFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_read) {
            fragment = new ReadFragment();
            displaySelectedFragment(fragment);
        } else if (id == R.id.nav_share) {
            fragment = new ShareFragment();
            displaySelectedFragment(fragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return false;
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


    private void displaySelectedFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frame_Main, fragment);
        fragmentTransaction.commit();
    }
}
