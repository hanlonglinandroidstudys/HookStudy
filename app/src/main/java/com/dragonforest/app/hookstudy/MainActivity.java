package com.dragonforest.app.hookstudy;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_hook_demo:
                go(HookDemoActivity.class);
                break;
            case R.id.btn_hook_activity_start1:
                go(HookActivityStart1Activity.class);
                break;
            case R.id.btn_hook_activity_start2:
                break;
        }
    }

    private void go(Class cls) {
        startActivity(new Intent(MainActivity.this, cls));
    }
}
