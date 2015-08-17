package com.cui.trypro;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cui.trypro.adapter.InstaMaterial_adapter;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/8/16.
 */
public class InstaMateriaL_Activity extends BaseActivity {


    @InjectView(R.id.rvFeed)
    RecyclerView mRecyclerView;
    @InjectView(R.id.appBarLayout)
    AppBarLayout appBarLayout;
    @InjectView(R.id.btnCreate)
    FloatingActionButton btnCreate;
    @InjectView(R.id.content)
    CoordinatorLayout content;

    private Context mContext;
    private InstaMaterial_adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instamaterial);
        ButterKnife.inject(this);
        mContext = this;


        mRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new InstaMaterial_adapter(mContext);
        mRecyclerView.setAdapter(adapter);


        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mRecyclerView, "content", Snackbar.LENGTH_LONG)
                        .setAction("right", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                            }
                        })
//                .setActionTextColor(R.color.green)
//                .setDuration(3000)
                        .show(); // Don’t forget to show!
            }
        });

    }
}
