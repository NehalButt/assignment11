package com.example.assignment11;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class Events_details extends AppCompatActivity {
    ListView mylist;
    Dbhelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_details);
        mylist = (ListView) findViewById(R.id.Mylist);
        dbhelper =  new Dbhelper(Events_details.this);
        ArrayList<HashMap<String, String>> getdata = dbhelper.fetch();

        ListAdapter myadapter = new SimpleAdapter(this ,
                getdata,
                R.layout.custom_layout,
                new String[]{"Event_Name" , "Event_date" , "Guest"},
                new int[]{R.id.Name , R.id.Date , R.id.EGuest}
                );

        mylist.setAdapter(myadapter);
    }
}