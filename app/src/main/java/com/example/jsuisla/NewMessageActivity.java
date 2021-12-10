package com.example.jsuisla;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class NewMessageActivity extends AppCompatActivity {

    EditText ETmessage;
    Button BTenvoi;

    String message;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        Intent intent = getIntent();

        ETmessage = findViewById(R.id.editText_message);
        BTenvoi = findViewById(R.id.ButtonValidText);
    }

    public void ValidText(View view){
        message = ETmessage.getText().toString();
        if(!TextUtils.isEmpty(message)){

            Intent intent = new Intent(NewMessageActivity.this, ConfigMessage.class);
            intent.putExtra("message",message);
            startActivity(intent);


        }else{
            new AlertDialog.Builder(NewMessageActivity.this)
                    .setTitle("Le message est vide")
                    .setMessage("Merci de remplir la zone de text avec le message que vous souhaitez envoyer")
                    .show();
        }

    }


}