package xyz.ibat.indicator.titles;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import xyz.ibat.indicator.simple.SimplePagerTitleView;
import xyz.ibat.indicator.utils.ArgbEvaluatorHolder;

/**
 * @author DongJr
 *
 * @date 2018/5/31.
 *
 * 渐变色标题
 */
public class ColorChangeTitleView extends SimplePagerTitleView{

    public ColorChangeTitleView(Context context) {
        super(context);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        int color = ArgbEvaluatorHolder.eval(leavePercent, mSelectedColor, mNormalColor);
        setTextColor(color);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        int color = ArgbEvaluatorHolder.eval(enterPercent, mNormalColor, mSelectedColor);
        setTextColor(color);
    }

}
