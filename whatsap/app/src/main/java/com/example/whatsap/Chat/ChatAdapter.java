package com.example.whatsap.Chat;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.example.whatsap.R;
import static com.example.whatsap.MainActivity.phoneUser;

public class ChatAdapter extends RecyclerView.Adapter<ChatViewHolders>  {
    private List<ChatObject> chatList;
    private Context context;
    public static  String textt,a ="hlooooo";

    public ChatAdapter(List<ChatObject> ChatList, Context context){
        this.chatList=ChatList;
        this.context=context;
    }

    @Override
    public ChatViewHolders onCreateViewHolder(ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chat,null,false);
        RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutView.setLayoutParams(lp);
        ChatViewHolders rcv = new ChatViewHolders((layoutView));


        return rcv;
    }

    @Override
    public void onBindViewHolder(ChatViewHolders holder, int position) {
        holder.mMeassage.setText(chatList.get(position).getMessage());

        String a = chatList.get(position).getCurrentUser();
        String b = phoneUser;

        if (a.equals(b)){
            holder.mMeassage.setGravity(Gravity.END);
            holder.mMeassage.setTextColor(Color.parseColor("#FFFFFF"));
            holder.mContainer.setBackgroundColor(Color.parseColor("#2DB4C8"));
        }
        else
        {
            holder.mMeassage.setGravity(Gravity.START);
            holder.mMeassage.setTextColor(Color.parseColor("#FFFFFF"));
            holder.mContainer.setBackgroundColor(Color.parseColor("#FF0000"));
        }
        textt = chatList.get(position).getMessage();

    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }
}
