package com.example.test2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DrawerLayout drawer;
    DatabaseHelper myDb;
    //private EditText editType,editDate,editTime,editDes;
    //private Button btnAddData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.setChange(false);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toogle = new ActionBarDrawerToggle(this,drawer,toolbar,
                R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawer.addDrawerListener(toogle);
        toogle.syncState();
        if(savedInstanceState == null) {   // to contour refresh due to device rotation
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Calendar()).commit();
            navigationView.setCheckedItem(R.id.nav_calendar);
        }

        myDb = new DatabaseHelper(this);
        App.setContext(this);
        //editType = (EditText)findViewById(R.id.editTextType);
        //editTime = (EditText)findViewById(R.id.editTextTime);
       // editDate = (EditText)findViewById(R.id.editTextDate);
       // editDes = (EditText)findViewById(R.id.editTextDes);
        //btnAddData = (Button)findViewById(R.id.submit);
        //AddData();
    }

    //public void AddData(){
     //   btnAddData.setOnClickListener(
    //            new View.OnClickListener(){

    //                @Override
    //                public void onClick(View v) {
     //                   boolean isInserted =  myDb.insertData(editType.getText().toString(),
     //                           editDate.getText().toString(),
     //                           editTime.getText().toString(),
     //                           editDes.getText().toString());
     //                   if(isInserted)
     //                       Toast.makeText(MainActivity.this,"DATA Inserted",Toast.LENGTH_LONG).show();
     //                   else
     //                       Toast.makeText(MainActivity.this,"DATA Not Inserted",Toast.LENGTH_LONG).show();
     //               }
     //           }
     //   );
    //}

    @Override
    protected void onRestart() {
        super.onRestart();
        if(App.getChange()){
            recreate();
            App.setChange(false);
        }
    }

    @Override
    public void onBackPressed() {
        if(drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_home:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home()).commit();
                break;
            case R.id.nav_calendar:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Calendar()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}