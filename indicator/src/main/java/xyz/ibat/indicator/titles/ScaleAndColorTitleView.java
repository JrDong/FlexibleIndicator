package xyz.ibat.indicator.titles;

import android.content.Context;
import xyz.ibat.indicator.utils.ArgbEvaluatorHolder;

/**
 * @author DongJr
 *
 * @date 2018/5/31.
 *
 * 大小和颜色渐变标题
 */
public class ScaleAndColorTitleView extends ScaleChangeTitleView{

    public ScaleAndColorTitleView(Context context) {
        super(context);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        super.onLeave(index, totalCount, leavePercent, leftToRight);
        int color = ArgbEvaluatorHolder.eval(leavePercent, mSelectedColor, mNormalColor);
        setTextColor(color);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        super.onEnter(index, totalCount, enterPercent, leftToRight);
        int color = ArgbEvaluatorHolder.eval(enterPercent, mNormalColor, mSelectedColor);
        setTextColor(color);
    }

}
