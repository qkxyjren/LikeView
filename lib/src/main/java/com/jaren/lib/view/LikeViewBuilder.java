package com.jaren.lib.view;

import static com.jaren.lib.view.LikeView.CHECKED_COLOR;
import static com.jaren.lib.view.LikeView.DEFAULT_COLOR;
import static com.jaren.lib.view.LikeView.DEFAULT_CYCLE_TIME;
import static com.jaren.lib.view.LikeView.DEFAULT_UN_SELECT_CYCLE_TIME;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * @date: 2018/11/7
 * @author: LiRJ
 */
public class LikeViewBuilder {

    private int defaultColor;
    private int checkedColor;
    private float lrGroupCRatio;
    private float lrGroupBRatio;
    private float bGroupACRatio;
    private  float tGroupBRatio;
    private Drawable defaultIcon;
    private Drawable checkedIcon;
    private float radius;
    private int cycleTime;
    private int unSelectCycleTime;
    public final Context mContext;


    public LikeViewBuilder(Context context) {
        this.mContext = context;
        this.defaultColor=DEFAULT_COLOR;
        this.checkedColor=CHECKED_COLOR;
        this.lrGroupCRatio =HeartShapePathController.LR_GROUP_C_RATIO;
        this.lrGroupBRatio =HeartShapePathController.LR_GROUP_B_RATIO;
        this.bGroupACRatio =HeartShapePathController.B_GROUP_AC_RATIO;
        this.tGroupBRatio =HeartShapePathController.T_GROUP_B_RATIO;
        this.radius=30;
        this.cycleTime=DEFAULT_CYCLE_TIME;
        this.unSelectCycleTime=DEFAULT_UN_SELECT_CYCLE_TIME;
    }

    public LikeViewBuilder setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
        return this;

    }

    public LikeViewBuilder setCheckedColor(int checkedColor) {
        this.checkedColor = checkedColor;
        return this;

    }

    public LikeViewBuilder setLrGroupCRatio(float lrGroupCRatio) {
        this.lrGroupCRatio = lrGroupCRatio;
        return this;
    }

    public LikeViewBuilder setLrGroupBRatio(float lrGroupBRatio) {
        this.lrGroupBRatio = lrGroupBRatio;
        return this;
    }

    public LikeViewBuilder setBGroupACRatio(float bGroupACRatio) {
        this.bGroupACRatio = bGroupACRatio;
        return this;
    }
    public LikeViewBuilder setTGroupBRatio(float tGroupBRatio) {
        this.tGroupBRatio= tGroupBRatio;
        return this;
    }
    public LikeViewBuilder setDefaultIcon(Drawable defaultIcon) {
        this.defaultIcon = defaultIcon;
        return this;

    }

    public LikeViewBuilder setCheckedIcon(Drawable checkedIcon) {
        this.checkedIcon = checkedIcon;
        return this;

    }

    public LikeViewBuilder setRadius(float radius) {
        this.radius = radius;
        return this;
    }

    public LikeViewBuilder setCycleTime(int cycleTime) {
        this.cycleTime = cycleTime;
        return this;
    }

    public LikeViewBuilder setUnSelectCycleTime(int unSelectCycleTime) {
        this.unSelectCycleTime = unSelectCycleTime;
        return this;
    }

    public LikeView create() {
        LikeView likeView = new LikeView(mContext);
        likeView.setDefaultColor(defaultColor);
        likeView.setCheckedColor(checkedColor);
        likeView.setLrGroupCRatio(lrGroupCRatio);
        likeView.setLrGroupBRatio(lrGroupBRatio);
        likeView.setBGroupACRatio(bGroupACRatio);
        likeView.setTGroupBRatio(tGroupBRatio);
        likeView.setDefaultIcon(defaultIcon);
        likeView.setCheckedIcon(checkedIcon);
        likeView.setRadius(radius);
        likeView.setCycleTime(cycleTime);
        likeView.setUnSelectCycleTime(unSelectCycleTime);
        return likeView;
    }

}
