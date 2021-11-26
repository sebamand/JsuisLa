package com.example.jsuisla;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    //token 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




    public TextView textView;

    public FragmentActivity fragment_write;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            Bundle bundle = new Bundle();
            bundle.putString("some_int", "Oui mon string viens du main");

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.fragmentContainerView5, FragmentWrite.class, bundle)
                    .commit();
        }

    }

    public void onClick(View v)
    {

    }

}
