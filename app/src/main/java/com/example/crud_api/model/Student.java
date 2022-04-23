package com.example.crud_api.model;

import com.google.gson.annotations.SerializedName;

public class Student {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("student_code")
    private String 	student_code;
    @SerializedName("grade")
    private String 	grade;
    @SerializedName("major")
    private String major;
    @SerializedName("date")
    private String date;
    @SerializedName("image")
    private String image;
    @SerializedName("value")
    private String value;
    @SerializedName("message")
    private String massage;
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStudent_code() {
        return student_code;
    }

    public void setStudent_code(String student_code) {
        this.student_code = student_code;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }



    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMassage() {
        return massage;
    }

    public void setMassage(String massage) {
        this.massage = massage;
    }


    public Student() {

    }
}
