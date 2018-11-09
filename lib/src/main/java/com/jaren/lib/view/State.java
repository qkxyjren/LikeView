package com.jaren.lib.view;

/**
 * @date: 2018/11/7
 * @author: LiRJ
 */
public  final class State {
    /**
     * 1.绘制心形并伴随缩小和颜色渐变
     */
    public static final int HEART_VIEW = 0;
    /**
     * 2.绘制圆并伴随放大和颜色渐变
     */
    public static final int CIRCLE_VIEW = 1;
    /**
     * 3.绘制圆环并伴随放大和颜色渐变
     */
    public static final int RING_VIEW = 2;
    /**
     * 4.圆环减消失、心形放大、周围环绕十四圆点
     */
    public static final int RING_DOT__HEART_VIEW = 3;
    /**
     * 5.环绕的十四圆点向外移动并缩小、透明度渐变、渐隐
     */
    public static final int DOT__HEART_VIEW = 4;
}
