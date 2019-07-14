package com.example.whatsap.fragment;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class Contact_Adapter_frend<calss> extends BaseAdapter {

    private List<Contact_frend> mListContactFrend;
    private LayoutInflater mInFlaster;
    private Context mcontext;

    public Contact_Adapter_frend(Context context, List<Contact_frend> mListContactFrend ){
        this.mListContactFrend = mListContactFrend;
        this.mInFlaster = LayoutInflater.from(context);
        this.mcontext = context;
    }


    @Override
    public int getCount() {
        return mListContactFrend.size();
    }

    @Override
    public Object getItem(int position) {
        return mListContactFrend.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        RecyclerView.ViewHolder viewHolder;


        return null;
    }
}
