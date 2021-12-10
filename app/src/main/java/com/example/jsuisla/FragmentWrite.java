package com.example.jsuisla;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class FragmentWrite extends Fragment {

    public Button butonValidText;
    public EditText editText;

    private String message;

    private fragment_config fgmConfig;
    public FragmentWrite() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        //String someInt = requireArguments().getString("some_int");
        Intent in = new Intent(getActivity(), MainActivity.class);
        View root = inflater.inflate(R.layout.fragment_write, container, false);
        butonValidText = (Button)root.findViewById(R.id.ButtonValidText);
        butonValidText.setOnClickListener(ValidText);
        editText = (EditText)root.findViewById(R.id.editText);
        editText.setText(message);
        //root.findViewById(R.id.fragmentContainerView5)
        return root;
    }

    public String getMessageContent(){
        return this.message;
    }


    public void setMessage(String pmessage){
        this.message = pmessage;
    }

    private View.OnClickListener ValidText = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            message = editText.getText().toString();
            fgmConfig = new fragment_config();
            fgmConfig.setMessage("ça marche");
            //getActivity().getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainerView6,fgmConfig);
            //getActivity().getSupportFragmentManager().beginTransaction().remove(FragmentWrite.this).commit();
        }
    };

}