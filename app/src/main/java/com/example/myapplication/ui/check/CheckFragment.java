package com.example.myapplication.ui.check;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private TextInputEditText bloodPlasmaGlucose;
    private TextInputEditText hba1c;
    private TextInputEditText lipidSerum;
    private TextInputEditText urinaryKetones;
    private TextInputEditText glucoseMoment;
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
        bloodPlasmaGlucose = root.findViewById(R.id.blood_plasma_glucose);
        hba1c = root.findViewById(R.id.hba1c);
        lipidSerum = root.findViewById(R.id.lipid_serum);
        urinaryKetones = root.findViewById(R.id.urinary_ketones);
        glucoseMoment = root.findViewById(R.id.glucose_moment);
        checkButton = root.findViewById(R.id.buttonCheck);
        editTexts = new ArrayList<TextInputEditText>() {{
            add(bloodPlasmaGlucose);
            add(hba1c);
            add(lipidSerum);
            add(urinaryKetones);
            add(glucoseMoment);
        }};
    }

    private void initializeOnClickListener() {
        checkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean allFilled = true;

                for (int i = 0; i < editTexts.size(); i++) {
                    if (TextUtils.isEmpty(editTexts.get(i).getText())) {
                        editTexts.get(i).setError("This field is required!");
                        allFilled = false;
                    }
                }

                if (allFilled) {
                    // getResult();
                    Intent intent = new Intent(CheckFragment.super.getContext(), ResultActivity.class);
                    intent.putExtra("result", "NORMAL");
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