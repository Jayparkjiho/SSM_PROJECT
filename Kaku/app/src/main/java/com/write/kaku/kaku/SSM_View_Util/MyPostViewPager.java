package com.write.kaku.kaku.SSM_View_Util;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class MyPostViewPager extends ViewPager {

    public MyPostViewPager(Context context) {
        super(context);
    }

    public MyPostViewPager(Context context, AttributeSet attrs){
        super(context, attrs);
    }

    //viewpager 슬라이딩 막기
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
//        return super.onTouchEvent(ev);
        return false;
    }
}
