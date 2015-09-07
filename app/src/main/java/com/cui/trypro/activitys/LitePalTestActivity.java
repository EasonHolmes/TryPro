package com.cui.trypro.activitys;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;
import com.cui.trypro.bean.LitePalBean;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;
import org.litepal.util.DBUtility;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/9/7.
 * <p/>
 * 详见有道云笔记 litePal
 */
public class LitePalTestActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = getClass().getName();

    /**
     * validationEditText.isValid("\\d+");
     * 带有报错字符串的正则表达式检查:
     * validationEditText.validate("\\d+", "Only Integer Valid!");
     */
    @InjectView(R.id.edit_update_content)
    MaterialEditText editUpdateContent;
    @InjectView(R.id.btn_add)
    Button btnAdd;
    @InjectView(R.id.btn_del)
    Button btnDel;
    @InjectView(R.id.btn_upate)
    Button btnUpate;
    @InjectView(R.id.btn_select)
    Button btnSelect;
    @InjectView(R.id.txt_preview)
    TextView txtPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.litepal_act);
        ButterKnife.inject(this);

        initView();
    }

    private void initView() {
        btnAdd.setOnClickListener(this);
        btnUpate.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnSelect.setOnClickListener(this);
    }

    private void add(String name) {
        LitePalBean bean = new LitePalBean();
        bean.setCity("西安");
        bean.setName(name);
        bean.save();
    }


    private void selectAll() {
        List<LitePalBean> allSongs = DataSupport.findAll(LitePalBean.class);
        if (allSongs.size() > 0) {
            StringBuffer sb = new StringBuffer();
            for (LitePalBean Beans : allSongs) {
                sb.append(Beans.toString());
            }
            txtPreview.setText(sb.toString());
        }
    }

    private void selectById(int id) {
        LitePalBean bean = DataSupport.find(LitePalBean.class, id);

    }

    private void updateByName(String Newname) {
        ContentValues values = new ContentValues();
        values.put("name", Newname);
        DataSupport.update(LitePalBean.class, values, 2);//比如把LitePalBean表中id为2的记录的标题修改
//        DataSupport.updateAll(LitePalBean.class, values, "name = ?", oldname);//前面是要改的值。后面是条件

    }

    private void selectByWhere(String name) {
        // List<Song> songs = DataSupport.where("name like ?", "song%").order("duration").find(Song.clas);
        List<LitePalBean> selAll = DataSupport.where("name like ?", name + "%").order("id desc").find(LitePalBean.class);
        if (selAll != null) {
            StringBuffer sb = new StringBuffer();
            for (LitePalBean Beans : selAll) {
                sb.append(Beans.toString());
            }
            txtPreview.setText(sb.toString());
        }
    }

    private void delByName(String name) {
//        DataSupport.delete(News.class,2);	// 删除news表中id为2的记录，news_id为2的记录为外键的数据也会删除，返回值为被输出的记录数
//        DataSupport.deleteAll(News.class,"title = ? and commentcount = ? ","title","0"); 	// 批量删除
//        DataSupport.deleteAll(News.class);	// 删除所有
        DataSupport.deleteAll(LitePalBean.class, "name = ?", name);
    }

    @Override
    public void onClick(View v) {
        String name = editUpdateContent.getText() + "";
        switch (v.getId()) {
            case R.id.btn_add:
                add(name);
                selectAll();
                break;
            case R.id.btn_upate:
                updateByName(name);
                selectByWhere(name);
                break;
            case R.id.btn_del:
                delByName(name);
                selectAll();
                break;
            case R.id.btn_select:
                selectAll();
                break;

        }
    }
}
