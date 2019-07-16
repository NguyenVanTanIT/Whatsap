package com.example.whatsap.fragment;

import android.support.annotation.NonNull;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.whatsap.Login.User;
import com.example.whatsap.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterPhone extends RecyclerView.Adapter<AdapterPhone.ViewHolder> {
    List<User> listphone;
    Fragment fragment;

    public AdapterPhone(fragment_frend fragment_frend, ArrayList<User> listphonr) {
        this.fragment = fragment_frend;
        this.listphone = listphonr;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_item_phone,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        User user = listphone.get(i);
        viewHolder.tvNameItem.setText(user.getmName());
        //viewHolder.imvAvataitemPhone.setImageBitmap(user.getAvata());
    }

    @Override
    public int getItemCount() {
        return listphone.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvAvataitemPhone;
        TextView tvNameItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvAvataitemPhone = itemView.findViewById(R.id.imv_avat_item_phone);
            tvNameItem = itemView.findViewById(R.id.tv_item_phone);
        }
    }
}
