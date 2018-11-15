package com.jaren.lib.view;

import static com.jaren.lib.view.LikeView.CHECKED_COLOR;
import static com.jaren.lib.view.LikeView.DEFAULT_COLOR;
import static com.jaren.lib.view.LikeView.DEFAULT_CYCLE_TIME;
import static com.jaren.lib.view.LikeView.DEFAULT_DOT_COLORS;
import static com.jaren.lib.view.LikeView.DEFAULT_RING_COLOR;
import static com.jaren.lib.view.LikeView.DEFAULT_UN_SELECT_CYCLE_TIME;
import static com.jaren.lib.view.LikeView.DOT_SIZE_SCALE;
import static com.jaren.lib.view.LikeView.RADIUS_INNER_SHAPE_SCALE;

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
    private float tGroupBRatio;
    private int innerShapeScale;
    private int dotSizeScale;
    private Drawable defaultIcon;
    private Drawable checkedIcon;
    private int[] dotColors;
    private int ringColor;
    private float radius;
    private int cycleTime;
    private int unSelectCycleTime;
    private boolean allowRandomDotColor;
    public final Context mContext;


    public LikeViewBuilder(Context context) {
        this.mContext = context;
        this.defaultColor = DEFAULT_COLOR;
        this.checkedColor = CHECKED_COLOR;
        this.lrGroupCRatio = HeartShapePathController.LR_GROUP_C_RATIO;
        this.lrGroupBRatio = HeartShapePathController.LR_GROUP_B_RATIO;
        this.bGroupACRatio = HeartShapePathController.B_GROUP_AC_RATIO;
        this.tGroupBRatio = HeartShapePathController.T_GROUP_B_RATIO;
        this.radius = 30;
        this.cycleTime = DEFAULT_CYCLE_TIME;
        this.unSelectCycleTime = DEFAULT_UN_SELECT_CYCLE_TIME;
        this.dotColors = DEFAULT_DOT_COLORS;
        this.ringColor = DEFAULT_RING_COLOR;
        this.innerShapeScale = RADIUS_INNER_SHAPE_SCALE;
        this.dotSizeScale = DOT_SIZE_SCALE;
        this.allowRandomDotColor = true;
    }
    /**
     * {@link  LikeView#setDefaultColor(int)}
     */
    public LikeViewBuilder setDefaultColor(int defaultColor) {
        this.defaultColor = defaultColor;
        return this;

    }
    /**
     * {@link  LikeView#setCheckedColor(int)}
     */
    public LikeViewBuilder setCheckedColor(int checkedColor) {
        this.checkedColor = checkedColor;
        return this;

    }
    /**
     * {@link  LikeView#setLrGroupCRatio(float)}
     */
    public LikeViewBuilder setLrGroupCRatio(float lrGroupCRatio) {
        this.lrGroupCRatio = lrGroupCRatio;
        return this;
    }
    /**
     * {@link  LikeView#setLrGroupBRatio(float)}
     */
    public LikeViewBuilder setLrGroupBRatio(float lrGroupBRatio) {
        this.lrGroupBRatio = lrGroupBRatio;
        return this;
    }
    /**
     * {@link  LikeView#setBGroupACRatio(float)}
     */
    public LikeViewBuilder setBGroupACRatio(float bGroupACRatio) {
        this.bGroupACRatio = bGroupACRatio;
        return this;
    }
    /**
     * {@link  LikeView#setTGroupBRatio(float)}
     */
    public LikeViewBuilder setTGroupBRatio(float tGroupBRatio) {
        this.tGroupBRatio = tGroupBRatio;
        return this;
    }
    /**
     * {@link  LikeView#setDefaultColor(int)}
     */
    public LikeViewBuilder setDefaultIcon(Drawable defaultIcon) {
        this.defaultIcon = defaultIcon;
        return this;

    }
    /**
     * {@link  LikeView#setCheckedIcon(Drawable)}
     */
    public LikeViewBuilder setCheckedIcon(Drawable checkedIcon) {
        this.checkedIcon = checkedIcon;
        return this;

    }
    /**
     * {@link  LikeView#setRadius(float)}
     */
    public LikeViewBuilder setRadius(float radius) {
        this.radius = radius;
        return this;
    }
    /**
     * {@link  LikeView#setCycleTime(int)}
     */
    public LikeViewBuilder setCycleTime(int cycleTime) {
        this.cycleTime = cycleTime;
        return this;
    }
    /**
     * {@link  LikeView#setUnSelectCycleTime(int)}
     */
    public LikeViewBuilder setUnSelectCycleTime(int unSelectCycleTime) {
        this.unSelectCycleTime = unSelectCycleTime;
        return this;
    }

    /**
     * {@link  LikeView#setInnerShapeScale(int)}
     */
    public LikeViewBuilder setInnerShapeScale(int innerShapeScale) {
        this.innerShapeScale = innerShapeScale;
        return this;

    }
    /**
     * {@link  LikeView#setDotSizeScale(int)}
     */
    public LikeViewBuilder setDotSizeScale(int dotSizeScale) {
        this.dotSizeScale = dotSizeScale;
        return this;
    }
    /**
     * {@link  LikeView#setDotColors(int[])}
     */
    public LikeViewBuilder setDotColors(int[] dotColors) {
        this.dotColors = dotColors;
        return this;
    }
    /**
     * {@link  LikeView#setRingColor(int)}
     */
    public LikeViewBuilder setRingColor(int ringColor) {
        this.ringColor = ringColor;
        return this;
    }
    /**
     * {@link  LikeView#setAllowRandomDotColor(boolean)}
     */
    public LikeViewBuilder setAllowRandomDotColor(boolean allowRandomDotColor) {
        this.allowRandomDotColor = allowRandomDotColor;
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
        likeView.setDotColors(dotColors);
        likeView.setRingColor(ringColor);
        likeView.setInnerShapeScale(innerShapeScale);
        likeView.setDotSizeScale(dotSizeScale);
        likeView.setAllowRandomDotColor(allowRandomDotColor);
        return likeView;
    }

}
