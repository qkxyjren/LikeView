package com.jaren.likeview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
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
public class AttributesFragment extends Fragment implements OnClickListener {

    private LikeView mLikeView;
    private TextView mTvMsg;
    private RelativeLayout rlParent;
    private TextSeekBar mBacTSeekBar;
    private TextSeekBar mLrcTSeekBar;
    private TextSeekBar mLrbTSeekBar;
    private TextSeekBar mTbTSeekBar;
    private TextView mTvRestore;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View root=  inflater.inflate(R.layout.fragment_attributes,container,false);
        rlParent = root.findViewById(R.id.rl_parent);
        mLikeView = root.findViewById(R.id.lv);
        mBacTSeekBar = root.findViewById(R.id.bac_s_bar);
        mLrcTSeekBar = root.findViewById(R.id.lrc_s_bar);
        mLrbTSeekBar = root.findViewById(R.id.lrb_s_bar);
        mTbTSeekBar = root.findViewById(R.id.tb_s_bar);
        mTvMsg = root.findViewById(R.id.tv_msg);
        mTvRestore = root.findViewById(R.id.tv_restore);

        mLikeView.setOnClickListener(this);
        mTvRestore.setOnClickListener(this);
        initTSeekBar("bGroupACRatio", mBacTSeekBar);
        initTSeekBar("lrGroupCRatio", mLrcTSeekBar);
        initTSeekBar("lrGroupBRatio", mLrbTSeekBar);
        initTSeekBar("tGroupBRatio", mTbTSeekBar);
        initLvSetting();
        useLikeViewBuilder();
        return root;
    }
    private void initLvSetting() {
        mBacTSeekBar.getSeekBar().setProgress(70);
        mLrcTSeekBar.getSeekBar().setProgress(92);
        mLrbTSeekBar.getSeekBar().setProgress(100);
        mTbTSeekBar.getSeekBar().setProgress(40);
    }
    private void initTSeekBar(final CharSequence attribute, final TextSeekBar tSeekBar) {
        tSeekBar.setSeekBarText(attribute);
        tSeekBar.getSeekBar().setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float realProgress=progress/100f;
                Log.d( "LikeView",realProgress+"");
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
            .setCycleTime(1000)
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
