package com.example.whatsap.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.whatsap.Login.User;
import com.example.whatsap.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.ArrayList;

import static com.example.whatsap.MainActivity.contactList;
import static com.example.whatsap.MainActivity.phoneUser;

public class fragment_frend  extends Fragment   {
    RecyclerView recyclerView;
    AdapterFrend adapterPhone;
    ArrayList<User> listphone = new ArrayList<>();
    ArrayList<User> listphone1 = new ArrayList<>();
    DatabaseReference mdata;
    String phone ;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handle();
    }

    private void handle() {
            listphone = contactList;
            mdata = FirebaseDatabase.getInstance().getReference();
            mdata.child("user").addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                  // Log.d("bbbbbbbbbbbb",dataSnapshot.toString());
                    User user = dataSnapshot.getValue(User.class);
                    String b = user.getmPhone();
                    for (int i = 0 ; i < listphone.size(); i++){
                        String a = listphone.get(i).getmPhone();
                        if (a.equals(b)){
                            listphone1.add(user);
                            handleListPhone();
                            break;
                        }
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

    private void handleListPhone() {
        phone = phoneUser;


        adapterPhone = new AdapterFrend(this,listphone1,phone);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterPhone);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_frend, null);
        recyclerView   = view.findViewById(R.id.rc_list_frend);
        return view;
    }



}
