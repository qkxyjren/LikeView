package com.jaren.likeview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    public static final int[] DOT_COLORS = {0xffdaa9fa, 0xfff2bf4b, 0xffe3bca6, 0xff329aed,
        0xffb1eb99, 0xff67c9ad, 0xff000000};
    private static final String TAG_CF =CreatedFragment.class.getSimpleName();
    private LikeView mLikeViewIcon;
    private LikeView mLikeViewIconBig;
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
        mLikeViewIconBig = root.findViewById(R.id.lv_icon_big);
        mTvBuilder = root.findViewById(R.id.tv_builder);
        mLikeViewXml.setOnClickListener(this);
        mLikeViewIcon.setOnClickListener(this);
        mLikeViewIconBig.setOnClickListener(this);
        mTvBuilder.setOnClickListener(this);
        useLikeViewBuilder();
        mLikeViewIcon.setCheckedWithoutAnimator(true);
        Log.i(TAG_CF, "mLikeViewIcon isChecked: "+mLikeViewIcon.isChecked());
        return root;
    }

    private void useLikeViewBuilder() {
        final LikeView likeView=new LikeViewBuilder(getContext())
            .setRadius(getResources().getDimension(R.dimen.lv_radius))
            .setDefaultColor(Color.GRAY)
            .setCheckedColor(Color.RED)
            .setCycleTime(1600)
            .setUnSelectCycleTime(200)
            .setTGroupBRatio(0.37f)
            .setBGroupACRatio(0.54f)
            .setDotColors(DOT_COLORS)
            .setLrGroupBRatio(1)
            .setInnerShapeScale(3)
            .setDotSizeScale(10)
            .setAllowRandomDotColor(false)
            .create();
        LinearLayout.LayoutParams layoutParams=new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity= Gravity.CENTER_HORIZONTAL;
        mLLParent.addView(likeView,layoutParams);
        likeView.setCheckedWithoutAnimator(true);
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
                Log.i(TAG_CF, "mLikeViewIcon isChecked: "+mLikeViewIcon.isChecked());
                break;
            case R.id.lv_icon_big:
                mLikeViewIconBig.toggle();
                break;
            case R.id.tv_builder:
                useLikeViewBuilder();
                break;
        }

    }




}
