package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class Fragment2 extends Fragment {

    private EditText emailEditText, universityEditText;
    private RadioGroup genderRadioGroup;
    private Button continueButton;
    private String userName;

    public interface OnFragment2Listener {
        void onDataFromFragment2(String name, String email, String gender, String university);
    }

    private OnFragment2Listener listener;

    public Fragment2() {}

    public static Fragment2 newInstance(String name) {
        Fragment2 fragment = new Fragment2();
        Bundle args = new Bundle();
        args.putString("name", name);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragment2Listener) {
            listener = (OnFragment2Listener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragment2Listener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_2, container, false);

        emailEditText = view.findViewById(R.id.emailEditText);
        universityEditText = view.findViewById(R.id.universityEditText);
        genderRadioGroup = view.findViewById(R.id.genderRadioGroup);
        continueButton = view.findViewById(R.id.continueButton);

        // في البداية الزر غير مفعل
        continueButton.setEnabled(false);

        if (getArguments() != null) {
            userName = getArguments().getString("name");
        }

        // مراقبة تغيّر النص
        TextWatcher watcher = new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkInputs();
            }
            @Override public void afterTextChanged(Editable s) {}
        };

        emailEditText.addTextChangedListener(watcher);
        universityEditText.addTextChangedListener(watcher);

        // مراقبة تغيّر اختيار الجنس
        genderRadioGroup.setOnCheckedChangeListener((group, checkedId) -> checkInputs());

        continueButton.setOnClickListener(v -> {
            String email = emailEditText.getText().toString().trim();
            String university = universityEditText.getText().toString().trim();

            int selectedId = genderRadioGroup.getCheckedRadioButtonId();
            String gender = "";

            if (selectedId != -1) {
                RadioButton selectedRadio = view.findViewById(selectedId);
                gender = selectedRadio.getText().toString();
            }

            if (listener != null) {
                listener.onDataFromFragment2(userName, email, gender, university);
            }
        });

        return view;
    }

    private void checkInputs() {
        String email = emailEditText.getText().toString().trim();
        String university = universityEditText.getText().toString().trim();
        boolean isGenderSelected = genderRadioGroup.getCheckedRadioButtonId() != -1;

        // تفعيل الزر فقط إذا كانت كل الحقول ممتلئة
        continueButton.setEnabled(!email.isEmpty() && !university.isEmpty() && isGenderSelected);
    }
}
