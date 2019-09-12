package com.dragonforest.app.hookstudy.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * hook activity启动1
 *
 * @author 韩龙林
 * @date 2019/9/12 17:20
 */
public class HookActivityStart1Util {
    public static void hook(Activity activity) {
        try {
            // 1.获取mInstrumentation实例
            Field mInstrumentationField = Activity.class.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);
            Instrumentation mInstrumentationInstance = (Instrumentation) mInstrumentationField.get(activity);

            // 2.创建自己的Instrumentation
            MyProxyInstumentation myProxyInstumentation=new MyProxyInstumentation(mInstrumentationInstance);

            // 3.设置成自己的Instrumentation,ok
            mInstrumentationField.set(activity,myProxyInstumentation);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyProxyInstumentation extends Instrumentation {

        Instrumentation mInstrumentaion;

        public MyProxyInstumentation(Instrumentation instrumentation) {
            mInstrumentaion = instrumentation;
        }

        public ActivityResult execStartActivity(
                Context who, IBinder contextThread, IBinder token, Activity target,
                Intent intent, int requestCode, Bundle options) {

            // hook内容
            Log.e("hook","哈哈，hook到startActivity的点了！！！");
            try {
                // execStartActivity方法是隐藏的，所以只能反射调用
                Class<?> InstrumentationCls = Class.forName("android.app.Instrumentation");
                Method execStartActivityMethod = InstrumentationCls.getDeclaredMethod("execStartActivity",
                        Context.class,IBinder.class,Activity.class,Intent.class,int.class,Bundle.class);
                execStartActivityMethod.setAccessible(true);
                return (ActivityResult) execStartActivityMethod.invoke(mInstrumentaion, who, contextThread, token, target, intent, requestCode, options);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
