package xyz.ibat.indicator.simple;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

import xyz.ibat.indicator.base.IPagerTitle;


/**
 * @author DongJr
 *
 * @date 2018/5/28.
 */
public class SimplePagerTitleView extends TextView implements IPagerTitle {

    protected int mNormalColor;
    protected int mSelectedColor;

    public SimplePagerTitleView(Context context) {
        this(context, null);
    }

    public SimplePagerTitleView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimplePagerTitleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setGravity(Gravity.CENTER);
        setSingleLine();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        setTextColor(mSelectedColor);
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        setTextColor(mNormalColor);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        Log.d("dong", " onLeave index " + index + " leavePercent " + leavePercent + " leftToRight " + leftToRight);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        Log.d("dong", " onEnter index " + index + " enterPercent " + enterPercent + " leftToRight " + leftToRight);
    }

    public void setNormalColor(int color) {
        mNormalColor = color;
    }

    public void setSelectedColor(int color) {
        mSelectedColor = color;
    }
}
