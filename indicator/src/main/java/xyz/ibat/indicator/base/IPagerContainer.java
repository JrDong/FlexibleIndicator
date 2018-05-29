package xyz.ibat.indicator.base;

import android.support.v4.view.ViewPager;

import xyz.ibat.indicator.TabSelectedListener;


/**
 * @author DongJr
 *
 * @date 2018/5/25.
 */
public interface IPagerContainer {

    void setOnTabSelectedListener(TabSelectedListener listener);

    void setViewPager(ViewPager pagerAdapter);

    void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    void onPageSelected(int position);

    void onPageScrollStateChanged(int state);

    void onDetachFromIndicator();

    void onAttachToIndicator();

}
