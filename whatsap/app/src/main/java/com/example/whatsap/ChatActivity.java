package com.example.whatsap;

import android.content.Intent;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.whatsap.Chat.ChatAdapter;
import com.example.whatsap.Chat.ChatObject;


import static com.example.whatsap.MainActivity.phoneUser;

public class ChatActivity extends AppCompatActivity {
    //private String matchId;
    private  String currentphone;
    DatabaseReference mDatabaseFrendChat,mDatabaseChat;


    private ArrayList<ChatObject> resultChat= new ArrayList<ChatObject>();
    private RecyclerView.Adapter mChatAdapter;

    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mChatLayoutManager;
    private List<ChatObject> getDataSetMatches() {
        return resultChat;
    }

    private EditText mSendEditText;
    private ImageButton mSendButton;
    String KeyChat ,phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        Intent intent = this.getIntent();
        KeyChat = intent.getStringExtra("keyChat");
        phone   = intent.getStringExtra("phone");


        currentphone = phoneUser;

        getFireBase(); ///ham nay sai
        getChatMessages();
        getAdaper();
        setButton();

    }
    private void setButton() {
        mSendEditText=findViewById(R.id.message);
        mSendButton=findViewById(R.id.send);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendMessage();
            }
        });
    }

    private void sendMessage() {

        final String sendMessageText = mSendEditText.getText().toString();

        if (!sendMessageText.isEmpty()){
            DatabaseReference databaseReferencephoneU= FirebaseDatabase.getInstance().getReference().child("phone");
            databaseReferencephoneU.child(phoneUser).child(phone).setValue(phone);

            DatabaseReference databaseReferencephone= FirebaseDatabase.getInstance().getReference().child("phone");
            databaseReferencephone.child(phone).child(phoneUser).setValue(phoneUser);

            DatabaseReference dtUseer= FirebaseDatabase.getInstance().getReference().child("user");
            dtUseer.child(phone).child("text").setValue(sendMessageText);

            DatabaseReference dtphonr= FirebaseDatabase.getInstance().getReference().child("user");
            dtphonr.child(phoneUser).child("text").setValue(sendMessageText);

                DatabaseReference newMessageDb = mDatabaseChat.child(KeyChat).push();

                Map newMessage = new HashMap();
                newMessage.put("createdByUser",phoneUser);
                newMessage.put("createdPhone",phone);
                newMessage.put("text",sendMessageText);
                newMessageDb.setValue(newMessage);
        }
        mSendEditText.setText(null);
    }


    private void getAdaper() {
        mRecyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(false);

        mChatLayoutManager = new LinearLayoutManager(ChatActivity.this);
        mRecyclerView.setLayoutManager(mChatLayoutManager);
        mChatAdapter = new ChatAdapter(getDataSetMatches(),ChatActivity.this);
        mRecyclerView.setAdapter(mChatAdapter);
    }

    private void getChatMessages() {
        Log.d("KeyChat",KeyChat);

        mDatabaseChat.child(KeyChat).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                if (dataSnapshot.exists()){
                    Log.d("dataSnapshot", String.valueOf(dataSnapshot.getValue()));
                    String message = null;
                    String createdByUser = null;
                    String createdByphone = null;

                    if (dataSnapshot.child("text").getValue()!=null){
                        message=dataSnapshot.child("text").getValue().toString();

                    }
                    if (dataSnapshot.child("createdByUser").getValue()!=null){
                        createdByUser=dataSnapshot.child("createdByUser").getValue().toString();

                    }
                    if (dataSnapshot.child("createdPhone").getValue() != null){
                        createdByphone = dataSnapshot.child("createdPhone").getValue().toString();
                    }

                    if (message!=null && createdByUser!=null && createdByphone != null){

                        ChatObject newMessage = new ChatObject(message,createdByUser,createdByphone);
                        resultChat.add(newMessage);
                        mChatAdapter.notifyDataSetChanged();

                        Log.d("148","148");
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

    private void getFireBase() {

        mDatabaseFrendChat = FirebaseDatabase.getInstance().getReference().child("chat").child(phoneUser);
        mDatabaseChat = FirebaseDatabase.getInstance().getReference().child("chat");
    }
}

