package com.example.jsuisla;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class ConfigMessage extends AppCompatActivity {

    TextView TVmessage;
    String message;
    RadioGroup radioGroup;
    RadioButton RB_adress;
    RadioButton RB_time;
    EditText ET_adresse;
    TextView TV_time;
    TextView TV_Date;

    Boolean conf_position = false;
    Boolean conf_time = false;

    public static final int PERM_COMPLETED_STORAGE_ACCESS = 1;
    public static final int WRITE_FILE_REQUEST_CODE = 43;

    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;

    private int lastSelectedDay = -1;
    private int lastSelectedMonth = -1;
    private int lastSelectedYear = -1;

    public static String id = "test_channel_01";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_message);
        Intent intent = getIntent();

        message = intent.getStringExtra("message");

        TVmessage = (TextView) findViewById(R.id.TV_affiche_message);
        TVmessage.setText(message);

        TV_Date = (TextView) findViewById(R.id.TV_Date);
        TV_Date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastSelectedDay == -1)  {
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    lastSelectedDay = c.get(Calendar.DAY_OF_MONTH);
                    lastSelectedMonth = c.get(Calendar.MONTH);
                    lastSelectedYear = c.get(Calendar.YEAR);
                }
                showDatePicker();

            }
        });


        TV_time = (TextView) findViewById(R.id.TV_time);
        TV_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(lastSelectedHour == -1)  {
                    // Get Current Time
                    final Calendar c = Calendar.getInstance();
                    lastSelectedHour = c.get(Calendar.HOUR_OF_DAY);
                    lastSelectedMinute = c.get(Calendar.MINUTE);
                }
                showTimePicker();

            }
        });

        TV_time.setVisibility(View.INVISIBLE);
        TV_Date.setVisibility(View.INVISIBLE);

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
            TV_time.setVisibility(View.INVISIBLE);
            TV_Date.setVisibility(View.INVISIBLE);
        }else if(checkedId == R.id.RB_time){
            conf_time = true;
            TV_time.setVisibility(View.VISIBLE);
            TV_Date.setVisibility(View.VISIBLE);
        }
    }
    /*
     * The service will call the handler to send back information.
     */
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            Object path = msg.obj;
            Toast.makeText(getBaseContext(), path.toString(), Toast.LENGTH_LONG).show();
            return true;
        }
    });

    public void ValidConfig(View view){
        if(conf_time){
            final Calendar current = Calendar.getInstance();
            final Calendar toValid = Calendar.getInstance();
            toValid.set(lastSelectedYear,lastSelectedMonth,lastSelectedDay,lastSelectedHour,lastSelectedMinute);
            if (current.before(toValid)){
                Intent intent = new Intent(this, MyService.class);

                intent.putExtra("year", toValid.get(Calendar.YEAR));
                intent.putExtra("month", toValid.get(Calendar.MONTH));
                intent.putExtra("day", toValid.get(Calendar.DAY_OF_MONTH));
                intent.putExtra("hour", toValid.get(Calendar.HOUR_OF_DAY));
                intent.putExtra("minutes", toValid.get(Calendar.MINUTE));


                //intent.putExtra("calendar", toValid);


                Messenger messenger = new Messenger(handler);
                intent.putExtra("MESSENGER", messenger);
                createchannel();
                this.startService(intent);
            }else{
                Toast.makeText(getBaseContext(), "Merci d'entrer une date supérieur à la date actuelle", Toast.LENGTH_LONG).show();
            }

        }
    }
    /**
     * for API 26+ create notification channels
     */
    private void createchannel() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationChannel mChannel = new NotificationChannel(id,
                    "R.string.channel_name",  //name of the channel
                    NotificationManager.IMPORTANCE_DEFAULT);   //importance level
            //important level: default is is high on the phone.  high is urgent on the phone.  low is medium, so none is low?
            // Configure the notification channel.
            mChannel.setDescription("testChanel");
            mChannel.enableLights(true);
            //Sets the notification light color for notifications posted to this channel, if the device supports this feature.
            mChannel.setLightColor(Color.RED);
            mChannel.enableVibration(true);
            mChannel.setShowBadge(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
            nm.createNotificationChannel(mChannel);

        }
    }
    //handle the response, but fully in the fragment.
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
    }

    Calendar date;
    public void showDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year,
                                  int monthOfYear, int dayOfMonth) {

                lastSelectedDay = dayOfMonth;
                lastSelectedMonth = monthOfYear;
                lastSelectedYear = year;
                TV_Date.setText(dayOfMonth + "-" + (monthOfYear) + "-" + year);
            }
        };

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                dateSetListener, lastSelectedYear, lastSelectedMonth, lastSelectedDay);

        // Show
        datePickerDialog.show();
    }

    public void showTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TV_time.setText(hourOfDay + ":" + minute );
                lastSelectedHour = hourOfDay;
                lastSelectedMinute = minute;
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                timeSetListener, lastSelectedHour, lastSelectedMinute, true);

        // Show
        timePickerDialog.show();
    }
}