package xyz.ibat.indicator.titles;
import android.content.Context;
import xyz.ibat.indicator.simple.SimplePagerTitleView;

/**
 * @author DongJr
 *
 * @date 2018/5/31.
 *
 * 大小渐变标题
 */
public class ScaleChangeTitleView extends SimplePagerTitleView{

    private float mMaxScale = 1.1f;

    public ScaleChangeTitleView(Context context) {
        super(context);
    }

    @Override
    public void onEnter(int index, int totalCount, float enterPercent, boolean leftToRight) {
        setScaleX(mMaxScale + (mMaxScale - 1.0f) * enterPercent);
        setScaleY(mMaxScale + (mMaxScale - 1.0f) * enterPercent);
    }

    @Override
    public void onLeave(int index, int totalCount, float leavePercent, boolean leftToRight) {
        setScaleX(mMaxScale + (1.0f - mMaxScale) * leavePercent);
        setScaleY(mMaxScale + (1.0f - mMaxScale) * leavePercent);
    }

    public float getMaxScale() {
        return mMaxScale;
    }

    public void setMaxScale(float minScale) {
        mMaxScale = minScale;
    }

}
