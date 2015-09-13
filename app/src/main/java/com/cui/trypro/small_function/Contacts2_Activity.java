package com.cui.trypro.small_function;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AlphabetIndexer;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.cui.trypro.BaseActivity;
import com.cui.trypro.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by cuiyang on 15/9/13.
 */
public class Contacts2_Activity extends BaseActivity {
    @InjectView(R.id.country_lvcountry)
    ListView sortListView;
    @InjectView(R.id.sidrbar)
    SideBar sideBar;
    @InjectView(R.id.dialog)
    TextView dialog;


    /**
     * 分组的布局
     */
    private LinearLayout titleLayout;

    /**
     * 分组上显示的字母
     */
    private TextView title;


    /**
     * 联系人列表适配器
     */
    private ContactAdapter adapter;

    /**
     * 用于进行字母表分组
     */
    private AlphabetIndexer indexer;

    /**
     * 存储所有手机中的联系人
     */
    private List<SortModel> SourceDateList = new ArrayList<SortModel>();

    /**
     * 定义字母表的排序规则
     */
    private String alphabet = "#ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 上次第一个可见元素，用于滚动时记录标识。
     */
    private int lastFirstVisibleItem = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_act);
        ButterKnife.inject(this);
        super.initToolbar("", true);
        adapter = new ContactAdapter(this, R.layout.contact_item, SourceDateList);
        initView();
    }

    private void initView() {

        dialog = (TextView) findViewById(R.id.dialog);
        title = (TextView) findViewById(R.id.title);
        titleLayout = (LinearLayout) findViewById(R.id.title_layout);
        sideBar.setTextView(dialog);


        /**获取通讯录*/
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = getContentResolver().query(uri,
                new String[]{"display_name", "sort_key"}, null, null, "sort_key");
        if (cursor.moveToFirst()) {
            do {
                String name = cursor.getString(0);
                String sortKey = getSortKey(cursor.getString(1));
                SortModel contact = new SortModel();
                contact.setName(name);
                contact.setSortLetters(sortKey);
                SourceDateList.add(contact);
            } while (cursor.moveToNext());
        }
        startManagingCursor(cursor);

        indexer = new AlphabetIndexer(cursor, 1, alphabet);//，从系统联系人数据库中去查询联系人的姓名
        // 和排序键，之后将查询返回的cursor直接传入AlphabetIndexer作为第一个参数。由于我们一共就查了两列，排
        // 序键在第二列，所以我们第二个sortedColumnIndex参数传入1。第三个alphabet参数这里传入了"#ABCDEFGHIJKLMNOPQRSTUVWXYZ"字符
        sortListView.setAdapter(adapter);
        adapter.setIndexer(indexer);
        if (SourceDateList.size() > 0) {
            setupContactsListView();
        }
        sideBar.setOnTouchingLetterChangedListener(new SideBar.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                int position = getPositionForSection(s.charAt(0));
                if (position != -1) {
                    sortListView.setSelection(position);
                }
            }
        });

        sortListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getApplication(), ((SortModel) adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    /**
     * 为联系人ListView设置监听事件，根据当前的滑动状态来改变分组的显示位置，从而实现挤压动画的效果。
     */
    private void setupContactsListView() {
        sortListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount,
                                 int totalItemCount) {
                int section = indexer.getSectionForPosition(firstVisibleItem);
                int nextSecPosition = indexer.getPositionForSection(section + 1);
                if (firstVisibleItem != lastFirstVisibleItem) {
                    ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout.getLayoutParams();
                    params.topMargin = 0;
                    titleLayout.setLayoutParams(params);
                    title.setText(String.valueOf(alphabet.charAt(section)));
                }
                if (nextSecPosition == firstVisibleItem + 1) {
                    View childView = view.getChildAt(0);
                    if (childView != null) {//，在onScroll方法中通过getSectionForPosition方法获取第一
                        // 个可见元素的分组值，然后给该分组值加1，再通过getPositionForSection方法或者到下一个
                        // 分组中的第一个元素，如果下个分组的第一个元素值等于第一个可见元素的值加1，那就说明下个分
                        // 组的布局要和界面顶部分组布局相碰了。之后再通过ListView的getChildAt(0)方法，获取到界
                        // 面上显示的第一个子View，再用view.getBottom获取底部距离父窗口的位置，对比分组布局的高度来对顶部分组布局进行纵向偏移，就可以实现挤压动画的效果了。
                        int titleHeight = titleLayout.getHeight();
                        int bottom = childView.getBottom();
                        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) titleLayout
                                .getLayoutParams();
                        if (bottom < titleHeight) {
                            float pushedDistance = bottom - titleHeight;
                            params.topMargin = (int) pushedDistance;
                            titleLayout.setLayoutParams(params);
                        } else {
                            if (params.topMargin != 0) {
                                params.topMargin = 0;
                                titleLayout.setLayoutParams(params);
                            }
                        }
                    }
                }
                lastFirstVisibleItem = firstVisibleItem;
            }
        });

    }

    /**
     * 获取sort key的首个字符，如果是英文字母就直接返回，否则返回#。
     *
     * @param sortKeyString 数据库中读取出的sort key
     * @return 英文字母或者#
     */
    private String getSortKey(String sortKeyString) {
        String key = sortKeyString.substring(0, 1).toUpperCase();
        if (key.matches("[A-Z]")) {
            return key;
        }
        return "#";
    }
    /**
     * 根据分类的首字母的Char ascii值获取其第一次出现该首字母的位置
     */
    public int getPositionForSection(int section) {
        for (int i = 0; i < SourceDateList.size(); i++) {
            String sortStr = SourceDateList.get(i).getSortLetters();
            char firstChar = sortStr.toUpperCase().charAt(0);
            if (firstChar == section) {
                return i;
            }
        }
        return -1;
    }
}
