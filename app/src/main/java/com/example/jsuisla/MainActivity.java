package com.example.jsuisla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainer;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.zip.Inflater;

public class MainActivity extends AppCompatActivity{

    //token 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




    public TextView textView;
    public Bundle bundle;
    public FragmentWrite fragmentWrite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            bundle = new Bundle();
            //bundle.putString("some_int", "Oui mon string vient du main");
            fragmentWrite = new FragmentWrite();
            fragmentWrite.setMessage("oui le string vient du main");
            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerView5, fragmentWrite)
                    .commit();

        }

        textView = (TextView) findViewById(R.id.text_home);
        textView.setOnClickListener(AfficheText);

    }

    public View.OnClickListener AfficheText = new View.OnClickListener()
    {
        @Override
        public void onClick(View v){

            textView.setBackground(null);
            String textMessage = fragmentWrite.getMessageContent();
            textView.setText(textMessage);
        }
    };

}
