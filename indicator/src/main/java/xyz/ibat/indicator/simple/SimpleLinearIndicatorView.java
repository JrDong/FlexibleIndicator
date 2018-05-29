package xyz.ibat.indicator.simple;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.util.List;

import xyz.ibat.indicator.IndicatorHelper;
import xyz.ibat.indicator.base.IPagerIndicator;
import xyz.ibat.indicator.model.LocationModel;


/**
 * @author DongJr
 *
 * @date 2018/5/28.
 */
public class SimpleLinearIndicatorView extends View implements IPagerIndicator {

    private int mPadding;
    private int mIndicatorHeight = 10;

    private Paint mIndicatorPaint;
    private RectF mLineRect = new RectF();
    private List<LocationModel> mLocationDatas;

    private Interpolator mStartInterpolator = new AccelerateDecelerateInterpolator();
    private Interpolator mEndInterpolator = new DecelerateInterpolator();

    public SimpleLinearIndicatorView(Context context) {
        this(context, null);
    }

    public SimpleLinearIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SimpleLinearIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mIndicatorPaint = new Paint();
        mIndicatorPaint.setStyle(Paint.Style.FILL);
        mIndicatorPaint.setAntiAlias(true);
    }

    public void setIndicatorColor(int color){
        if (mIndicatorPaint != null){
            mIndicatorPaint.setColor(color);
        }
    }

    public void setIndicatorPadding(int padding){
        mPadding = padding;
    }

    public void setIndicatorHeight(int height){
        mIndicatorHeight = height;
    }

    public void setStartInterpolator(Interpolator interpolator){
        if (interpolator == null){
            mStartInterpolator = new LinearInterpolator();
        } else {
            mStartInterpolator = interpolator;
        }
    }

    public void setEndInterpolator(Interpolator interpolator){
        if (interpolator == null){
            mEndInterpolator = new LinearInterpolator();
        } else {
            mEndInterpolator = interpolator;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mLineRect, mIndicatorPaint);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //positionOffset 右滑从0-1，左滑从1-0
        if (mLocationDatas == null || mLocationDatas.isEmpty()){
            return;
        }
        LocationModel current = IndicatorHelper.getCorrectLocation(mLocationDatas, position);
        LocationModel next = IndicatorHelper.getCorrectLocation(mLocationDatas, position + 1);

        float leftX = current.left;
        float nextLeftX = next.left;
        float rightX = current.right;
        float nextRightX = next.right;
        Log.d("dong", " positionOffset " + positionOffset);
        mLineRect.left = leftX + (nextLeftX - leftX) * mStartInterpolator.getInterpolation(positionOffset) + mPadding;
        mLineRect.right = rightX + (nextRightX - rightX) * mEndInterpolator.getInterpolation(positionOffset) - mPadding;
        mLineRect.top = getHeight() - mIndicatorHeight;
        mLineRect.bottom = getHeight();
        Log.d("dong", " position " + position + " left " + mLineRect.left + " right " + mLineRect.right);
        invalidate();
    }

    @Override
    public void onPageSelected(int position) {
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onProvideLocation(List<LocationModel> locationModels) {
        mLocationDatas = locationModels;
    }
}
