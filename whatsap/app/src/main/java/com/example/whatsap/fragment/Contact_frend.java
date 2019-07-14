package com.example.whatsap.fragment;

import android.graphics.Bitmap;

public class Contact_frend  {
    private  String mName;
    private int mPhoneNumber;
    private Bitmap mAvata;

    public Contact_frend(String mName, int mPhoneNumber, Bitmap mAvata) {
        this.mName = mName;
        this.mPhoneNumber = mPhoneNumber;
        this.mAvata = mAvata;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public int getmPhoneNumber() {
        return mPhoneNumber;
    }

    public void setmPhoneNumber(int mPhoneNumber) {
        this.mPhoneNumber = mPhoneNumber;
    }

    public Bitmap getmAvata() {
        return mAvata;
    }

    public void setmAvata(Bitmap mAvata) {
        this.mAvata = mAvata;
    }
}
