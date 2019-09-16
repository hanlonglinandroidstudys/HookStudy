package com.dragonforest.app.hookstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.dragonforest.app.hookstudy.hook.HookActivityStart2Util;

public class HookActivityStart2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hook_start2);
        HookActivityStart2Util.hook(this);
        initView();
    }

    private void initView() {
        Button btn_startAc=findViewById(R.id.btn_click_startAc);
        btn_startAc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(HookActivityStart2Activity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                getApplicationContext().startActivity(intent);
            }
        });
    }
}
