package xyz.ibat.indicator.simple;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.view.View;

import java.util.List;

import xyz.ibat.indicator.IndicatorHelper;
import xyz.ibat.indicator.base.IPagerIndicator;
import xyz.ibat.indicator.base.IndicatorParameter;
import xyz.ibat.indicator.model.LocationModel;


/**
 * @author DongJr
 *
 * @date 2018/5/28.
 */
@SuppressLint("ViewConstructor")
public class SimpleLinearIndicatorView extends View implements IPagerIndicator {

    private Paint mIndicatorPaint;
    private RectF mLineRect = new RectF();
    private List<LocationModel> mLocationDatas;
    private IndicatorParameter mParameter;

    public SimpleLinearIndicatorView(Context context, @NonNull IndicatorParameter parameter) {
        super(context);
        this.mParameter = parameter;
        init();
    }

    private void init() {
        mIndicatorPaint = new Paint();
        mIndicatorPaint.setStyle(Paint.Style.FILL);
        mIndicatorPaint.setAntiAlias(true);
        mIndicatorPaint.setColor(mParameter.indicatorColor);
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

        mLineRect.left = leftX + (nextLeftX - leftX) * mParameter.startInterpolator.getInterpolation(positionOffset) + mParameter.lRPadding;
        mLineRect.right = rightX + (nextRightX - rightX) * mParameter.endInterpolator.getInterpolation(positionOffset) - mParameter.lRPadding;
        mLineRect.top = getHeight() - mParameter.indicatorHeight;
        mLineRect.bottom = getHeight();
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
