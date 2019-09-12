package com.dragonforest.app.hookstudy;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dragonforest.app.hookstudy.hook.HookClickListenerUtil;

/**
 * hook 小样例
 * 点击事件hook
 */
public class HookDemoActivity extends AppCompatActivity {
    private Button btn_click_me;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook_demo);
        initView();
        HookClickListenerUtil.hook(this,btn_click_me);
    }

    private void initView() {
        btn_click_me = findViewById(R.id.btn_click_me);
        btn_click_me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(HookDemoActivity.this, "不要点击我！！！", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
