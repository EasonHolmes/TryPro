package com.cui.trypro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends BaseActivity {


    @InjectView(R.id.main_list)
    ListView mainList;
    private String[] animation = {"ListAnimation"};
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);
        mContext = this;
        super.initToolbar(mContext.getResources().getString(R.string.app_name), false);


        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, animation);
        mainList.setAdapter(adapter);

        mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        startActivity(new Intent(mContext, OpenActivity.class));
                        overridePendingTransition(R.anim.in_translate_top, R.anim.in_translate_top);
                        break;
                }
            }
        });
    }
}
