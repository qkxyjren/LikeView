package com.jaren.likeview;

import android.content.Context;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * @date: 2018/11/7
 * @author: LiRJ
 */
public class TextSeekBar extends LinearLayout {

    private final TextView tv;
    private final SeekBar sb;

    public TextSeekBar(Context context) {
        this(context, null);
    }

    public TextSeekBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        View root = LayoutInflater.from(context).inflate(R.layout.text_seekbar_item, this, true);
        tv = root.findViewById(R.id.tv);
        sb = root.findViewById(R.id.s_bar);
        sb.setId((this.getId()));
    }


    public void setSeekBarText(CharSequence s) {
        tv.setText(s);
    }

    public SeekBar getSeekBar() {
        return sb;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        try {
            super.onRestoreInstanceState(state);
        } catch (Exception e) {
        }
        state = null;
    }


}
