package com.example.jsuisla;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Messenger;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class ConfigMessage extends AppCompatActivity {

    TextView TVmessage;
    String message;
    RadioGroup radioGroup;
    RadioButton RB_adress;
    RadioButton RB_time;
    EditText ET_adresse;

    Boolean conf_position = false;
    Boolean conf_time = false;

    public static final int PERM_COMPLETED_STORAGE_ACCESS = 1;
    public static final int WRITE_FILE_REQUEST_CODE = 43;

    public static String id = "test_channel_01";



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
        }else if(checkedId == R.id.RB_time){
            conf_time = true;
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

            Intent intent = new Intent(this, MyService.class);
            intent.putExtra("times", 5);
            Messenger messenger = new Messenger(handler);
            intent.putExtra("MESSENGER", messenger);
            createchannel();
            this.startService(intent);

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
}