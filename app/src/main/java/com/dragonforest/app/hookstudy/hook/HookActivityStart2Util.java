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
 * hook activity启动2
 * hook Application启动activity
 *
 * @author 韩龙林
 * @date 2019/9/12 17:20
 */
public class HookActivityStart2Util {
    public static void hook(Activity activity) {
        try {
            // 1.反射得到ActivityThread
            Class<?> ActivityThreadCls = Class.forName("android.app.ActivityThread");
            // 2.获取静态变量实例 sCurrentActivityThread
            Field sCurrentActivityThreadField = ActivityThreadCls.getDeclaredField("sCurrentActivityThread");
            sCurrentActivityThreadField.setAccessible(true);
            Object sCurrentActivityThreadInstance = sCurrentActivityThreadField.get(null);

            // 3.获取mInstrumentation实例
            Field mInstrumentationField = ActivityThreadCls.getDeclaredField("mInstrumentation");
            mInstrumentationField.setAccessible(true);
            Instrumentation mInstrumetationInstance = (Instrumentation) mInstrumentationField.get(sCurrentActivityThreadInstance);

            // 4.创建代理类Instrumetation
            MyProxyInstrumentation myProxyInstumentation = new MyProxyInstrumentation(mInstrumetationInstance);

            // 5.设置成我们自己的代理类
            mInstrumentationField.set(sCurrentActivityThreadInstance, myProxyInstumentation);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class MyProxyInstrumentation extends Instrumentation {

        Instrumentation mInstrumentaion;

        public MyProxyInstrumentation(Instrumentation instrumentation) {
            mInstrumentaion = instrumentation;
        }

        public ActivityResult execStartActivity(
                Context who, IBinder contextThread, IBinder token, Activity target,
                Intent intent, int requestCode, Bundle options) {

            // hook内容
            Log.e("hook", "哈哈，hook到startActivity的点了！！！");
            try {
                // execStartActivity方法是隐藏的，所以只能反射调用
                Class<?> InstrumentationCls = Class.forName("android.app.Instrumentation");
                Method execStartActivityMethod = InstrumentationCls.getDeclaredMethod("execStartActivity",
                        Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, int.class, Bundle.class);
                execStartActivityMethod.setAccessible(true);
                return (ActivityResult) execStartActivityMethod.invoke(mInstrumentaion, who, contextThread, token, target, intent, requestCode, options);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
