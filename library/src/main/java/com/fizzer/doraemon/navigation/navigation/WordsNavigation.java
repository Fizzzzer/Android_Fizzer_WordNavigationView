package com.fizzer.doraemon.navigation.navigation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by Fizzer on 2017/2/22.
 * Email: doraemonmqq@sina.com
 */

public class WordsNavigation extends View {
    public WordsNavigation(Context context) {
        super(context);
        init();
    }

    public WordsNavigation(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WordsNavigation(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    //需要索引的字母
    private String words[] = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
            "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "#"};

    //字母的宽度
    private int itemWidth;
    //字母的高度
    private int itemHeight;
    //文字的画笔
    private Paint wordPaint;
    //文字的颜色
    private int wordColor = Color.parseColor("#FF0000");
    //文字大小
    private int wordSize = 55;
    //选中文字的背景画笔
    private Paint bgPaint;
    //选中的文字的背景颜色
    private int bgColor = Color.parseColor("#0000FF");
    //选中时文字的颜色
    private int wordSelectColor = Color.parseColor("#FFFFFF");

    //手指按下的字母索引
    private int touchIndex;

    private List<String> wordList = Arrays.asList(words);

    /**
     * 文字变化的监听
     */
    private WordsChangeListener mListener;

    /**
     * 初始化数据
     */
    private void init() {
        wordPaint = new Paint();
        wordPaint.setAntiAlias(true);
        wordPaint.setColor(wordColor);
        wordPaint.setTextSize(wordSize);
        wordPaint.setTextAlign(Paint.Align.CENTER);

        bgPaint = new Paint();
        bgPaint.setAntiAlias(true);
        bgPaint.setColor(bgColor);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        itemWidth = MeasureSpec.getSize(widthMeasureSpec);
        //使得边距好看一些
        int height = MeasureSpec.getSize(heightMeasureSpec) - 10;
        itemHeight = height / 27;

        int width = measureWidth(widthMeasureSpec);
        int height1 = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height1);
    }

    //测量宽度
    private int measureWidth(int widthMeasureSpec) {

        int measureMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureSize = MeasureSpec.getSize(widthMeasureSpec);
        int size;

        if (measureMode == MeasureSpec.EXACTLY) {
            size = measureSize;
        } else {
            size = 80;
        }
        return size;
    }

    //测量高度
    private int measureHeight(int heightMeasureSpec) {

        int measureMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureSize = MeasureSpec.getSize(heightMeasureSpec);
        int size;

        if (measureMode == MeasureSpec.EXACTLY) {
            size = measureSize;
        } else {
            size = itemHeight * wordList.size();
        }
        return size;
    }

    /**
     * 如果不调用setData方法，这里的deaw会绘制全部的26个英文字母
     * 如果调用了，则只会绘制有包含的字母
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int index = 0; index < wordList.size(); index++) {
            Paint.FontMetrics metrics = wordPaint.getFontMetrics();
            wordPaint.setColor(wordColor);
            if (touchIndex == index) {
                canvas.drawCircle(itemWidth / 2, itemWidth / 2 + index * itemHeight + metrics.top / 2 + 30, (itemHeight - 20) / 2, bgPaint);
                wordPaint.setColor(wordSelectColor);
            }
            //绘制字母
            canvas.drawText(wordList.get(index), itemWidth / 2, itemWidth / 2 + index * itemHeight + 20, wordPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            //手指按下和移动做相同的操作
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                int index = (int) (event.getY() / itemHeight);
                if (index != touchIndex) {
                    touchIndex = index;
                    invalidate();

                    if (mListener != null && touchIndex >= 0 && touchIndex < wordList.size()) {
                        mListener.wordsChangeListener(wordList.get(touchIndex));
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //手指抬起不做任何操作
                break;
        }
        return true;
    }

    /**
     * 更新索引的列表
     *
     * @param c
     */
    public void updateIndex(String c) {
        if (TextUtils.isEmpty(c)) {
            return;
        }
        for (int index = 0; index < wordList.size(); index++) {
            if (c.equals(wordList.get(index))) {
                if (touchIndex != index) {
                    touchIndex = index;
                    invalidate();
                    return;
                }
            }
        }
    }

    /**
     * 设置数据源
     *
     * @param list
     */
    public void setData(List<NavigationBean> list) {
        if (list == null) {
            return;
        }
        wordList = new ArrayList<>();
        for (NavigationBean bean : list) {
            if (!wordList.contains(bean.getHeadChar())) {
                wordList.add(bean.getHeadChar());
            }
        }

        Collections.sort(wordList);
        requestLayout();
        invalidate();
    }

    public void setWordsChangeListener(WordsChangeListener listener) {
        this.mListener = listener;
    }

    /**
     * 文字索引改变的接口
     */
    public interface WordsChangeListener {
        void wordsChangeListener(String word);
    }
}
