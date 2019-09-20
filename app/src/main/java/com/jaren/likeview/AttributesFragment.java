package com.jaren.likeview;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import com.jaren.lib.view.LikeView;
import com.jaren.lib.view.LikeViewBuilder;

/**
 * @date: 2018/11/6
 * @author: LiRJ
 */
public class AttributesFragment extends BaseFragment implements OnClickListener {

    private LikeView mLikeView;
    private TextView mTvMsg;
    private RelativeLayout rlParent;
    private TextSeekBar mBacTSeekBar;
    private TextSeekBar mLrcTSeekBar;
    private TextSeekBar mLrbTSeekBar;
    private TextSeekBar mTbTSeekBar;
    private TextView mTvRestore;



    @Override
    protected void initEvent(Bundle savedInstanceState) {
        rlParent = mRootView.findViewById(R.id.rl_parent);
        mLikeView = mRootView.findViewById(R.id.lv);
        mBacTSeekBar = mRootView.findViewById(R.id.bac_s_bar);
        mLrcTSeekBar = mRootView.findViewById(R.id.lrc_s_bar);
        mLrbTSeekBar = mRootView.findViewById(R.id.lrb_s_bar);
        mTbTSeekBar = mRootView.findViewById(R.id.tb_s_bar);
        mTvMsg = mRootView.findViewById(R.id.tv_msg);
        mTvRestore = mRootView.findViewById(R.id.tv_restore);

        mLikeView.setOnClickListener(this);
        mTvRestore.setOnClickListener(this);
        initTSeekBar("bGroupACRatio", mBacTSeekBar);
        initTSeekBar("lrGroupCRatio", mLrcTSeekBar);
        initTSeekBar("lrGroupBRatio", mLrbTSeekBar);
        initTSeekBar("tGroupBRatio", mTbTSeekBar);
        initLvSetting();

        //useLikeViewBuilder();
        Log.e("AttributesFragment", "initEvent: " );
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_attributes;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    private void initLvSetting() {
        mBacTSeekBar.getSeekBar().setProgress(70);
        mLrcTSeekBar.getSeekBar().setProgress(92);
        mLrbTSeekBar.getSeekBar().setProgress(100);
        mTbTSeekBar.getSeekBar().setProgress(40);
    }
    private void initTSeekBar(final CharSequence attribute, final TextSeekBar tSeekBar) {
        Log.e("AttributesFragment", "initTSeekBar: "+attribute );
        tSeekBar.setSeekBarText(attribute);
        tSeekBar.getSeekBar().setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float realProgress=progress/100f;
                Log.d( "AttributesFragment",+ realProgress+"---"+attribute +fromUser);
                tSeekBar.setSeekBarText("app:"+attribute+" "+realProgress);
                if ("tGroupBRatio".equals(attribute)) {
                    mLikeView.setTGroupBRatio(realProgress);
                } else if ("lrGroupCRatio".equals(attribute)){
                    mLikeView.setLrGroupCRatio(realProgress);
                }else if ("lrGroupBRatio".equals(attribute)){
                    mLikeView.setLrGroupBRatio(realProgress);
                }else if ("bGroupACRatio".equals(attribute)){
                    mLikeView.setBGroupACRatio(realProgress);
                }
                mLikeView.requestLayout();
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void useLikeViewBuilder() {
        final LikeView likeView=new LikeViewBuilder(getContext())
            .setRadius(getResources().getDimension(R.dimen.lv_radius))
            .setDefaultColor(Color.GRAY)
            .setCheckedColor(Color.RED)
            .setCycleTime(4000)
            .setUnSelectCycleTime(200)
            .setTGroupBRatio(0.37f)
            .setBGroupACRatio(0.54f)
            .setLrGroupBRatio(1)
            .create();

        RelativeLayout.LayoutParams  layoutParams=  new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT);
        layoutParams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
        rlParent.addView(likeView,layoutParams);
        likeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                likeView.toggle();
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case  R.id.lv:
                mLikeView.toggle();
                break;
            case  R.id.tv_restore:
                initLvSetting();
                break;
        }

    }


}
