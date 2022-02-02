package com.example.assignment11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.lights.Light;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText Eventname,Eventdate,Eventtime,Guest;
    Spinner Ramp,Music,Lights;
    Button Register;
    Dbhelper dbhelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Eventname = (EditText) findViewById(R.id.EventName);
        Eventdate = (EditText) findViewById(R.id.Eventdate);
        Eventtime = (EditText) findViewById(R.id.Eventtime);
        Guest = (EditText) findViewById(R.id.Guest);
        Register = (Button) findViewById(R.id.Register);
        dbhelper = new Dbhelper(MainActivity.this);

        Ramp = (Spinner) findViewById(R.id.Ramp);
        Music = (Spinner) findViewById(R.id.Music);
        Lights = (Spinner) findViewById(R.id.Lights);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Ramp,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.Music,
                android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Light,
                android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Ramp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Ramp Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Music.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Music Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Lights.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this, "Lights Added", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        Ramp.setAdapter(adapter);
        Music.setAdapter(adapter1);
        Lights.setAdapter(adapter2);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventname = Eventname.getText().toString();
                String eventdate = Eventdate.getText().toString();
                int guest = Integer.parseInt(Guest.getText().toString());
                boolean success = dbhelper.Inserted(eventname , eventdate , guest);
                if (success == true) {
                    Intent intent = new Intent(MainActivity.this, Events_details.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}