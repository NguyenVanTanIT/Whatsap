package com.example.whatsap.fragment;

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
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.whatsap.ListSMS.AdapterSMS;
import com.example.whatsap.Login.User;
import com.example.whatsap.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class fragment_sms extends Fragment   {
    RecyclerView recyclerView;
    AdapterSMS adapterSMS;
    ArrayList<String> listfrend = new ArrayList<>();
    DatabaseReference mdata;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addcontrol();
    }

    private void addcontrol() {
        final String data = "a";
        mdata = FirebaseDatabase.getInstance().getReference("phone");
        mdata.child("0337575739").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String data = dataSnapshot.getValue().toString();
                listfrend.add(data);
                lisitemfrend();
                Log.d("aaaaaaaaaaa",data);
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
        adapterSMS = new AdapterSMS(this,listfrend);
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
