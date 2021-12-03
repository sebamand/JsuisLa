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
    private Bundle savedInstanceState;
    private String text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        String someInt = requireArguments().getString("some_int");
        View root = inflater.inflate(R.layout.fragment_write, container, false);
        butonValidText = (Button)root.findViewById(R.id.ButtonValidText);
        butonValidText.setOnClickListener(ValidText);
        editText = (EditText)root.findViewById(R.id.editText);
        editText.setText(someInt);

        return root;
    }
    //last_version

    private View.OnClickListener ValidText = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            text = editText.getText().toString();
            Intent in = new Intent(getActivity(), MainActivity.class);
            in.putExtra("frgm_write",text);
            //requireArguments().putString("fgm_write", text);
            //savedInstanceState.putString("fgm_write", text);
        }
    };

}