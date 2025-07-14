package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class Fragment3 extends Fragment {

    private static final String ARG_NAME = "name";
    private String userName;

    private TextView nameTextView;
    private CheckBox confirmCheckbox;
    private Button continueButton;

    public Fragment3() {}

    public static Fragment3 newInstance(String name) {
        Fragment3 fragment = new Fragment3();
        Bundle args = new Bundle();
        args.putString(ARG_NAME, name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_3, container, false);

        nameTextView = view.findViewById(R.id.nameTextView);
        confirmCheckbox = view.findViewById(R.id.confirmCheckbox);
        continueButton = view.findViewById(R.id.continueButton);

        if (getArguments() != null) {
            userName = getArguments().getString(ARG_NAME);
            nameTextView.setText("Welcome, " + userName);
        }

        continueButton.setEnabled(false);
        continueButton.setText("Continue");

        confirmCheckbox.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                continueButton.setEnabled(true);
                continueButton.setText("Finish");
            } else {
                continueButton.setEnabled(false);
                continueButton.setText("Continue");
            }
        });

        continueButton.setOnClickListener(v -> {
            Toast.makeText(getActivity(), "Thank you, " + userName + "!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }
}
