package xyz.ibat.indicator.simple;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextPaint;
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
        super(context);
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
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
    }

    @Override
    public int getContentLeft() {
        Rect rect = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), rect);
        return getLeft() + getWidth() / 2 - rect.width() / 2;
    }

    @Override
    public int getContentRight() {
        Rect rect = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), rect);
        return getRight() - getWidth() / 2 + rect.width() / 2;
    }

    @Override
    public int getContentTop() {
        Rect rect = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), rect);
        return rect.top;
    }

    @Override
    public int getContentBottom() {
        Rect rect = new Rect();
        getPaint().getTextBounds(getText().toString(), 0, getText().length(), rect);
        return rect.bottom;
    }

    public void setNormalColor(int color) {
        mNormalColor = color;
    }

    public void setSelectedColor(int color) {
        mSelectedColor = color;
    }
}
