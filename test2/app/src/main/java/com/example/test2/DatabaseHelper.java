package com.example.test2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {


    private Context context;
    public static final String DATABASE_NAME = "Event.db";
    public static final String LIST_NAME = "EventList";
    public static final String COLUMN_ID = "id";
    public static final String TYPE = "TYPE";
    public static final String DATE = "DATE";
    public static final String TIME = "TIME";
    public static final String DES = "DESCRIPTION";
    public static final String TAB = "TAB";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LIST_NAME + "(_id INTEGER PRIMARY KEY AUTOINCREMENT, TYPE TEXT, DATE TEXT, TIME TEXT, DESCRIPTION TEXT, TAB INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+LIST_NAME);
        onCreate(db);
    }

    public boolean insertData(String type,String date,String time,String description,Integer tab){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(TYPE,type);
        contentvalues.put(DATE,date);
        contentvalues.put(TIME,time);
        contentvalues.put(DES,description);
        contentvalues.put(TAB,tab);
        long result = db.insert(LIST_NAME,null,contentvalues);
        if(result == -1){
            return false;
        }
        else return true;
    }

    Cursor readAllData(){
        String query = "SELECT * FROM " + LIST_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor =  db.rawQuery(query,null);
        }
        return cursor;
    }

    Cursor getMyData1(String date){
        String query ="SELECT * FROM " + LIST_NAME +" WHERE TAB=1 AND DATE = ?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =null;
        String[] args = {date};
        if(db != null) cursor = db.rawQuery(query, args);
        return cursor;
    }
    Cursor getMyData2(String date){
        String query ="SELECT * FROM " + LIST_NAME +" WHERE TAB=2 AND DATE =?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =null;
        String[] args = {date};
        if(db != null) cursor = db.rawQuery(query, args);
        return cursor;
    }
    Cursor getMyData3(String date){
        String query ="SELECT * FROM " + LIST_NAME +" WHERE TAB=3 AND DATE =?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =null;
        String[] args = {date};
        if(db != null) cursor = db.rawQuery(query,args);
        return cursor;
    }
    Cursor getMyData4(String date){
        String query ="SELECT * FROM " + LIST_NAME +" WHERE TAB=4 AND DATE =?";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =null;
        String[] args = {date};
        if(db != null) cursor = db.rawQuery(query, args);
        return cursor;
    }

    void updateData(String row_id, String title,String time,String date,String des,Integer tab){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(TYPE, title);
        cv.put(TIME,time);
        cv.put(DATE,date);
        cv.put(DES,des);
        cv.put(TAB,tab);
        long result = db.update(LIST_NAME,cv,"_id = ?",new String[]{row_id});

    }
    void deleteRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(LIST_NAME,"_id = ?",new String[]{row_id});
    }

}
