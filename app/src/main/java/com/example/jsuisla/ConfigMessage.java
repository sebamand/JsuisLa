package com.example.jsuisla;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ConfigMessage extends AppCompatActivity {

    TextView TVmessage;
    String message;
    RadioGroup radioGroup;
    RadioButton RB_adress;
    RadioButton RB_time;
    EditText ET_adresse;

    Boolean conf_position = false;
    Boolean conf_time = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_message);
        Intent intent = getIntent();

        message = intent.getStringExtra("message");

        TVmessage = (TextView) findViewById(R.id.TV_affiche_message);
        TVmessage.setText(message);

        RB_adress = (RadioButton) findViewById(R.id.RB_position);
        RB_time = (RadioButton) findViewById(R.id.RB_time);

        radioGroup = (RadioGroup) findViewById(R.id.radio_group_condition);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radioChange(checkedId);
            }
        });
    }

    public void radioChange(int checkedId){
        if (checkedId == R.id.RB_position){
            conf_position = true;
        }else if(checkedId == R.id.RB_position){
            conf_time = true;
        }
    }

    public void ValidConfig(View view){
        if(conf_position){
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        }
    }
}