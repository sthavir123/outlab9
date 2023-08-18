package com.example.test2;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class Home extends Fragment{
    @Nullable

    DatabaseHelper myDb;

    private TextView date_view;
    private TextView StudyPlanCount;
    private TextView LectureCount;
    private TextView AssignmentCount;
    private TextView ExamCount;
    private String temp_date;
    CalendarView calendar;
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View homeView = inflater.inflate(R.layout.fragment_home,container,false);

        calendar = (CalendarView) homeView.findViewById(R.id.calendar);
        date_view = (TextView) homeView.findViewById(R.id.date_view);
        temp_date = new SimpleDateFormat("d/M/yyyy", Locale.getDefault()).format(new Date());
        date_view.setText(temp_date);

        StudyPlanCount = (TextView) homeView.findViewById(R.id.StudyPlanCount);
        LectureCount = (TextView) homeView.findViewById(R.id.LectureCount);
        AssignmentCount = (TextView) homeView.findViewById(R.id.AssignmentCount);
        ExamCount = (TextView) homeView.findViewById(R.id.ExamCount);

        myDb = new DatabaseHelper(App.getContext());
        Cursor cursor1=myDb.getMyData1(date_view.getText().toString());
        Cursor cursor2=myDb.getMyData2(date_view.getText().toString());
        Cursor cursor3=myDb.getMyData3(date_view.getText().toString());
        Cursor cursor4=myDb.getMyData4(date_view.getText().toString());
        int a1 =cursor1.getCount(),a2=cursor2.getCount(),a3=cursor3.getCount(),a4=cursor4.getCount();
        StudyPlanCount.setText(String.valueOf(a1));
        AssignmentCount.setText(String.valueOf(a2));
        ExamCount.setText(String.valueOf(a3));
        LectureCount.setText(String.valueOf(a4));

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                temp_date = dayOfMonth + "/" + (month+1) + "/" +year;
                //set this date in TextView for display
                date_view.setText(temp_date);
                Cursor cursor1=myDb.getMyData1(date_view.getText().toString());
                Cursor cursor2=myDb.getMyData2(date_view.getText().toString());
                Cursor cursor3=myDb.getMyData3(date_view.getText().toString());
                Cursor cursor4=myDb.getMyData4(date_view.getText().toString());
                int a1 =cursor1.getCount(),a2=cursor2.getCount(),a3=cursor3.getCount(),a4=cursor4.getCount();
                StudyPlanCount.setText(String.valueOf(a1));
                AssignmentCount.setText(String.valueOf(a2));
                ExamCount.setText(String.valueOf(a3));
                LectureCount.setText(String.valueOf(a4));
            }
        });



        return homeView;
    }

}
