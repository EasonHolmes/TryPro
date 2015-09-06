package com.cui.trypro.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cui.trypro.R;

import java.util.List;

/**
 * Created by cuiyang on 15/8/28.
 */
public class MyBaseAdapter extends BaseAdapter {
    private Context mContext;
    private List<String> list;

    public MyBaseAdapter(Context mContext, List<String> list) {
        this.list = list;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_animation_group, null);
            holder = new ViewHolder();
            convertView.setTag(holder);
            holder.txt = (TextView) convertView.findViewById(R.id.tvComment);
        }
        holder.txt.setText(list.get(position));
        return convertView;
    }

    static class ViewHolder {
        public TextView txt;
    }
}