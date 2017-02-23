package com.fizzer.doraemon.navigation.navigation;


import com.github.promeg.pinyinhelper.Pinyin;

/**
 * Created by Fizzer on 2017/2/22.
 * Email: doraemonmqq@sina.com
 */

public class NavigationBean {

    public String name;
    public String pinyin;
    public String headChar;

    public NavigationBean(String name) {
        this.name = name;
        this.pinyin = Pinyin.toPinyin(name,"-");
        this.headChar = pinyin.substring(0,1);
    }

    public String getName() {
        return name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public String getHeadChar() {
        return headChar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public void setHeadChar(String headChar) {
        this.headChar = headChar;
    }
}
