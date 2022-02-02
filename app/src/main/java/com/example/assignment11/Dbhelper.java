package com.example.assignment11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class Dbhelper extends SQLiteOpenHelper {
    String Databasename  = "Event.db";
    public Dbhelper(@Nullable Context context) {
        super(context, "Event.db", null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table Event(Id integer primary key autoincrement , Event_Name text , Event_date Integer , Guest Integer)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("Drop table if exists Event");
    }
    public boolean Inserted(String Event_Name , String Date , int Guest){
        SQLiteDatabase db = this.getWritableDatabase();
         Date = new SimpleDateFormat("yyyy.MM.dd").format(Calendar.getInstance().getTime());
        ContentValues insert = new ContentValues();
        insert.put("Event_Name" , Event_Name);
        insert.put("Event_date" , Date);
        insert.put("Guest" , Guest);

        long result = db.insert("Event" , null , insert);
        if (result > 0){
            return true;
        }
        else{
            return false;
        }
    }
        public ArrayList<HashMap<String , String>> fetch(){
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor fetchdata = db.rawQuery("Select Event_Name , Event_date , Guest from Event" , null);
            ArrayList<HashMap<String , String>> records = new ArrayList<>();
            while (fetchdata.moveToNext()){
                HashMap<String , String> coloums = new HashMap<>();
                coloums.put("Event_Name" , fetchdata.getString(0));
                coloums.put("Event_date" , fetchdata.getString(1));
                coloums.put("Guest" , fetchdata.getString(2));
                records.add(coloums);
            }
            return records;
        }

    }

