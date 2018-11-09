package com.jaren.likeview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import com.jaren.lib.view.LikeView;
import com.jaren.lib.view.LikeViewBuilder;

/**
 * @date: 2018/11/6
 * @author: LiRJ
 */
public class CreatedFragment extends Fragment implements OnClickListener {

    private LikeView mLikeViewIcon;
    private LikeView mLikeViewXml;
    private View mTvBuilder;
    private ViewGroup mLLParent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View  root= (ViewGroup) inflater.inflate(R.layout.fragment_created,container,false);
        mLLParent = root.findViewById(R.id.ll_parent);
        mLikeViewXml = root.findViewById(R.id.lv_xml);
        mLikeViewXml = root.findViewById(R.id.lv_xml);
        mLikeViewIcon = root.findViewById(R.id.lv_icon);
        mTvBuilder = root.findViewById(R.id.tv_builder);
        mLikeViewXml.setOnClickListener(this);
        mLikeViewIcon.setOnClickListener(this);
        mTvBuilder.setOnClickListener(this);
        useLikeViewBuilder();
        return root;
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
        LinearLayout.LayoutParams layoutParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity= Gravity.CENTER_HORIZONTAL;
        mLLParent.addView(likeView,layoutParams);
        likeView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                likeView.toggle();
            }
        });
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lv_xml:
               mLikeViewXml.toggle();
                break;
            case R.id.lv_icon:
                mLikeViewIcon.toggle();
                break;
            case R.id.tv_builder:
                useLikeViewBuilder();
                break;
        }

    }




}
