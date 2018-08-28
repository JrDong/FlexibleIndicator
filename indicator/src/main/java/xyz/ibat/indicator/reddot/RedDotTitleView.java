package xyz.ibat.indicator.reddot;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import xyz.ibat.indicator.base.IPagerTitle;


/**
 * @author DongJr
 *
 * @date 2018/8/27.
 *
 * 有红点的title
 */
public class RedDotTitleView extends RelativeLayout implements IPagerTitle {

    private IPagerTitle mPagerTitle;
    private View mRedDotView;
    private int mRedDotAnchor = RedDotAnchor.LEFT_TOP;

    public RedDotTitleView(Context context) {
        super(context);
    }

    public void setPagerTitleView(IPagerTitle pagerTitle){
        if (mPagerTitle == pagerTitle){
            return;
        }
        mPagerTitle = pagerTitle;
        removeAllViews();
        if (mPagerTitle instanceof View){
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            addView((View) mPagerTitle, lp);
        }
        if (mRedDotView != null) {
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            addView(mRedDotView, lp);
        }

    }

    public void setRedDot(View redDotView){
        if (mRedDotView == redDotView){
            return;
        }
        mRedDotView = redDotView;
        removeAllViews();
        if (mPagerTitle instanceof View){
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            addView((View) mPagerTitle, lp);
        }
        if (mRedDotView != null) {
            LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            addView(mRedDotView, lp);
        }
    }

    public void setRedDotAnchor(int redDotAnchor){
        mRedDotAnchor = redDotAnchor;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (mRedDotView == null){
            return;
        }
        int offsetX = 0;
        int offsetY = 0;
        int redDotViewSize = mRedDotView.getHeight();
        int titleHeight = getContentBottom() - getContentTop();
        switch (mRedDotAnchor){
            case RedDotAnchor.LEFT:
                offsetX = getContentLeft() - redDotViewSize;
                offsetY = getMeasuredHeight() / 2 - redDotViewSize / 2;
                break;
            case RedDotAnchor.TOP:
                offsetX = getMeasuredWidth() / 2 - redDotViewSize / 2;
                offsetY = getMeasuredHeight() / 2 - titleHeight / 2 - redDotViewSize;
                break;
            case RedDotAnchor.RIGHT:
                offsetX = getContentRight();
                offsetY = getMeasuredHeight() / 2 - redDotViewSize / 2;
                break;
            case RedDotAnchor.BOTTOM:
                offsetX = getMeasuredWidth() / 2 - redDotViewSize / 2;
                offsetY = getMeasuredHeight() / 2 + titleHeight / 2;
                break;
            case RedDotAnchor.LEFT_TOP:
                offsetX = getContentLeft() - redDotViewSize;
                offsetY = getMeasuredHeight() / 2 - titleHeight / 2 - redDotViewSize / 2;
                break;
            case RedDotAnchor.RIGHT_TOP:
                offsetX = getContentRight();
                offsetY = getMeasuredHeight() / 2 - titleHeight / 2 - redDotViewSize / 2;
                break;
            case RedDotAnchor.LEFT_BOTTOM:
                offsetX = getContentLeft() - redDotViewSize;
                offsetY = getMeasuredHeight() / 2 + titleHeight / 2 - redDotViewSize / 2;
                break;
            case RedDotAnchor.RIGHT_BOTTOM:
                offsetX = getContentRight();
                offsetY = getMeasuredHeight() / 2 + titleHeight / 2 - redDotViewSize / 2;
                break;
            default:
                break;
        }
        mRedDotView.offsetLeftAndRight(offsetX);
        mRedDotView.offsetTopAndBottom(offsetY);
    }

    @Override
    public void onSelected(int index, int totalCount) {
        if (mPagerTitle != null){
            mPagerTitle.onSelected(index, totalCount);
        }
    }

    @Override
    public void onDeselected(int index, int totalCount) {
        if (mPagerTitle != null){
            mPagerTitle.onDeselected(index, totalCount);
        }
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        if (mPagerTitle != null){
            mPagerTitle.onLeave(index, totalCount, leavePercent, leftToRight);
        }
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        if (mPagerTitle != null){
            mPagerTitle.onEnter(index, totalCount, enterPercent, leftToRight);
        }
    }

    @Override
    public int getContentLeft() {
        if (mPagerTitle != null){
            return mPagerTitle.getContentLeft();
        }
        return 0;
    }

    @Override
    public int getContentRight() {
        if (mPagerTitle != null){
            return mPagerTitle.getContentRight();
        }
        return 0;
    }

    @Override
    public int getContentTop() {
        if (mPagerTitle != null){
            return mPagerTitle.getContentTop();
        }
        return 0;
    }

    @Override
    public int getContentBottom() {
        if (mPagerTitle != null){
            return mPagerTitle.getContentBottom();
        }
        return 0;
    }

}
