package com.dragonforest.app.hookstudy.hook;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * hook点击事件
 *
 * @author 韩龙林
 * @date 2019/9/12 16:42
 */
public class HookClickListenerUtil {

    public static void hook(Context context, View view) {
        try {
            // 1.反射拿到mListenerInfo属性
            Method getListenerInfoMethod = View.class.getDeclaredMethod("getListenerInfo");
            getListenerInfoMethod.setAccessible(true);
            Object mListenerInfoInstance = getListenerInfoMethod.invoke(view);

            // 2.拿到当前的点击事件
            Class<?> listenerInfoCls = Class.forName("android.view.View$ListenerInfo"); //内部类的表示方法
            Field mOnClickListenerField = listenerInfoCls.getDeclaredField("mOnClickListener");
            mOnClickListenerField.setAccessible(true);
            View.OnClickListener onClickListenerInstance = (View.OnClickListener) mOnClickListenerField.get(mListenerInfoInstance);

            // 3.创建自己的代理类
            MyProxyOnClickListener myOnClickListener = new MyProxyOnClickListener(onClickListenerInstance,context);

            // 4.替换成自己的代理类,ok
            mOnClickListenerField.set(mListenerInfoInstance, myOnClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyProxyOnClickListener implements View.OnClickListener {

        View.OnClickListener mOnClickListener;
        Context context;

        public MyProxyOnClickListener(View.OnClickListener onClickListener,Context context) {
            this.mOnClickListener = onClickListener;
            this.context=context;
        }

        @Override
        public void onClick(View v) {

            Toast.makeText(context,  "哈哈哈哈，被我hook了吧！！", Toast.LENGTH_SHORT).show();

            if (mOnClickListener != null) {
                mOnClickListener.onClick(v);
            }
        }
    }
}
