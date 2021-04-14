package com.example.owner.calendar;

import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar ;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.LinearLayout;

import java.text.MessageFormat;
public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        drawer = findViewById(R.id.draw_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new today_fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_today_calendar);
        }
        int id = getIntent().getIntExtra("id",0);
        if(id ==1){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new updata_fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_updataview);                     //設定側邊攔的選取
        }else if(id == 2){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new memorandum_fragment()).commit();
            navigationView.setCheckedItem(R.id.nav_memorandum);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.nav_today_calendar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new today_fragment()).commit();
                break;
            case R.id.nav_updataview:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new updata_fragment()).commit();
                break;
            case R.id.nav_memorandum:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new memorandum_fragment()).commit();
                break;
//            case R.id.nav_permanent:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new permanent_fragment()).commit();
//                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed(){
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }

    }
}
