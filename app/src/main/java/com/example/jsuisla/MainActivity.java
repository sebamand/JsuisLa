package com.example.jsuisla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentOnAttachListener;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity{

    //token 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



    public Bundle bundle;
    public ImageButton btnNewMessage;
    public ImageButton oldMessage;
    public ImageButton configUser;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            bundle = new Bundle();
            
            btnNewMessage = (ImageButton) findViewById(R.id.button_New_Message);
            oldMessage = (ImageButton) findViewById(R.id.button_Old_Message);
            configUser = (ImageButton) findViewById(R.id.button_Config_User);



        }



    }

    public void NewMessage(View view){
        Intent intent = new Intent(MainActivity.this,NewMessageActivity.class);
        startActivity(intent);
    }







}
