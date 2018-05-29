package xyz.ibat.indicator.simple;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

import java.util.List;

import xyz.ibat.indicator.base.CommonContainer;
import xyz.ibat.indicator.base.IPagerIndicator;
import xyz.ibat.indicator.base.IPagerTitle;


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
        simplePagerTitleView.setPadding(16,0,16,0);
        simplePagerTitleView.setNormalColor(Color.BLACK);
        simplePagerTitleView.setSelectedColor(Color.YELLOW);
        simplePagerTitleView.setText(mTitles.get(index));
        return simplePagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        SimpleLinearIndicatorView indicatorView = new SimpleLinearIndicatorView(context);
        indicatorView.setIndicatorColor(Color.BLUE);
        indicatorView.setIndicatorPadding(20);
        indicatorView.setIndicatorHeight(6);
        return indicatorView;
    }
}
