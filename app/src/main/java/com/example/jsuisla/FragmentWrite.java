package com.example.jsuisla;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentWrite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentWrite extends Fragment {

    public Button butonValidText;
    public EditText editText;

    private String text;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View root = inflater.inflate(R.layout.fragment_write, container, true);
        butonValidText = (Button)root.findViewById(R.id.ButtonValidText);
        butonValidText.setOnClickListener(ValidText);
        editText = (EditText)root.findViewById(R.id.editText);
        String text = editText.getText().toString();

        return root;
    }

    private View.OnClickListener ValidText = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Log.i("DEBUG", text);
        }
    };

}