package com.example.myapplication;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Fragment1 extends Fragment {

    private EditText editTextName;
    private Button buttonContinue;

    public Fragment1() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_1, container, false);

        editTextName = view.findViewById(R.id.editTextName);
        buttonContinue = view.findViewById(R.id.buttonContinue);

        // بدايةً: الزر غير مفعل
        buttonContinue.setEnabled(false);

        // تفعيل الزر عند إدخال اسم
        editTextName.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // تفعيل الزر إذا النص ليس فارغًا
                buttonContinue.setEnabled(!s.toString().trim().isEmpty());
            }

            @Override public void afterTextChanged(Editable s) {}
        });

        buttonContinue.setOnClickListener(v -> {
            String name = editTextName.getText().toString().trim();
            if (name.isEmpty()) {
                Toast.makeText(getActivity(), "Please enter your name", Toast.LENGTH_SHORT).show();
            } else {
                Fragment2 fragment2 = Fragment2.newInstance(name);
                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_container, fragment2)
                        .addToBackStack(null)
                        .commit();
            }
        });

        return view;
    }
}
