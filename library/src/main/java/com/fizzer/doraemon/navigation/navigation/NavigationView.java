package com.fizzer.doraemon.navigation.navigation;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Fizzer on 2017/2/23.
 * Email: doraemonmqq@sina.com
 */

public class NavigationView extends RelativeLayout {
    private ListView mListView;
    private WordsNavigation mWordsNavigation;

    private List<NavigationBean> mList;

    private Context mContext;

    public NavigationView(Context context) {
        super(context);
        mContext = context;

        initView();
    }

    public NavigationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;

        initView();
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initView();
    }

    //初始化布局
    private void initView() {
        mListView = new ListView(mContext);
        LayoutParams listLp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
        this.addView(mListView, listLp);

        mWordsNavigation = new WordsNavigation(mContext);
        LayoutParams navLp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        navLp.addRule(ALIGN_PARENT_RIGHT);
        navLp.addRule(CENTER_VERTICAL);
        this.addView(mWordsNavigation, navLp);

        initListener();
    }

    private void initListener() {
        mWordsNavigation.setWordsChangeListener(new WordsNavigation.WordsChangeListener() {
            @Override
            public void wordsChangeListener(String word) {
                updateListView(word);
            }
        });

        mListView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                if (mList != null) {
                    mWordsNavigation.updateIndex(mList.get(firstVisibleItem).getHeadChar());
                }
            }
        });
    }

    /**
     * 设置数据源
     *
     * @param list
     */
    public void setData(List<String> list) {
        mList = new ArrayList<>();
        for (String str : list) {
            mList.add(new NavigationBean(str));
        }

        Collections.sort(mList, new Comparator<NavigationBean>() {
            @Override
            public int compare(NavigationBean o1, NavigationBean o2) {
                return o1.getPinyin().compareTo(o2.getPinyin());
            }
        });
        mWordsNavigation.setData(mList);

        mListView.setAdapter(new IndesAdapter(mContext, mList));

    }

    //更新ListView列表
    private void updateListView(String word) {
        for (int index = 0; index < mList.size(); index++) {
            if (word.equals(mList.get(index).getHeadChar())) {
                mListView.setSelection(index);
                return;
            }
        }
    }

}
