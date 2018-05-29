package xyz.ibat.indicator.simple;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.TextView;

import xyz.ibat.indicator.base.IPagerTitle;


/**
 * @author DongJr
 *
 * @date 2018/5/28.
 */
public class SimplePagerTitleView extends TextView implements IPagerTitle {

    private int normalColor;

    private int selectedColor;

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
        setTextColor(selectedColor);
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        setTextColor(normalColor);
    }

    public void setNormalColor(int color) {
        normalColor = color;
    }

    public void setSelectedColor(int color) {
        selectedColor = color;
    }
}
