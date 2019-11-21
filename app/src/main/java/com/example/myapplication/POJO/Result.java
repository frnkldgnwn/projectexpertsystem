package com.example.myapplication.POJO;

public class Result {

    private String pregnancies;
    private String glucose;
    private String bloodPressure;
    private String skinThickess;
    private String insulin;
    private String bmi;
    private String age;
    private String result;

    public Result() {}

    public void setValue(String pregnancies, String glucose, String bloodPressure, String skinThickess,
                  String insulin, String bmi, String age, String result) {
        this.pregnancies = pregnancies;
        this.glucose = glucose;
        this.bloodPressure = bloodPressure;
        this.skinThickess = skinThickess;
        this.insulin = insulin;
        this.bmi = bmi;
        this.age = age;
        this.result = result;
    }

    public String getPregnancies() {
        return pregnancies;
    }

    public String getGlucose() {
        return glucose;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public String getSkinThickess() {
        return skinThickess;
    }

    public String getInsulin() {
        return insulin;
    }

    public String getBmi() {
        return bmi;
    }

    public String getAge() {
        return age;
    }

    public String getResult() {
        return result;
    }
}
