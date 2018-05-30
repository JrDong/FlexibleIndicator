package xyz.ibat.indicator.base;

import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;

/**
 * @author DongJr
 *
 * @date 2018/5/30.
 */
public class IndicatorParameter {

    public int lRPadding;
    public int indicatorHeight;
    public int indicatorColor;
    public Interpolator startInterpolator;
    public Interpolator endInterpolator;

    public IndicatorParameter(Builder builder){
        this.lRPadding = builder.lRPadding;
        this.indicatorHeight = builder.indicatorHeight;
        this.indicatorColor = builder.indicatorColor;
        this.startInterpolator = builder.startInterpolator == null? new LinearInterpolator() : builder.startInterpolator;
        this.endInterpolator = builder.endInterpolator == null? new LinearInterpolator() : builder.endInterpolator;
    }

    public static final class Builder{
        private int lRPadding;
        private int indicatorHeight;
        private int indicatorColor;
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

        public Builder withStartInterpolator(Interpolator startInterpolator){
            this.startInterpolator = startInterpolator;
            return this;
        }

        public Builder withEndInterpolator(Interpolator endInterpolator){
            this.endInterpolator = endInterpolator;
            return this;
        }

        public IndicatorParameter build(){
            return new IndicatorParameter(this);
        }

    }


}
