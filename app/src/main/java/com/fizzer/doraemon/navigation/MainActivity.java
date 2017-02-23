package com.fizzer.doraemon.navigation;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.fizzer.doraemon.navigation.navigation.NavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    private NavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = (NavigationView) findViewById(R.id.myView);

        initData();
    }

    private void initData() {
        List dateList = new ArrayList<>();
        dateList.add("周杰伦");
        dateList.add("周润发");
        dateList.add("刘德华");
        dateList.add("周杰");
        dateList.add("艾弗森");
        dateList.add("科比");
        dateList.add("成龙");
        dateList.add("李建杰");
        dateList.add("特朗普");
        dateList.add("阿汤哥");
        dateList.add("林依晨");
        dateList.add("陈柏霖");
        dateList.add("小综");
        dateList.add("林俊杰");
        dateList.add("陈道明");
        dateList.add("张一山");
        dateList.add("陈乔恩");
        dateList.add("李建");
        dateList.add("胡歌");
        dateList.add("霍建华");
        dateList.add("杨幂");
        dateList.add("冯绍峰");
        dateList.add("周华健");
        dateList.add("张磊");
        dateList.add("马倩倩");
        dateList.add("大司马");

        mNavigationView.setData(dateList);
    }
}
