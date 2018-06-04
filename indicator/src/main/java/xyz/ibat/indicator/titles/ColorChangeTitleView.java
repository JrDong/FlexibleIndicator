package xyz.ibat.indicator.titles;

import android.animation.ArgbEvaluator;
import android.content.Context;

import xyz.ibat.indicator.simple.SimplePagerTitleView;

/**
 * @author DongJr
 *
 * @date 2018/5/31.
 *
 * 渐变色标题
 */
public class ColorChangeTitleView extends SimplePagerTitleView{
    private ArgbEvaluator mArgbEvaluator;
    public ColorChangeTitleView(Context context) {
        super(context);
        mArgbEvaluator = new ArgbEvaluator();
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        int color = (int) mArgbEvaluator.evaluate(leavePercent, mSelectedColor, mNormalColor);
        setTextColor(color);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        int color = (int) mArgbEvaluator.evaluate(enterPercent, mNormalColor, mSelectedColor);
        setTextColor(color);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        //do nothing
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        //do nothing
    }


}
