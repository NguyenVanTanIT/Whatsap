package com.example.whatsap.ListSMS;
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
import com.example.whatsap.fragment.fragment_frend;
import com.example.whatsap.fragment.fragment_sms;

import java.util.ArrayList;
import java.util.List;
public class AdapterSMS extends RecyclerView.Adapter<AdapterSMS.ViewHolder> {
    List<String> listsms;
    Fragment fragment;

    public AdapterSMS(fragment_sms fragment_frend, ArrayList<String> listsms) {
        this.fragment = fragment_frend;
        this.listsms = listsms;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_frend,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.tvNameItem.setText(listsms.get(i));
        //User user = listsms.get(i);
        //viewHolder.tvNameItem.setText(user.getmName());
        //viewHolder.imvAvataitemPhone.setImageBitmap(user.getAvata());
    }

    @Override
    public int getItemCount() {
        return listsms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imvAvataitemPhone;
        TextView tvNameItem,tvContenItem;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imvAvataitemPhone = itemView.findViewById(R.id.imv_avata_item);
            tvNameItem = itemView.findViewById(R.id.tv_item_name);
            tvContenItem = itemView.findViewById(R.id.tv_item_content);
        }
    }
}
