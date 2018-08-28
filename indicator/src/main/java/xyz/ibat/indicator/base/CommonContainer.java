package xyz.ibat.indicator.base;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.IntDef;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.List;

import xyz.ibat.indicator.R;
import xyz.ibat.indicator.TabSelectedListener;
import xyz.ibat.indicator.model.LocationModel;

/**
 * @author DongJr
 *
 * @date 2018/5/25.
 */
public abstract class CommonContainer extends FrameLayout implements IPagerContainer {

    public static final int MODE_SCROLLABLE = 0;
    public static final int MODE_FIXED = 1;
    private int mMode = MODE_FIXED;
    private int mSelectedIndex;
    private int mLastIndex;
    private int mScrollState;
    private float mLastPositionOffset;
    private float mScrollRate = 0.5f;
    /**
     * 渐变色标题需要强制更新
     */
    private boolean mIsTransform;
    private List<LocationModel> mLocationDatas = new ArrayList<>();
    private SparseArray<Float> mLeavedPercents = new SparseArray<Float>();

    private IPagerIndicator mPagerIndicator;
    private PagerAdapter mAdapter;
    private ViewPager mViewPager;
    private LinearLayout mTitleContainer;
    private LinearLayout mIndicatorContainer;
    private HorizontalScrollView mScrollView;
    private TabSelectedListener mTabSelectedListener;

    public CommonContainer(@NonNull Context context) {
        super(context);
    }

    @IntDef(value = {MODE_SCROLLABLE, MODE_FIXED})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode {}

    public void setTabMode(@Mode int mode) {
        mMode = mode;
    }

    protected abstract IPagerTitle getTitleView(Context context, int index);

    protected abstract IPagerIndicator getIndicator(Context context);

    /**
     * MODE_FIXED模式可重写，默认为1
     */
    protected int getTitleWeight(Context context, int index){
        return 1;
    }

    public void setTransform(boolean isTransform){
        mIsTransform = isTransform;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mAdapter != null && mTitleContainer != null){
            buildLocationModel();
            if (mPagerIndicator != null){
                mPagerIndicator.onProvideLocation(mLocationDatas);
            }
        }
    }

    @Override
    public void setOnTabSelectedListener(TabSelectedListener listener) {
        mTabSelectedListener = listener;
    }

    @Override
    public void onAttachToIndicator() {
        removeAllViews();
        View root;
        if (mMode == MODE_SCROLLABLE){
            root = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout, this, true);
            mScrollView = (HorizontalScrollView) root.findViewById(R.id.scroll_view);
        } else {
            root = LayoutInflater.from(getContext()).inflate(R.layout.pager_navigator_layout_no_scroll, this, true);
        }
        mIndicatorContainer = (LinearLayout) root.findViewById(R.id.indicator_container);
        mTitleContainer = (LinearLayout)root.findViewById(R.id.title_container);
        initIndicatorAndTitle();
    }

    @Override
    public void onDetachFromIndicator() {
        removeAllViews();
    }

    @Override
    public void setViewPager(ViewPager viewPager) {
        mViewPager = viewPager;
        mAdapter = viewPager.getAdapter();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mPagerIndicator != null){
            mPagerIndicator.onPageScrolled(position, positionOffset, positionOffsetPixels);
        }
        onScrollViewScrolled(position, positionOffset);
        onTitleViewScrolled(position, positionOffset);
    }

    @Override
    public void onPageSelected(int position) {
        mLastIndex = position;
        mSelectedIndex = position;
        if (mPagerIndicator != null){
            mPagerIndicator.onPageSelected(position);
        }
        for (int i = 0; i < mAdapter.getCount(); i++){
            if (position == i){
                dispatchOnSelected(i);
            } else {
                dispatchOnDeselected(i);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {
        mScrollState = state;
        if (mPagerIndicator != null){
            mPagerIndicator.onPageScrollStateChanged(state);
        }
    }

    protected void setTitleClickListener(View view, final int i) {
        view.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (i == mSelectedIndex){
                    if (mTabSelectedListener != null){
                        mTabSelectedListener.onTabReselected(i);
                    }
                } else {
                    mViewPager.setCurrentItem(i, true);
                }
            }
        });
    }

    private void buildLocationModel() {
        mLocationDatas.clear();
        for (int i = 0 ; i < mAdapter.getCount(); i++){
            LocationModel locationModel = new LocationModel();
            View childAt = mTitleContainer.getChildAt(i);
            locationModel.left = childAt.getLeft();
            locationModel.top = childAt.getTop();
            locationModel.right = childAt.getRight();
            locationModel.bottom = childAt.getBottom();
            if (childAt instanceof IPagerTitle){
                locationModel.contentLeft = ((IPagerTitle) childAt).getContentLeft();
                locationModel.contentRight = ((IPagerTitle) childAt).getContentRight();
                locationModel.contentTop = ((IPagerTitle) childAt).getContentTop();
                locationModel.contentBottom = ((IPagerTitle) childAt).getContentBottom();
            }
            mLocationDatas.add(locationModel);
        }
    }

    private void initIndicatorAndTitle() {
        for (int i = 0; i < mAdapter.getCount(); i++) {
            IPagerTitle pagerTitle = getTitleView(getContext(), i);
            if (pagerTitle instanceof View) {
                View view = (View) pagerTitle;
                LinearLayout.LayoutParams lp;
                if (mMode == MODE_FIXED) {
                    lp = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.MATCH_PARENT);
                    lp.weight = getTitleWeight(getContext(), i);
                } else {
                    lp = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
                }
                mTitleContainer.addView(view, lp);
                setTitleClickListener(view, i);
            }
        }

        mPagerIndicator = getIndicator(getContext());
        if (mPagerIndicator instanceof View) {
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            mIndicatorContainer.addView((View) mPagerIndicator, lp);
        }
    }

    private void onScrollViewScrolled(int position, float positionOffset) {
        boolean positionAvailable = mLocationDatas.size() > 0 && position >= 0 && position < mLocationDatas.size();
        if (mScrollView != null && positionAvailable && positionOffset > 0) {
            int currentPosition = Math.min(mLocationDatas.size() - 1, position);
            int nextPosition = Math.min(mLocationDatas.size() - 1, position + 1);
            LocationModel current = mLocationDatas.get(currentPosition);
            LocationModel next = mLocationDatas.get(nextPosition);
            //到中间再滚动
            float scrollTo = current.horizontalCenter() - mScrollView.getWidth() * mScrollRate;
            float nextScrollTo = next.horizontalCenter() - mScrollView.getWidth() * mScrollRate;
            mScrollView.scrollTo((int) (scrollTo + (nextScrollTo - scrollTo) * positionOffset), 0);
        }
    }

    private void onTitleViewScrolled(int position, float positionOffset) {
        float currentPositionOffset = position + positionOffset;
        boolean leftToRight = currentPositionOffset >= mLastPositionOffset;
        if (mScrollState != ViewPager.SCROLL_STATE_IDLE){
            if (mLastPositionOffset == currentPositionOffset){
                return;
            }
            int nextPosition = position + 1;
            boolean normalDispatch = true;
            if (positionOffset == 0.0f) {
                if (leftToRight) {
                    nextPosition = position - 1;
                    normalDispatch = false;
                }
            }
            for (int i = 0; i < mAdapter.getCount(); i++) {
                if (i == position || i == nextPosition) {
                    continue;
                }
                Float leavedPercent = mLeavedPercents.get(i, 0.0f);
                if (leavedPercent != 1.0f) {
                    dispatchOnLeave(i, 1.0f, leftToRight ,false);
                }
            }
            if (normalDispatch) {
                if (leftToRight) {
                    dispatchOnLeave(position, positionOffset, true ,false);
                    dispatchOnEnter(nextPosition, positionOffset, true ,false);
                } else {
                    dispatchOnLeave(nextPosition, 1.0f - positionOffset, false, false);
                    dispatchOnEnter(position, 1.0f - positionOffset, false, false);
                }
            } else {
                dispatchOnLeave(nextPosition, 1.0f - positionOffset, true, false);
                dispatchOnEnter(position, 1.0f - positionOffset, true, false);
            }
        } else {
            for (int i = 0 ; i < mAdapter.getCount() ; i++){
                if (i == mSelectedIndex){
                    continue;
                }
                Float leavedPercent = mLeavedPercents.get(i, 0.0f);
                if (leavedPercent != 1.0f) {
                    dispatchOnLeave(i, 1.0f, leftToRight,true);
                }
            }
            dispatchOnEnter(mSelectedIndex, 1f, leftToRight, true);
            dispatchOnSelected(mSelectedIndex);
        }
        mLastPositionOffset = currentPositionOffset;
    }

    private void dispatchOnLeave(int position, float leavePercent, boolean leftToRight ,boolean force){
        if (mTitleContainer == null){
            return;
        }
        boolean dragingNearby = (position == mSelectedIndex - 1 || position == mSelectedIndex + 1) && mLeavedPercents.get(position, 0.0f) != 1.0f;
        if (mIsTransform || position == mLastIndex || mScrollState == ViewPager.SCROLL_STATE_DRAGGING || dragingNearby ||force) {
            View titleView = mTitleContainer.getChildAt(position);
            if (titleView instanceof IPagerTitle){
                IPagerTitle pagerTitle = (IPagerTitle) titleView;
                pagerTitle.onLeave(position, mAdapter.getCount(), leavePercent, leftToRight);
                mLeavedPercents.put(position, leavePercent);
            }
        }
    }

    private void dispatchOnEnter(int position, float enterPercent, boolean leftToRight, boolean force){
        if (mTitleContainer == null){
            return;
        }
        if (position == mSelectedIndex || mScrollState == ViewPager.SCROLL_STATE_DRAGGING ) {
            View titleView = mTitleContainer.getChildAt(position);
            if (titleView instanceof IPagerTitle){
                IPagerTitle pagerTitle = (IPagerTitle) titleView;
                pagerTitle.onEnter(position, mAdapter.getCount(), enterPercent, leftToRight);
                mLeavedPercents.put(position, 1.0f - enterPercent);
            }
        }
    }

    private void dispatchOnSelected(int index) {
        if (mTitleContainer == null){
            return;
        }
        View view = mTitleContainer.getChildAt(index);
        if (view instanceof IPagerTitle){
            IPagerTitle pagerTitle = (IPagerTitle) view;
            pagerTitle.onSelected(index, mAdapter.getCount());
        }
    }

    private void dispatchOnDeselected(int index) {
        if (mTitleContainer == null){
            return;
        }
        View view = mTitleContainer.getChildAt(index);
        if (view instanceof IPagerTitle){
            IPagerTitle pagerTitle = (IPagerTitle) view;
            pagerTitle.onDeselected(index, mAdapter.getCount());
        }
    }
}
