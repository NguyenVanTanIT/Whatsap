package com.example.whatsap.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsap.ListSMS.AdapterSMS;
import com.example.whatsap.Login.User;
import com.example.whatsap.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import static com.example.whatsap.MainActivity.phoneUser;
import java.util.ArrayList;

public class fragment_sms extends Fragment  {
    RecyclerView recyclerView;
    AdapterSMS adapterSMS;
    String keyphone;
    ArrayList<User> listfrend = new ArrayList<>();
    DatabaseReference mdata,mdatauser;
    String avata="aaaa",mName = null,mPassword=null,mPhone=null,mSex=null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addcontrol();
    }

    private void addcontrol() {

        mdata = FirebaseDatabase.getInstance().getReference("phone");
        mdata.child(phoneUser).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String data = dataSnapshot.getValue().toString();
                Log.d("aaaaaaaaaaa",data);
                keyphone = data;
                lisitemfrend();

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void lisitemfrend() {
        mdatauser = FirebaseDatabase.getInstance().getReference("user");
        mdatauser.child(keyphone).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                  Log.d("aaaaaaaa", dataSnapshot.getValue().toString());
//                if (dataSnapshot.getKey().toString().equals("avata")){
//                    avata=dataSnapshot.getValue().toString();
//                }
                if (dataSnapshot.getKey().toString().equals("mName")){
                    mName=dataSnapshot.getValue().toString();
                    Log.d("mName",mName);
                }
                if (dataSnapshot.getKey().toString().equals("mPassword")){
                    mPassword=dataSnapshot.getValue().toString();
                    Log.d("mPassword",mPassword);
                }
                if (dataSnapshot.getKey().toString().equals("mPhone")){
                    mPhone=dataSnapshot.getValue().toString();
                    Log.d("mPhone",mPhone);
                }
                if (dataSnapshot.getKey().toString().equals("mSex")){
                    mSex=dataSnapshot.getValue().toString();
                    Log.d("mSex",mSex);
                }
                if (mName != null && mPassword != null && mSex !=null && mPhone !=null){
                    User user = new User(mPhone,mName,mPassword,mSex,avata);
                    listfrend.add(user);
                    mName = null;mPassword= null;mPhone= null;mSex=null;
                    Log.d("111111111","11111111111");
                    handleAdapTerphone();
                }



            }
            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void handleAdapTerphone() {
        Log.d("phoneUser",phoneUser);
        adapterSMS = new AdapterSMS(this,listfrend,phoneUser);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterSMS);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_frament_sms,null);
        recyclerView   = view.findViewById(R.id.rc_item_sms);
        return view;
    }

}
