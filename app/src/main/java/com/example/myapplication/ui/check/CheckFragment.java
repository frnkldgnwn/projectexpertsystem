package com.example.myapplication.ui.check;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.myapplication.R;
import com.example.myapplication.ResultActivity;
import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class CheckFragment extends Fragment {

    private CheckViewModel checkViewModel;
    private ArrayList<TextInputEditText> editTexts;
    private AutoCompleteTextView pregnancies;
    private TextInputEditText glucose;
    private TextInputEditText bloodPressure;
    private TextInputEditText skinThickness;
    private TextInputEditText insulin;
    private TextInputEditText bmi;
    private TextInputEditText age;
    private CardView checkButton;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        checkViewModel = ViewModelProviders.of(this).get(CheckViewModel.class);
        View root = inflater.inflate(R.layout.fragment_check, container, false);

        initializeUI(root);

        initializeOnClickListener();

        return root;
    }

    private void initializeUI(View root) {
        pregnancies = root.findViewById(R.id.pregnancies);
        pregnancies.setDropDownHeight(500);

        String[] VALUES = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), R.layout.dropdown_menu_popup_item, VALUES);
        pregnancies.setAdapter(adapter);

        glucose = root.findViewById(R.id.glucose_level);
        bloodPressure = root.findViewById(R.id.blood_pressure);
        skinThickness = root.findViewById(R.id.skin_thickness);
        insulin = root.findViewById(R.id.insulin_level);
        bmi = root.findViewById(R.id.body_mass_index);
        age = root.findViewById(R.id.age);

        checkButton = root.findViewById(R.id.buttonCheck);
        editTexts = new ArrayList<TextInputEditText>() {{
            add(glucose);
            add(bloodPressure);
            add(skinThickness);
            add(insulin);
            add(bmi);
            add(age);
        }};
    }

    private void initializeOnClickListener() {
        pregnancies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InputMethodManager in = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                in.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
            }
        });

        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allFilled = true;

                if (TextUtils.isEmpty(pregnancies.getText())) {
                    pregnancies.setError("This field is required!");
                    allFilled = false;
                }

                for (int i = 0; i < editTexts.size(); i++) {
                    if (TextUtils.isEmpty(editTexts.get(i).getText())) {
                        editTexts.get(i).setError("This field is required!");
                        allFilled = false;
                    }
                }

                if (allFilled) {
                    Intent intent = new Intent(CheckFragment.super.getContext(), ResultActivity.class);
                    intent.putExtra("pregnancies", pregnancies.getText().toString());
                    intent.putExtra("glucose", glucose.getText().toString());
                    intent.putExtra("blood_pressure", bloodPressure.getText().toString());
                    intent.putExtra("skin_thickness", skinThickness.getText().toString());
                    intent.putExtra("insulin", insulin.getText().toString());
                    intent.putExtra("bmi", bmi.getText().toString());
                    intent.putExtra("age", age.getText().toString());
                    startActivity(intent);
                    clearForm();
                }
            }
        });
    }

    private void clearForm() {
        for (TextInputEditText a : editTexts) {
            a.setText("");
        }
    }

}