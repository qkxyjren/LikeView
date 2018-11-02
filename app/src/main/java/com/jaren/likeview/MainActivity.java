package com.jaren.likeview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import com.jaren.lib.view.LikeView;
import java.util.Random;


public class MainActivity extends AppCompatActivity implements OnClickListener {

    private LikeView mLvTop;
    private LikeView mLvCenter;
    private LikeView mLvBottom;
    private LikeView mLvBottom2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLvTop = (LikeView) findViewById(R.id.lv_top);
        mLvCenter = (LikeView) findViewById(R.id.lv_center);
        mLvBottom = (LikeView) findViewById(R.id.lv_bottom);
        mLvBottom2 = (LikeView) findViewById(R.id.lv_bottom2);
        mLvTop.setOnClickListener(this);
        mLvCenter.setOnClickListener(this);
        mLvBottom.setOnClickListener(this);
        mLvBottom2.setOnClickListener(this);
        mLvTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLvTop.toggle();
                requestPraise(new Callback() {
                    @Override
                    public void onSuccess() {
                        Log.i("LikeView",
                            "onClick onSuccess mLvTop.isChecked():" + mLvTop.isChecked());
                    }
                    @Override
                    public void onFail() {
                        mLvTop.toggleWithoutAnimator();
//                        or
//                        if (mLvTop.isChecked()) {
//                            mLvTop.setChecked(false);
//                        } else {
//                            mLvTop.setCheckedWithoutAnimator(true);
//                        }
                        Log.i("LikeView", "onClick onFail  mLvTop.isChecked():" + mLvTop.isChecked());
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lv_center:
                mLvCenter.toggleWithoutAnimator();
                break;
            case R.id.lv_bottom:
                mLvBottom.toggle();
                break;  case R.id.lv_bottom2:
                mLvBottom2.toggle();
                break;
        }
    }

    private void requestPraise(final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Random random = new Random();
                            if (random.nextInt() % 2 == 0) {
                                callback.onSuccess();
                            } else {
                                callback.onFail();
                            }
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    interface Callback {

        void onSuccess();

        void onFail();
    }
}
