package xyz.ibat.indicator.base;
import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import xyz.ibat.indicator.TabSelectedListener;


/**
 * @author DongJr
 *
 * @date 2018/5/25.
 */
public class FlexibleIndicator extends FrameLayout implements ViewPager.OnPageChangeListener{

    private IPagerContainer mPagerContainer;
    private TabSelectedListener mTabSelectedListener;
    private int mLastPosition = -1;
    private int mCurrentPosition = -1;

    public FlexibleIndicator(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public FlexibleIndicator(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bind(ViewPager viewPager){
        if (mPagerContainer == null){
            throw new IllegalArgumentException("Indicator does not have a container set!");
        }
        PagerAdapter adapter = viewPager.getAdapter();
        if (adapter == null){
            throw new IllegalArgumentException("ViewPager does not have a PagerAdapter set!");
        }
        viewPager.addOnPageChangeListener(this);
        mPagerContainer.setOnTabSelectedListener(mTabSelectedListener);
        mPagerContainer.setViewPager(viewPager);
        mPagerContainer.onAttachToIndicator();
        if (adapter.getCount() > 0){
            int curItem = viewPager.getCurrentItem();
            selectedTab(curItem);
        }
    }

    public IPagerContainer getContainer() {
        return mPagerContainer;
    }

    public void setContainer(IPagerContainer container) {
        if (mPagerContainer == container) {
            return;
        }
        if (mPagerContainer != null) {
            mPagerContainer.onDetachFromIndicator();
        }
        mPagerContainer = container;
        removeAllViews();
        if (mPagerContainer instanceof View) {
            FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            addView((View) mPagerContainer, lp);
        }
    }

    public void setOnTabSelectedListener(TabSelectedListener listener){
        this.mTabSelectedListener = listener;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mPagerContainer != null){
            mPagerContainer.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
    }

    @Override
    public void onPageSelected(int position) {
        mLastPosition = mCurrentPosition;
        selectedTab(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        if (mPagerContainer != null){
            mPagerContainer.onPageScrollStateChanged(state);
        }
    }

    private void selectedTab(int position){
        if (mLastPosition == position){
            if (mTabSelectedListener != null) {
                mTabSelectedListener.onTabReselected(position);
            }
        } else {
            mCurrentPosition = position;
            if (mPagerContainer != null){
                mPagerContainer.onPageSelected(position);
            }
            if (mTabSelectedListener != null){
                mTabSelectedListener.onTabSelected(position);
            }
            if (mLastPosition >= 0 && mTabSelectedListener != null){
                mTabSelectedListener.onTabUnselected(mLastPosition);
            }
        }
    }

}
