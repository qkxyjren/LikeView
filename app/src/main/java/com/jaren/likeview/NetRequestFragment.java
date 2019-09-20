package com.jaren.likeview;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import com.jaren.lib.view.LikeView;
import java.util.Random;

/**
 * @date: 2018/11/6
 * @author: LiRJ
 */
public class NetRequestFragment extends BaseFragment implements OnClickListener {

    private LikeView mLikeView;
    private LikeView mLikeViewSet;



    @Override
    protected void initEvent(Bundle savedInstanceState) {
        mLikeView = mRootView.findViewById(R.id.lv);
        mLikeViewSet = mRootView.findViewById(R.id.lv_set);
        mLikeView.setOnClickListener(this);
        mLikeViewSet.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_net_request;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
           case  R.id.lv:
               useToggle();
            break;
            case  R.id.lv_set:
               useSet();
            break;
        }

    }



    private void useToggle() {
        mLikeView.toggle();
        requestPraise(new Callback() {
            @Override
            public void onSuccess() {
                Toast.makeText(getContext(),"toggle Success ",Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFail() {
                mLikeView.toggleWithoutAnimator();
                Toast.makeText(getContext(),"toggle Fail ",Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void useSet() {
        requestPraise(new Callback() {
            @Override
            public void onSuccess() {
                Toast.makeText(getContext(),"toggle Success ",Toast.LENGTH_SHORT).show();
                if (mLikeViewSet.isChecked()) {
                    mLikeViewSet.setChecked(false);
//                  mLikeViewSet.setCheckedWithoutAnimator(false);
                } else {
                    mLikeViewSet.setChecked(true);
                }
            }
            @Override
            public void onFail() {
                Toast.makeText(getContext(),"toggle Fail ",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void requestPraise(final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(400);
                    getActivity().runOnUiThread(new Runnable() {
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
