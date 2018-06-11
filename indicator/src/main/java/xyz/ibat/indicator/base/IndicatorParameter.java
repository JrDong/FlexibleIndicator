package xyz.ibat.indicator.base;

import android.support.annotation.IntDef;
import android.view.Gravity;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author DongJr
 *
 * @date 2018/5/30.
 */
public class IndicatorParameter {

    public int lRPadding;
    public int indicatorHeight;
    public int indicatorColor;
    public int radius;
    public int gravity;
    public int showMode;
    public Interpolator startInterpolator;
    public Interpolator endInterpolator;
    /**
     * 普通模式
     */
    public static final int MODE_NORMAL = 1;
    /**
     * 自适应标题
     */
    public static final int MODE_FIXED_TITLE = 2;

    @IntDef(value = {MODE_NORMAL, MODE_FIXED_TITLE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Mode{
    }

    public IndicatorParameter(Builder builder){
        this.lRPadding = builder.lRPadding;
        this.indicatorHeight = builder.indicatorHeight;
        this.indicatorColor = builder.indicatorColor;
        this.radius = builder.radius;
        this.gravity = builder.gravity;
        this.showMode = builder.showMode;
        this.startInterpolator = builder.startInterpolator == null ? new LinearInterpolator() : builder.startInterpolator;
        this.endInterpolator = builder.endInterpolator == null ? new LinearInterpolator() : builder.endInterpolator;
    }

    public static final class Builder{
        private int lRPadding;
        private int indicatorHeight;
        private int indicatorColor;
        private int radius;
        private int gravity = Gravity.BOTTOM;
        private int showMode = MODE_NORMAL;

        private Interpolator startInterpolator;
        private Interpolator endInterpolator;

        public Builder withLRPadding(int lRPadding){
            this.lRPadding = lRPadding;
            return this;
        }

        public Builder withIndicatorHeight(int indicatorHeight){
            this.indicatorHeight = indicatorHeight;
            return this;
        }

        public Builder withIndicatorColor(int indicatorColor){
            this.indicatorColor = indicatorColor;
            return this;
        }

        public Builder withRadius(int radius){
            this.radius = radius;
            return this;
        }

        /**
         * Gravity.TOP
         * Gravity.BOTTOM
         * Gravity.Center
         */
        public Builder withGravity(int gravity){
            this.gravity = gravity;
            return this;
        }

        public Builder withStartInterpolator(Interpolator startInterpolator){
            this.startInterpolator = startInterpolator;
            return this;
        }

        public Builder withEndInterpolator(Interpolator endInterpolator){
            this.endInterpolator = endInterpolator;
            return this;
        }

        public Builder withShowMode(@Mode int mode){
            this.showMode = mode;
            return this;
        }

        public IndicatorParameter build(){
            return new IndicatorParameter(this);
        }

    }


}
