package com.cui.trypro.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cui.trypro.R;

import java.util.List;

/**
 * Created by cuiyang on 15/9/11.
 */
public class MySimpleRecycler_Adapter extends RecyclerView.Adapter<MySimpleRecycler_Adapter.Viewholder> {

    List<String> list ;

    public MySimpleRecycler_Adapter(List<String> list){
        this.list =list;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_animation_group, parent, false);

        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        holder.txt_contnet.setText(list.get(position));
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class Viewholder extends RecyclerView.ViewHolder {
        public TextView txt_contnet;

        public Viewholder(View itemView) {
            super(itemView);
            txt_contnet = (TextView) itemView.findViewById(R.id.tvComment);
        }
    }
}