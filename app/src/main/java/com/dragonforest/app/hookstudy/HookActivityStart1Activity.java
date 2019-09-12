package com.dragonforest.app.hookstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dragonforest.app.hookstudy.hook.HookActivityStart1Util;

public class HookActivityStart1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook_activity1);
        HookActivityStart1Util.hook(this);
        initView();
    }

    private void initView() {
        Button btn_startAc=findViewById(R.id.btn_click_startAc);
        btn_startAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HookActivityStart1Activity.this,MainActivity.class));
            }
        });
    }
}
