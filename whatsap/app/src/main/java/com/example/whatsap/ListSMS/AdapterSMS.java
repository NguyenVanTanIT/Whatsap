package com.example.whatsap.ListSMS;
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
import com.example.whatsap.fragment.fragment_sms;

import java.util.ArrayList;
import java.util.List;
public class AdapterSMS extends RecyclerView.Adapter<AdapterSMS.ViewHolder> {
    List<User> listsms;
    Fragment fragment;
    String phoneUser;

    public AdapterSMS(fragment_sms fragment_frend, ArrayList<User> listsms, String phoneUser) {
        this.fragment = fragment_frend;
        this.listsms = listsms;
        this.phoneUser = phoneUser;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_frend,viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        User user = listsms.get(i);
        viewHolder.tvNameItem.setText(user.getmName());
        viewHolder.phone = user.getmPhone();
      //  viewHolder.imvAvataitemPhone.setImageBitmap(user.getAvata());
    }

    @Override
    public int getItemCount() {
        return listsms.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        ImageView imvAvataitemPhone;
        TextView tvNameItem,tvContenItem;
        String phone ;
        public ViewHolder(@NonNull final View itemView)  {
            super(itemView);
            imvAvataitemPhone = itemView.findViewById(R.id.imv_avata_item);
            tvNameItem = itemView.findViewById(R.id.tv_item_name_phone);
            tvContenItem = itemView.findViewById(R.id.tv_item_content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("phoneuser",tvNameItem.getText().toString());
                    Intent intent = new Intent(itemView.getContext(), ChatActivity.class);
                    intent.putExtra("phone",phone);
                    intent.putExtra("phoneUser",phoneUser);
                    fragment.startActivity(intent);
                }
            });
        }

    }
}
