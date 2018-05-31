package xyz.ibat.indicator.simple;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import java.util.List;
import xyz.ibat.indicator.base.CommonContainer;
import xyz.ibat.indicator.base.IPagerIndicator;
import xyz.ibat.indicator.base.IPagerTitle;
import xyz.ibat.indicator.base.IndicatorParameter;
import xyz.ibat.indicator.utils.IndicatorHelper;


/**
 * @author DongJr
 *
 * @date 2018/5/28.
 */
public class SimpleContainer extends CommonContainer {

    private List<String> mTitles;

    public SimpleContainer(@NonNull Context context) {
        super(context);
    }

    public SimpleContainer(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public SimpleContainer(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setTitles(List<String> titles){
        mTitles = titles;
    }

    @Override
    public IPagerTitle getTitleView(Context context, int index) {
        SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
        int padding = IndicatorHelper.dip2px(context, 16);
        simplePagerTitleView.setPadding(padding,0,padding,0);
        simplePagerTitleView.setNormalColor(Color.LTGRAY);
        simplePagerTitleView.setSelectedColor(Color.BLACK);
        simplePagerTitleView.setText(mTitles.get(index));
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        return new SimpleLinearIndicatorView(context, provideIndicatorParameter());
    }

    protected IndicatorParameter provideIndicatorParameter(){
        return new IndicatorParameter.Builder()
                .withIndicatorHeight(IndicatorHelper.dip2px(getContext(), 3))
                .withIndicatorColor(Color.BLUE)
                .withLRPadding(IndicatorHelper.dip2px(getContext(), 20))
                .withStartInterpolator(new AccelerateDecelerateInterpolator())
                .withEndInterpolator(new DecelerateInterpolator())
                .build();
    }
}
