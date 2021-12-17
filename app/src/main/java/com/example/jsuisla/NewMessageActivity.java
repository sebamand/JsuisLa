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
    EditText ETphoneNumber;
    Button BTenvoi;

    String message;
    String phoneNumber="+33698369197";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_message);
        Intent intent = getIntent();
        ETphoneNumber = findViewById(R.id.editText_phone);
        ETphoneNumber.setText(phoneNumber);
        ETmessage = findViewById(R.id.editText_message);
        BTenvoi = findViewById(R.id.ButtonValidText);
    }

    public void ValidText(View view){
        phoneNumber = ETphoneNumber.getText().toString();
        message = ETmessage.getText().toString();
        if(!TextUtils.isEmpty(message)){
            if(!TextUtils.isEmpty(phoneNumber)){
                Intent intent = new Intent(NewMessageActivity.this, ConfigMessage.class);
                intent.putExtra("message",message);
                intent.putExtra("phoneNumber",phoneNumber);
                startActivity(intent);
            }else{
                new AlertDialog.Builder(NewMessageActivity.this)
                        .setTitle("Le numéro de téléphone est vide")
                        .setMessage("Merci de remplir la zone de text avec le numéro au quel vous souhaitez envoyer un message\n ex : 0607080910")
                        .show();
            }

        }else{
            new AlertDialog.Builder(NewMessageActivity.this)
                    .setTitle("Le message est vide")
                    .setMessage("Merci de remplir la zone de text avec le message que vous souhaitez envoyer")
                    .show();
        }

    }


}