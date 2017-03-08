package cn.a10086.www.viewpagerdemo;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * @author
 * @time 2017/3/7  16:14
 * @desc ${TODD}
 */
public class ZengViewPager extends ViewPager {
    public ZengViewPager(Context context) {
        super(context);
    }

    public ZengViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    //    本身不拦截和不消费点击事件
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
