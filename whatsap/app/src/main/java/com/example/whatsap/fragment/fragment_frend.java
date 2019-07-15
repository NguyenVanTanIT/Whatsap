package com.example.whatsap.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.whatsap.Login.User;

import com.example.whatsap.MainActivity;
import com.example.whatsap.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class fragment_frend  extends Fragment   {
    TextView tvNameItem, tvItemContent;
    ImageView imvItemAvata;
    ListView lvItem;
    final ArrayList<User> contactList = new ArrayList<>();
    ArrayList<String> listphone=new ArrayList<>();
    final ArrayList<User> myDataset = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handle();
    }

    private void handle() {
        DatabaseReference currentUserDb = FirebaseDatabase.getInstance().getReference().child("user");
        currentUserDb.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                User user = dataSnapshot.getValue(User.class);
                contactList.add(user);
                for (int i = 0; i <contactList.size(); i++){
//                    Log.d("aaaa", contactList.get(i).getmPassword());
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
//        for (User user:contactList){
//            Log.d("44",user.getmPhone());
//            Log.d("44",user.getmName());
//        }


    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_fragment_frend, null);
        tvNameItem = view.findViewById(R.id.tv_item_name);
        tvItemContent  = view.findViewById(R.id.tv_item_content);
        imvItemAvata   = view.findViewById(R.id.imv_avat_item);
        lvItem     = view.findViewById(R.id.lv_item);
        return view;
    }



}
