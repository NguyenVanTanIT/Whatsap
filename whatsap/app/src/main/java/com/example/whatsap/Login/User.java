package com.example.whatsap.Login;

public class User {

    private String mPhone;
    private String  mName;
    private String mPassword;
    private String mSex;
    private String avata;
    private  String text;

    public User() {
    }

    public User(String mPhone, String mName, String mPassword, String mSex, String avata) {
        this.mPhone = mPhone;
        this.mName = mName;
        this.mPassword = mPassword;
        this.mSex = mSex;
        this.avata = avata;
    }

    public User(String mPhone, String mName) {
        this.mPhone = mPhone;
        this.mName = mName;
    }

    public String getmPhone() {
        return mPhone;
    }

    public void setmPhone(String mPhone) {
        this.mPhone = mPhone;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmPassword() {
        return mPassword;
    }

    public void setmPassword(String mPassword) {
        this.mPassword = mPassword;
    }

    public String getmSex() {
        return mSex;
    }

    public void setmSex(String mSex) {
        this.mSex = mSex;
    }

    public String getAvata() {
        return avata;
    }

    public void setAvata(String avata) {
        this.avata = avata;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public User(String mPhone, String mName, String mPassword, String mSex, String avata, String text) {
        this.mPhone = mPhone;
        this.mName = mName;
        this.mPassword = mPassword;
        this.mSex = mSex;
        this.avata = avata;
        this.text = text;
    }
}