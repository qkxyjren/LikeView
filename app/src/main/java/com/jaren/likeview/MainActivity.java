package com.jaren.likeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import android.view.View.OnClickListener;
import com.jaren.lib.view.LikeView;


public class MainActivity extends AppCompatActivity implements OnClickListener {
    private LikeView mLvTop;
    private LikeView mLvCenter;
    private LikeView mLvBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLvTop = (LikeView) findViewById(R.id.lv_top);
        mLvCenter = (LikeView) findViewById(R.id.lv_center);
        mLvBottom = (LikeView) findViewById(R.id.lv_bottom);
        mLvTop.setOnClickListener(this);
        mLvCenter.setOnClickListener(this);
        mLvBottom.setOnClickListener(this);
//        mLvTop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lv_top:
                mLvTop.toggle();
                Log.i("LikeView", "onClick" + mLvTop.isChecked());
                break;
            case R.id.lv_center:
                mLvCenter.toggleWithoutAnimator();
                break;
            case R.id.lv_bottom:
                mLvBottom.toggle();
                break;
        }
    }
}
