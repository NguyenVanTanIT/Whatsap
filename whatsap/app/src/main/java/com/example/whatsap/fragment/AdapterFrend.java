package com.example.whatsap.fragment;

import android.content.Intent;
import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whatsap.ChatActivity;
import com.example.whatsap.Login.User;
import com.example.whatsap.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdapterFrend extends RecyclerView.Adapter<AdapterFrend.ViewHolder>  {
    List<User> listphone;
    Fragment fragment;
    String phoneUser  ;
    String chatId  ;
    DatabaseReference mDatabaseUser;

    public AdapterFrend(String phoneUser){
        this.phoneUser = phoneUser;
    }
    public AdapterFrend(fragment_frend fragment_frend, ArrayList<User> listphonr,String phoneUser) {
        this.fragment = fragment_frend;
        this.listphone = listphonr;
        this.phoneUser = phoneUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_sms,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        User user = listphone.get(i);
        viewHolder.tvNameItem.setText(user.getmName());
        viewHolder.phone = user.getmPhone();
        //viewHolder.imvAvataitemPhone.setImageBitmap(user.getAvata());

    }

    @Override
    public int getItemCount() {
        return listphone.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView imvAvataitemPhone;
        TextView tvNameItem;
        String phone ;
        String key;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            imvAvataitemPhone = itemView.findViewById(R.id.imv_avat_item_phone);
            tvNameItem = itemView.findViewById(R.id.tv_item_name_phone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("phoneuser",phone);
                    setPhoneTable();
                    Intent intent = new Intent(itemView.getContext(),ChatActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("phoneUser",phoneUser);
                    setIdChat();
                    //intent.putExtra("chatId",key);
                    fragment.startActivity(intent);
                }
            });
        }

        private void setPhoneTable() {
            DatabaseReference databaseReferencephoneUser= FirebaseDatabase.getInstance().getReference().child("phone");
            DatabaseReference databaseReferencephone= FirebaseDatabase.getInstance().getReference().child("phone");
            databaseReferencephone.child(phoneUser).setValue(phoneUser);
            databaseReferencephone.child(phoneUser).push().setValue(phone);
            databaseReferencephoneUser.child(phone).setValue(phone);
            databaseReferencephoneUser.child(phone).push().setValue(phoneUser);
        }

        private void setIdChat() {
//            key=phone+"x"+phoneUser;
//
//          mDatabaseUser = FirebaseDatabase.getInstance().getReference().child("user").child(phoneUser).child("chatId");
//
//            Map<String, Object> usermap = new HashMap<>();
//            usermap.put(key,key);
            //String key=FirebaseDatabase.getInstance().getReference().child("chat").push().getKey();

         //   String key;
            DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("phone");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.exists()){
                        String key=FirebaseDatabase.getInstance().getReference().child("chat").push().getKey();
                        FirebaseDatabase.getInstance().getReference().child("chat").child(key).setValue(key);
                        Log.d("117",key);
                        DatabaseReference databaseReferencephoneUser= FirebaseDatabase.getInstance().getReference().child("user").child(phoneUser).child("chatId");
                        DatabaseReference databaseReferencephone= FirebaseDatabase.getInstance().getReference().child("user").child(phone).child("chatId");
                        databaseReferencephone.setValue(key);
                        databaseReferencephoneUser.setValue(key);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });




        }

    }


}
