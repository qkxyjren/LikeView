package com.jaren.likeview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
public class CreatedFragment extends Fragment implements OnClickListener {
    public static final int[] DOT_COLORS = {0xffdaa9fa, 0xfff2bf4b, 0xffe3bca6, 0xff329aed,
        0xffb1eb99, 0xff67c9ad, 0xff000000};
    private static final String TAG_CF =CreatedFragment.class.getSimpleName();
    private LikeView mLikeViewIcon;
    private LikeView mLikeViewIconBig;
    private LikeView mLikeViewXml;
    private View mTvBuilder;
    private ViewGroup mLLParent;
    private RecyclerView mRecyclerView;

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
        mRecyclerView = root.findViewById(R.id.recyclerview);
        mLikeViewXml.setOnClickListener(this);
        mLikeViewIcon.setOnClickListener(this);
        mLikeViewIconBig.setOnClickListener(this);
        mTvBuilder.setOnClickListener(this);
        useLikeViewBuilder();
        mLikeViewIcon.setCheckedWithoutAnimator(true);
        Log.i(TAG_CF, "mLikeViewIcon isChecked: "+mLikeViewIcon.isChecked());
//        initList();
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
