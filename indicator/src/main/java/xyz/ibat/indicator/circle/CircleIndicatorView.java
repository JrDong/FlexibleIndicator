package xyz.ibat.indicator.circle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
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
public class CircleIndicatorView extends View implements IPagerIndicator {

    private int mLRPadding;
    private int mIndicatorHeight;

    private Paint mIndicatorPaint;
    private RectF mRoundRect = new RectF();
    private List<LocationModel> mLocationDatas;

    private Interpolator mStartInterpolator = new AccelerateDecelerateInterpolator();
    private Interpolator mEndInterpolator = new DecelerateInterpolator();

    public CircleIndicatorView(Context context) {
        this(context, null);
    }

    public CircleIndicatorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleIndicatorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setIndicatorColor(int color){
        if (mIndicatorPaint != null){
            mIndicatorPaint.setColor(color);
        }
    }

    public void setIndicatorPadding(int padding){
        mLRPadding = padding;
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

    private void init() {
        mIndicatorPaint = new Paint();
        mIndicatorPaint.setStyle(Paint.Style.FILL);
        mIndicatorPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float radius = getHeight() / 2;
        canvas.drawRoundRect(mRoundRect, radius, radius, mIndicatorPaint);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        if (mLocationDatas == null || mLocationDatas.isEmpty()){
            return;
        }
        LocationModel current = IndicatorHelper.getCorrectLocation(mLocationDatas, position);
        LocationModel next = IndicatorHelper.getCorrectLocation(mLocationDatas, position + 1);

        float leftX = current.left;
        float nextLeftX = next.left;
        float rightX = current.right;
        float nextRightX = next.right;
        mRoundRect.left = leftX + (nextLeftX - leftX) * mStartInterpolator.getInterpolation(positionOffset) + mLRPadding;
        mRoundRect.right = rightX + (nextRightX - rightX) * mEndInterpolator.getInterpolation(positionOffset) - mLRPadding;
        mRoundRect.top = (getHeight() - mIndicatorHeight) / 2;
        mRoundRect.bottom = getHeight() - (getHeight() - mIndicatorHeight) / 2;
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
