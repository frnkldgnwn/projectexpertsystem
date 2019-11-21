package com.example.myapplication.POJO;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication.R;

import java.util.HashMap;

public class GetResult extends AsyncTask<Void, Void, Void> {

    private ProgressDialog progressDialog;
    private Context context;
    private TextView result;
    private ImageView imageResult;
    private Result resultDetails;
    private String pregnancies;
    private String glucose;
    private String bloodPressure;
    private String skinThickess;
    private String insulin;
    private String bmi;
    private String age;
    private HashMap<String, String> resultHash;

    public GetResult(Context context, TextView result, ImageView imageResult, Result resultDetails, String pregnancies, String glucose, String bloodPressure,
                     String skinThickess, String insulin, String bmi, String age) {
        this.context = context;
        this.result = result;
        this.imageResult = imageResult;
        this.resultDetails = resultDetails;
        this.pregnancies = pregnancies;
        this.glucose = glucose;
        this.bloodPressure = bloodPressure;
        this.skinThickess = skinThickess;
        this.insulin = insulin;
        this.bmi = bmi;
        this.age = age;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setMessage("Fetching your result..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // rest api here
        resultHash = new HashMap<String, String>() {{
            put("result", "NORMAL");
        }};
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);

        if (progressDialog.isShowing()) {
            progressDialog.dismiss();
        }

        resultDetails.setValue(pregnancies, glucose, bloodPressure, skinThickess, insulin, bmi, age, resultHash.get("result"));

        if (resultHash.get("result").equals("NORMAL")) {
            imageResult.setImageResource(R.drawable.ic_undraw_feeling_happy);
        } else {
            imageResult.setImageResource(R.drawable.ic_undraw_feeling_sad);
        }

        result.setText(resultHash.get("result"));
    }

}
