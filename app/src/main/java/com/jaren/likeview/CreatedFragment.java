package com.jaren.likeview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2018/11/6
 * @author: LiRJ
 */
public class CreatedFragment extends BaseFragment implements OnClickListener {
    public static final int[] DOT_COLORS = {0xffdaa9fa, 0xfff2bf4b, 0xffe3bca6, 0xff329aed,
        0xffb1eb99, 0xff67c9ad, 0xff000000};
    private static final String TAG_CF =CreatedFragment.class.getSimpleName();
    private LikeView mLikeViewIcon;
    private LikeView mLikeViewIconBig;
    private LikeView mLikeViewXml;
    private View mTvBuilder;
    private ViewGroup mLLParent;
    private RecyclerView mRecyclerView;


    @Override
    protected void initEvent(Bundle savedInstanceState) {
        mLLParent = mRootView.findViewById(R.id.ll_parent);
        mLikeViewXml = mRootView.findViewById(R.id.lv_xml);
        mLikeViewXml = mRootView.findViewById(R.id.lv_xml);
        mLikeViewIcon = mRootView.findViewById(R.id.lv_icon);
        mLikeViewIconBig = mRootView.findViewById(R.id.lv_icon_big);
        mTvBuilder = mRootView.findViewById(R.id.tv_builder);
        mRecyclerView = mRootView.findViewById(R.id.recyclerview);
        mLikeViewXml.setOnClickListener(this);
        mLikeViewIcon.setOnClickListener(this);
        mLikeViewIconBig.setOnClickListener(this);
        mTvBuilder.setOnClickListener(this);
        useLikeViewBuilder();
        mLikeViewIcon.setCheckedWithoutAnimator(true);
        Log.i(TAG_CF, "mLikeViewIcon isChecked: "+mLikeViewIcon.isChecked());
//        initList();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_created;
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

    private void initList() {
        List<Integer> list=new ArrayList<>();
        for (int i = 0; i < 120; i++) {
            list.add(i);
        }
        mRecyclerView.setAdapter(new LvAdapter(list));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }


    private class LvAdapter extends RecyclerView.Adapter<LvAdapter.LvHolder> {
        private final List<Integer> list;

        public LvAdapter(List<Integer> list) {
            this.list=list;
        }

        @NonNull
        @Override
        public LvHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view  =LayoutInflater.from(getContext()).inflate(R.layout.item_lv,viewGroup,false);
            return new LvHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final LvHolder lvHolder, int i) {
            lvHolder.lv.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    lvHolder.lv.toggle();
                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class LvHolder extends RecyclerView.ViewHolder {

            private  LikeView lv;

            public LvHolder(View itemView) {
                super(itemView);
                lv = itemView.findViewById(R.id.lv);
            }
        }
    }
}
