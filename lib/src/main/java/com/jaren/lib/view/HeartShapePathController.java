package com.jaren.lib.view;

import android.graphics.Path;
import android.graphics.PointF;

/**
 * @date: 2018/11/6
 * @author: LiRJ
 */
final public class HeartShapePathController {
    /**
     * Bézier曲线画圆的近似常数
     */
    private static final float BEZIER_C = 0.551915024494f;
    public static final float LR_GROUP_C_RATIO = 0.92f;
    public static final float LR_GROUP_B_RATIO = 1.0f;
    public static final float B_GROUP_AC_RATIO =0.7f ;
    public static final float T_GROUP_B_RATIO =0.5f ;
    private final float mLrGroupCRatio;
    private final float mLrGroupBRatio;
    private final float mBGroupACRatio;
    private final float mTroupBRatio;

    private PointF tPointA;
    private PointF tPointB;
    private PointF tPointC;
    private PointF rPointA;
    private PointF rPointB;
    private PointF rPointC;
    private PointF bPointA;
    private PointF bPointB;
    private PointF bPointC;
    private PointF lPointA;
    private PointF lPointB;
    private PointF lPointC;

    public HeartShapePathController(float lrGroupCRatio,float lrGroupBRatio, float bGroupLRRatio,float tGroupBRatio) {
        this.mLrGroupCRatio =lrGroupCRatio;
        this.mLrGroupBRatio =lrGroupBRatio;
        this.mBGroupACRatio =bGroupLRRatio;
        this.mTroupBRatio =tGroupBRatio;
    }
    /**
     * 初始化Bézier 曲线四组控制点
     */
    private void updateControlPoints(int radius) {
        float  offset = BEZIER_C * radius;

        tPointA = new PointF(-offset, -radius);
        tPointB = new PointF(0, -radius * mTroupBRatio);
        tPointC = new PointF(offset, -radius);

        rPointA = new PointF(radius, -offset);
        rPointB = new PointF(radius*mLrGroupBRatio, 0);
        rPointC = new PointF(radius * mLrGroupCRatio, offset);

        bPointA = new PointF(-offset, radius * mBGroupACRatio);
        bPointB = new PointF(0, radius);
        bPointC = new PointF(offset, radius * mBGroupACRatio);

        lPointA = new PointF(-radius, -offset);
        lPointB = new PointF(-radius* mLrGroupBRatio, 0);
        lPointC = new PointF(-radius * mLrGroupCRatio, offset);
    }

    public Path getPath(int radius) {
        updateControlPoints(radius);
        Path path = new Path();
        path.moveTo(tPointB.x, tPointB.y);
        path.cubicTo(tPointC.x, tPointC.y, rPointA.x, rPointA.y, rPointB.x, rPointB.y);
        path.cubicTo(rPointC.x, rPointC.y, bPointC.x, bPointC.y, bPointB.x, bPointB.y);
        path.cubicTo(bPointA.x, bPointA.y, lPointC.x, lPointC.y, lPointB.x, lPointB.y);
        path.cubicTo(lPointA.x, lPointA.y, tPointA.x, tPointA.y, tPointB.x, tPointB.y);
        return path;
    }

}
