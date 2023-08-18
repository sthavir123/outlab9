package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class UpdateActivity extends AppCompatActivity {

    TextView title_input,date_input,time_input,des_input;
    Button update_button, delete_button;
    String type,time,date,des,id;
    Integer tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        title_input = findViewById(R.id.title_input);
        date_input = findViewById(R.id.date_input);
        time_input = findViewById(R.id.time_input);
        des_input = findViewById(R.id.des_input);
        //update_button = findViewById(R.id.update);
        delete_button = findViewById(R.id.delete);
        getAndSetIntentData();
        //update_button.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View v) {
        //        DatabaseHelper myDb = new DatabaseHelper(App.getContext());
        //        myDb.updateData(id,type,time,date,des,tab);
        //        App.setChange(true);
        //        finish();
        //    }
        //});

        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper myDb = new DatabaseHelper(App.getContext());
                myDb.deleteRow(id);
                App.setChange(true);
                finish();
            }
        });

       // getAndSetIntentData();


    }

    void getAndSetIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("type") && getIntent().hasExtra("time") &&
                getIntent().hasExtra("date") && getIntent().hasExtra("des")){
            type = getIntent().getStringExtra("type");
            time = getIntent().getStringExtra("time");
            date = getIntent().getStringExtra("date");
            des = getIntent().getStringExtra("des");
            id = getIntent().getStringExtra("id");
            tab = getIntent().getIntExtra("tab",0);

            title_input.setText(type);
            time_input.setText(time);
            date_input.setText(date);
            des_input.setText(des);



        }

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();

    }

}