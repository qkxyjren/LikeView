package com.jaren.lib.view;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Checkable;
import com.jaren.lib.R;


/**
 * Created
 * by jaren on 2017/5/26.
 */

public class LikeView extends View implements Checkable {

    private  int mDefaultColor;
    private  int mCheckedColor;
    private  float mLrGroupCRatio;
    private  float mLrGroupBRatio;
    private  float mBGroupACRatio;
    private  float mTGroupBRatio;
    private  Drawable mDefaultIcon;
    private  Drawable mCheckedIcon;
    /**
     * 圆最大半径（心形）
     */
    private float mRadius;
    /**
     * View选中用时
     */
    private int mCycleTime;

    /**
     * View取消选中用时
     */
    private  int mUnSelectCycleTime;

    /**
     * 是否点赞
     */
    private boolean isChecked;
    /**
     * 心形默认选中颜色
     */
    public static final int CHECKED_COLOR = 0xffe53a42;
    /**
     * 心形默认未选中颜色
     */
    public static final int DEFAULT_COLOR = 0Xff657487;

    public static final int DEFAULT_CYCLE_TIME = 2000;

    public static final int DEFAULT_UN_SELECT_CYCLE_TIME = 200;

    /**
     * 环绕圆点的颜色
     */
    private static final int[] dotColors = {0xffdaa9fa, 0xfff2bf4b, 0xffe3bca6, 0xff329aed,
        0xffb1eb99, 0xff67c9ad, 0xffde6bac};


    private float mCenterX;
    private float mCenterY;
    private Paint mPaint;
    private ValueAnimator animatorTime;
    private ValueAnimator animatorArgb;
    private AnimatorUpdateListener lvAnimatorUpdateListener;
    private int mCurrentRadius;
    private int mCurrentColor;
    private int mCurrentState;
    private float mCurrentPercent;
    private float rDotL;
    private float rDotS;
    private float offS;
    private float offL;
    private boolean isMax;
    private float dotR;
    private ObjectAnimator unselectAnimator;
    private HeartShapePathController mHeartShapePathController;


    public LikeView(Context context) {
        this(context, null);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LikeView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.LikeView, defStyleAttr, 0);
        mRadius = array.getDimension(R.styleable.LikeView_cirRadius, dp2px(10));
        mCycleTime = array.getInt(R.styleable.LikeView_cycleTime, DEFAULT_CYCLE_TIME);
        mUnSelectCycleTime = array.getInt(R.styleable.LikeView_unSelectCycleTime, DEFAULT_UN_SELECT_CYCLE_TIME);
        mDefaultColor = array.getColor(R.styleable.LikeView_defaultColor, DEFAULT_COLOR);
        mCheckedColor = array.getColor(R.styleable.LikeView_checkedColor, CHECKED_COLOR);
        mLrGroupCRatio = array.getFloat(R.styleable.LikeView_lrGroupCRatio, HeartShapePathController.LR_GROUP_C_RATIO);
        mLrGroupBRatio = array.getFloat(R.styleable.LikeView_lrGroupBRatio, HeartShapePathController.LR_GROUP_B_RATIO);
        mBGroupACRatio = array.getFloat(R.styleable.LikeView_bGroupACRatio, HeartShapePathController.B_GROUP_AC_RATIO);
        mTGroupBRatio = array.getFloat(R.styleable.LikeView_tGroupBRatio, HeartShapePathController.T_GROUP_B_RATIO);
        if (array.hasValue(R.styleable.LikeView_defaultLikeIconRes)){
            mDefaultIcon= array.getDrawable(R.styleable.LikeView_defaultLikeIconRes);
        }
        if (array.hasValue(R.styleable.LikeView_checkedLikeIconRes)){
            mCheckedIcon= array.getDrawable(R.styleable.LikeView_checkedLikeIconRes);
        }
        array.recycle();
        mCenterX =mRadius;
        mCenterY =mRadius;
        mPaint = new Paint();
        mCurrentRadius = (int)mRadius;
        mCurrentColor = mDefaultColor;
        dotR = mRadius / 6;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mCenterX, mCenterY);//使坐标原点在canvas中心位置
        switch (mCurrentState) {
            case State.HEART_VIEW:
                drawInnerShape(canvas, mCurrentRadius, false);
                break;
            case State.CIRCLE_VIEW:
                drawCircle(canvas, mCurrentRadius, mCurrentColor);
                break;
            case State.RING_VIEW:
                drawRing(canvas, mCurrentRadius, mCurrentColor, mCurrentPercent);
                break;
            case State.RING_DOT__HEART_VIEW:
                drawDotWithRing(canvas, mCurrentRadius, mCurrentColor);
                break;
            case State.DOT__HEART_VIEW:
                drawDot(canvas, mCurrentRadius, mCurrentColor);
                break;
        }
    }


    //绘制内部图形
    private void drawInnerShape(Canvas canvas, int radius, boolean isChecked) {
        if (isHasIcon()){
            Drawable icon=isChecked?mCheckedIcon:mDefaultIcon;
            icon.setBounds(-radius,-radius,radius,radius);
            icon.draw(canvas);
        }else {
            int color=isChecked?mCheckedColor:mCurrentColor;
            mPaint.setColor(color);
            mPaint.setAntiAlias(true);
            mPaint.setDither(true);
            mPaint.setStyle(Paint.Style.FILL);
            mHeartShapePathController=new HeartShapePathController(mLrGroupCRatio,mLrGroupBRatio ,mBGroupACRatio,
                mTGroupBRatio);
            canvas.drawPath(mHeartShapePathController.getPath(radius), mPaint);
        }
    }

    private boolean isHasIcon() {
        return mCheckedIcon!=null&&mDefaultIcon!=null;
    }

    //绘制圆
    private void drawCircle(Canvas canvas, int radius, int color) {
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle(0f, 0f, radius, mPaint);
    }


    //绘制圆环
    private void drawRing(Canvas canvas, int radius, int color, float percent) {

        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(2 * mRadius * percent);
        RectF rectF = new RectF(-radius, -radius, radius, radius);
        canvas.drawArc(rectF, 0, 360, false, mPaint);
    }

    //绘制圆点、圆环、心形
    private void drawDotWithRing(Canvas canvas, int radius, int color) {
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);

        mPaint.setStyle(Paint.Style.STROKE);
        if (mCurrentPercent <= 1) {
            RectF rectF = new RectF(-radius, -radius, radius, radius);
            canvas.drawArc(rectF, 0, 360, false, mPaint);
        }
        mCurrentPercent =
            (1f - mCurrentPercent > 1f ? 1f : 1f - mCurrentPercent) * 0.2f;//用于计算圆环宽度，最小0，与动画进度负相关
        mPaint.setStrokeWidth(2 * mRadius * mCurrentPercent);

        float innerR = radius - mRadius * mCurrentPercent + dotR;
        double angleA = 0;
        double angleB = -Math.PI / 20;

        offS += dotR / 17;
        offL += dotR / 14;
        rDotS = radius - mRadius / 12 / 2 + offS;
        rDotL = innerR + offL;

        mPaint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < 7; i++) {
            canvas
                .drawCircle((float) (rDotS * Math.sin(angleA)), (float) (rDotS * Math.cos(angleA)),
                    dotR, mPaint);
            angleA += 2 * Math.PI / 7;
            canvas
                .drawCircle((float) (rDotL * Math.sin(angleB)), (float) (rDotL * Math.cos(angleB)),
                    dotR, mPaint);
            angleB += 2 * Math.PI / 7;
        }
        mCurrentRadius = (int) (mRadius / 3 + offL * 4);
        drawInnerShape(canvas, mCurrentRadius, true);

    }

    //绘制圆点、心形
    private void drawDot(Canvas canvas, int radius, int color) {
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);

        double angleA = 0;
        double angleB = -Math.PI / 20;
        float dotRS;
        float dotRL;
        if (rDotL < 2.6 * mRadius) {//限制圆点的扩散范围
            rDotS += dotR / 17;
            rDotL += dotR / 14;
        }
        if (!isMax && mCurrentRadius <= 1.1 * mRadius) {
            offL += dotR / 14;
            mCurrentRadius = (int) (mRadius / 3 + offL * 4);

        } else {
            isMax = true;
        }

        if (isMax && mCurrentRadius > mRadius) {
            mCurrentRadius = (int) (mCurrentRadius - dotR / 16);

        }
        drawInnerShape(canvas, mCurrentRadius, true);

        mPaint.setAlpha((int) (255 * (1 - mCurrentPercent)));//圆点逐渐透明
        dotRS = (float) (dotR * (1 - mCurrentPercent));
        dotRL = (float) (dotR * (1 - mCurrentPercent) * 4) > dotR ? dotR
            : (float) (dotR * (1 - mCurrentPercent) * 3);
        for (int i = 0; i < 7; i++) {
            mPaint.setColor(dotColors[i]);
            canvas
                .drawCircle((float) (rDotS * Math.sin(angleA)), (float) (rDotS * Math.cos(angleA)),
                    dotRS, mPaint);
            angleA += 2 * Math.PI / 7;
            canvas
                .drawCircle((float) (rDotL * Math.sin(angleB)), (float) (rDotL * Math.cos(angleB)),
                    dotRL, mPaint);
            angleB += 2 * Math.PI / 7;
        }

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mWidth, mHeight;
        mWidth = (int) (5.2 * mRadius + 2 * dotR);
        mHeight = (int) (5.2 * mRadius + 2 * dotR);
        setMeasuredDimension(mWidth, mHeight);

    }

    /**
     * 展现View选中后的变化效果
     */
    private void startSelectViewMotion() {
        resetState();
        if (animatorTime == null) {
            animatorTime = ValueAnimator.ofInt(0, 1200);
            animatorTime.setDuration(mCycleTime);
            animatorTime.setInterpolator(new LinearInterpolator());//需要随时间匀速变化
        }
        if (lvAnimatorUpdateListener == null) {
            lvAnimatorUpdateListener = new LvAnimatorUpdateListener();
            animatorTime.addUpdateListener(lvAnimatorUpdateListener);
        }
        animatorTime.start();
    }

    private void startUnselectViewMotion() {
        if (unselectAnimator == null) {
            PropertyValuesHolder holderX = PropertyValuesHolder.ofFloat("scaleX", 1.0f, 0.8f, 1.0f);
            PropertyValuesHolder holderY = PropertyValuesHolder.ofFloat("scaleY", 1.0f, 0.8f, 1.0f);
            unselectAnimator = ObjectAnimator.ofPropertyValuesHolder(this, holderX, holderY).setDuration(mUnSelectCycleTime);
            unselectAnimator.setInterpolator(new OvershootInterpolator());
        }
        unselectAnimator.start();
    }

    private class LvAnimatorUpdateListener implements AnimatorUpdateListener {

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            int animatedValue = (int) animation.getAnimatedValue();
            if (animatedValue == 0) {
                if (animatorArgb == null || !animatorArgb.isRunning()) {
                    animatorArgb = ofArgb(mDefaultColor, 0Xfff74769, 0Xffde7bcc);
                    animatorArgb.setDuration(mCycleTime * 28 / 120);
                    animatorArgb.setInterpolator(new LinearInterpolator());
                    animatorArgb.start();
                }
            } else if (animatedValue <= 100) {
                float percent = calcPercent(0f, 100f, animatedValue);
                mCurrentRadius = (int) (mRadius - mRadius * percent);
                if (animatorArgb != null && animatorArgb.isRunning()) {
                    mCurrentColor = (int) animatorArgb.getAnimatedValue();
                }
                mCurrentState = State.HEART_VIEW;
                invalidate();

            } else if (animatedValue <= 280) {
                float percent = calcPercent(100f, 340f, animatedValue);//此阶段未达到最大半径
                mCurrentRadius = (int) (2 * mRadius * percent);
                if (animatorArgb != null && animatorArgb.isRunning()) {
                    mCurrentColor = (int) animatorArgb.getAnimatedValue();
                }
                mCurrentState =  State.CIRCLE_VIEW;
                invalidate();
            } else if (animatedValue <= 340) {
                float percent = calcPercent(100f, 340f, animatedValue);//半径接上一阶段增加，此阶段外环半径已经最大值
                mCurrentPercent = 1f - percent + 0.2f > 1f ? 1f
                    : 1f - percent + 0.2f;//用于计算圆环宽度，最小0.2，与动画进度负相关
                mCurrentRadius = (int) (2 * mRadius * percent);
                if (animatorArgb != null && animatorArgb.isRunning()) {
                    mCurrentColor = (int) animatorArgb.getAnimatedValue();
                }
                mCurrentState =  State.RING_VIEW;
                invalidate();
            } else if (animatedValue <= 480) {
                float percent = calcPercent(340f, 480f, animatedValue);//内环半径增大直至消亡
                mCurrentPercent = percent;
                mCurrentRadius = (int) (2 * mRadius);//外环半径不再改变
                mCurrentState =  State.RING_DOT__HEART_VIEW;
                invalidate();
            } else if (animatedValue <= 1200) {
                float percent = calcPercent(480f, 1200f, animatedValue);
                mCurrentPercent = percent;
                mCurrentState =  State.DOT__HEART_VIEW;
                invalidate();
                if (animatedValue == 1200) {
                    animatorTime.cancel();
                    if (!isChecked) {
                        restoreDefaultView();
                    }
                }
            }
        }
    }

    /**
     * 重置为初始状态
     */
    private void resetState() {
        mCurrentPercent = 0;
        mCurrentRadius = 0;
        isMax = false;
        rDotS = 0;
        rDotL = 0;
        offS = 0;
        offL = 0;
    }

    private float calcPercent(float start, float end, float current) {
        return (current - start) / (end - start);
    }


    /**
     * @return 由于颜色变化的动画API是SDK21 添加的，这里导入了源码的 ArgbEvaluator
     */
    private ValueAnimator ofArgb(int... values) {
        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(values);
        anim.setEvaluator(ArgbEvaluator.getInstance());
        return anim;
    }

    private float dp2px(int value) {
        return TypedValue
            .applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, getResources().getDisplayMetrics());
    }


    /**
     * 选择/取消选择 有动画
     */
    private void selectLike(boolean isSetChecked) {
        isChecked=isSetChecked;
        if (isSetChecked) {
            cancelAnimator();
            startSelectViewMotion();
        } else {
            if (!isAnimatorTimeRunning()) {
                restoreDefaultView();
                startUnselectViewMotion();
            }
        }

    }


    /**
     * 选择/取消选择 无动画
     */
    private void selectLikeWithoutAnimator(boolean isSetChecked) {
        isChecked=isSetChecked;
        cancelAnimator();
        if (isSetChecked) {
            mCurrentColor = mCheckedColor;
            mCurrentRadius = (int) mRadius;
            mCurrentState = State.HEART_VIEW;
            invalidate();
        } else {
            restoreDefaultView();
        }

    }

    private void restoreDefaultView() {
        mCurrentColor = mDefaultColor;
        mCurrentRadius = (int) mRadius;
        mCurrentState = State.HEART_VIEW;
        invalidate();
    }

    private void cancelAnimator() {
        if (isAnimatorTimeRunning()) {
            animatorTime.cancel();
        }
    }

    private boolean isAnimatorTimeRunning() {
        return animatorTime != null && animatorTime.isRunning();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        releaseAnimator(animatorTime);
        releaseAnimator(animatorArgb);
        releaseAnimator(unselectAnimator);
    }

    private void releaseAnimator(Animator animator ) {
        if (animator != null) {
            animator.removeAllListeners();
            animator.cancel();
        }
    }

    /*=========================================public=========================================*/

    @Override
    public void setChecked(boolean checked) {
        selectLike(checked);
    }

    /**
     * the method is equivalent to {@link #setChecked(boolean)}<br>
     * but it performs no animator and it will cancel the  animator that is running.
     */
    public void setCheckedWithoutAnimator(boolean checked) {
        selectLikeWithoutAnimator(checked);
    }

    @Override
    public void toggle() {
        selectLike(!isChecked);
    }

    /**
     * the method is equivalent to {@link  #toggle()}<br>
     * but it performs no animator and it will cancel the  animator that is running.
     */
    public void toggleWithoutAnimator() {
        selectLikeWithoutAnimator(!isChecked);
    }

    @Override
    public boolean isChecked() {
        return this.isChecked;
    }


    public void setDefaultColor(int defaultColor) {
        this.mDefaultColor = defaultColor;
    }

    public void setCheckedColor(int checkedColor) {
        this.mCheckedColor = checkedColor;
    }

    /**
     * set unselect animation-duration(ms)
     */
    public void setUnSelectCycleTime(int unSelectCycleTime) {
        this.mUnSelectCycleTime = unSelectCycleTime;
    }

    public void setLrGroupCRatio(float lrGroupCRatio) {
        this.mLrGroupCRatio = lrGroupCRatio;
    }

    public void setLrGroupBRatio(float lrGroupBRatio) {
        this.mLrGroupBRatio = lrGroupBRatio;
    }

    public void setBGroupACRatio(float bGroupACRatio) {
        this.mBGroupACRatio = bGroupACRatio;
    }

    public void setTGroupBRatio(float tGroupBRatio) {
        this.mTGroupBRatio = tGroupBRatio;
    }

    public void setDefaultIcon(Drawable defaultIcon) {
        this.mDefaultIcon = defaultIcon;
    }

    public void setCheckedIcon(Drawable checkedIcon) {
        this.mCheckedIcon = checkedIcon;
    }

    public void setRadius(float radius) {
        this.mRadius = radius;
    }
    /**
     * set select animation-duration(ms)
     */
    public void setCycleTime(int cycleTime) {
        this.mCycleTime = cycleTime;
    }

}
