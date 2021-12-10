package com.example.jsuisla;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragment_config#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragment_config extends Fragment {

    TextView afficheMessage;

    private String message;

    public fragment_config() {
        // Required empty public constructor
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        //String someInt = requireArguments().getString("some_int");
        Intent in = new Intent(getActivity(), MainActivity.class);
        View root = inflater.inflate(R.layout.fragment_write, container, false);
        afficheMessage = (TextView)root.findViewById(R.id.TV_affiche_message);
        afficheMessage.setText(message);
        //afficheMessage.setOnClickListener(ValidText);

        return root;
    }

    public void setMessage(String pmessage){
        this.message = pmessage;
    }

}