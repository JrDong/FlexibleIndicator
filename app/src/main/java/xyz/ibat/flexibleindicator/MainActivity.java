package xyz.ibat.flexibleindicator;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import xyz.ibat.indicator.TabSelectedListener;
import xyz.ibat.indicator.base.CommonContainer;
import xyz.ibat.indicator.base.IPagerTitle;
import xyz.ibat.indicator.base.IndicatorParameter;
import xyz.ibat.indicator.base.FlexibleIndicator;
import xyz.ibat.indicator.simple.SimpleContainer;
import xyz.ibat.indicator.simple.SimplePagerTitleView;
import xyz.ibat.indicator.titles.ColorChangeTitleView;
import xyz.ibat.indicator.titles.ScaleAndColorTitleView;
import xyz.ibat.indicator.titles.ScaleChangeTitleView;
import xyz.ibat.indicator.utils.IndicatorHelper;
import xyz.ibat.indicator.utils.T;

public class MainActivity extends AppCompatActivity {

    private FlexibleIndicator mFlexibleIndicator;
    private FlexibleIndicator mFlexibleIndicator1;
    private FlexibleIndicator mFlexibleIndicator2;
    private FlexibleIndicator mFlexibleIndicator3;
    private FlexibleIndicator mFlexibleIndicator4;
    private ViewPager mViewPager;

    private static final String[] CHANNELS = new String[]{"JANUARY", "FEBRUARY", "MARCH", "APRIL", "MAY", "JUNE", "JULY", "AUGUST", "SEPTEMBER", "OCTOBER", "NOVEMBER" ,"DECEMBER"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private IndicatorAdapter mAdapter = new IndicatorAdapter(mDataList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFlexibleIndicator = (FlexibleIndicator) findViewById(R.id.kw_indicator);
        mFlexibleIndicator1 = (FlexibleIndicator) findViewById(R.id.kw_indicator1);
        mFlexibleIndicator2 = (FlexibleIndicator) findViewById(R.id.kw_indicator2);
        mFlexibleIndicator3 = (FlexibleIndicator) findViewById(R.id.kw_indicator3);
        mFlexibleIndicator4 = (FlexibleIndicator) findViewById(R.id.kw_indicator4);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mAdapter);
        initFirstIndicator();
        initSecondIndicator();
        initThirdIndicator();
        initFourthIndicator();
        initFifthIndicator();
    }

    private void initFirstIndicator() {
        SimpleContainer commonContainer = new SimpleContainer(this){
            @Override
            public IPagerTitle getTitleView(Context context, int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleChangeTitleView(context);
                int padding = IndicatorHelper.dip2px(getContext(), 16);
                simplePagerTitleView.setPadding(padding, 0, padding, 0);
                simplePagerTitleView.setNormalColor(Color.LTGRAY);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
                simplePagerTitleView.setText(mDataList.get(index));
                return simplePagerTitleView;
            }

            @Override
            protected IndicatorParameter provideIndicatorParameter() {
                return new IndicatorParameter.Builder()
                        .withIndicatorHeight(IndicatorHelper.dip2px(getContext(), 3))
                        .withIndicatorColor(Color.GREEN)
                        .withLRPadding(IndicatorHelper.dip2px(getContext(), 20))
                        .withGravity(Gravity.TOP)
                        .withStartInterpolator(new AccelerateDecelerateInterpolator())
                        .withEndInterpolator(new DecelerateInterpolator())
                        .build();
            }
        };
        commonContainer.setTitles(mDataList);
        commonContainer.setTabMode(CommonContainer.MODE_SCROLLABLE);
        commonContainer.setTransform(true);
        mFlexibleIndicator.setBackgroundColor(Color.parseColor("#455a64"));
        mFlexibleIndicator.setContainer(commonContainer);
        mFlexibleIndicator.bind(mViewPager);
    }



    private void initSecondIndicator() {
        SimpleContainer commonContainer = new SimpleContainer(this){
            @Override
            protected IndicatorParameter provideIndicatorParameter() {
                return new IndicatorParameter.Builder()
                        .withIndicatorHeight(IndicatorHelper.dip2px(getContext(), 30))
                        .withIndicatorColor(Color.GRAY)
                        .withLRPadding(IndicatorHelper.dip2px(getContext(), 5))
                        .withRadius(IndicatorHelper.dip2px(getContext(), 20))
                        .withGravity(Gravity.CENTER)
                        .build();
            }
        };
        commonContainer.setTitles(mDataList);
        commonContainer.setTabMode(CommonContainer.MODE_SCROLLABLE);
        mFlexibleIndicator1.setBackgroundColor(Color.parseColor("#d43d3d"));
        mFlexibleIndicator1.setContainer(commonContainer);
        mFlexibleIndicator1.bind(mViewPager);
    }

    private void initThirdIndicator() {
        SimpleContainer simpleContainer = new SimpleContainer(this){
            @Override
            public IPagerTitle getTitleView(Context context, int index) {
                SimplePagerTitleView simplePagerTitleView = new ColorChangeTitleView(context);
                int padding = IndicatorHelper.dip2px(getContext(), 16);
                simplePagerTitleView.setPadding(padding, 0, padding, 0);
                simplePagerTitleView.setNormalColor(Color.LTGRAY);
                simplePagerTitleView.setSelectedColor(Color.BLACK);
                simplePagerTitleView.setText(mDataList.get(index));
                return simplePagerTitleView;
            }
        };
        simpleContainer.setTitles(mDataList);
        simpleContainer.setTabMode(CommonContainer.MODE_SCROLLABLE);
        simpleContainer.setTransform(true);
        mFlexibleIndicator2.setOnTabSelectedListener(new TabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                T.show(MainActivity.this, " onTabSelected " + position);
            }

            @Override
            public void onTabUnselected(int position) {
                T.show(MainActivity.this, " onTabUnselected " + position);
            }

            @Override
            public void onTabReselected(int position) {
                T.show(MainActivity.this, " onTabReselected " + position);
            }
        });
        mFlexibleIndicator2.setBackgroundColor(Color.parseColor("#00c853"));
        mFlexibleIndicator2.setContainer(simpleContainer);
        mFlexibleIndicator2.bind(mViewPager);
    }

    private void initFourthIndicator(){
        SimpleContainer commonContainer = new SimpleContainer(this){
            @Override
            public IPagerTitle getTitleView(Context context, int index) {
                SimplePagerTitleView simplePagerTitleView = new ScaleAndColorTitleView(context);
                int padding = IndicatorHelper.dip2px(getContext(), 16);
                simplePagerTitleView.setPadding(padding, 0, padding, 0);
                simplePagerTitleView.setNormalColor(Color.parseColor("#616161"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#f57c00"));
                simplePagerTitleView.setText(mDataList.get(index));
                return simplePagerTitleView;
            }

            @Override
            protected IndicatorParameter provideIndicatorParameter() {
                return new IndicatorParameter.Builder()
                        .withIndicatorHeight(IndicatorHelper.dip2px(getContext(), 3))
                        .withIndicatorColor(Color.parseColor("#40c4ff"))
                        .withLRPadding(IndicatorHelper.dip2px(getContext(), 20))
                        .withGravity(Gravity.BOTTOM)
                        .withStartInterpolator(new AccelerateDecelerateInterpolator())
                        .withEndInterpolator(new DecelerateInterpolator())
                        .build();
            }
        };
        commonContainer.setTransform(true);
        commonContainer.setTitles(mDataList);
        commonContainer.setTabMode(CommonContainer.MODE_SCROLLABLE);
        mFlexibleIndicator3.setBackgroundColor(Color.parseColor("#e94220"));
        mFlexibleIndicator3.setContainer(commonContainer);
        mFlexibleIndicator3.bind(mViewPager);
    }

    private void initFifthIndicator() {
        SimpleContainer commonContainer = new SimpleContainer(this){
            @Override
            public IPagerTitle getTitleView(Context context, int index) {
                SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                int padding = IndicatorHelper.dip2px(getContext(), 16);
                simplePagerTitleView.setPadding(padding, 0, padding, 0);
                simplePagerTitleView.setNormalColor(Color.parseColor("#616161"));
                simplePagerTitleView.setSelectedColor(Color.parseColor("#f57c00"));
                simplePagerTitleView.setText(mDataList.get(index));
                return simplePagerTitleView;
            }

            @Override
            protected IndicatorParameter provideIndicatorParameter() {
                return new IndicatorParameter.Builder()
                        .withIndicatorHeight(IndicatorHelper.dip2px(getContext(), 3))
                        .withIndicatorColor(Color.parseColor("#40c4ff"))
                        .withGravity(Gravity.BOTTOM)
                        .withShowMode(IndicatorParameter.MODE_FIXED_TITLE)
                        .withStartInterpolator(new LinearInterpolator())
                        .withEndInterpolator(new LinearInterpolator())
                        .build();
            }
        };
        commonContainer.setTitles(mDataList);
        commonContainer.setTabMode(CommonContainer.MODE_SCROLLABLE);
        mFlexibleIndicator4.setBackgroundColor(Color.parseColor("#394120"));
        mFlexibleIndicator4.setContainer(commonContainer);
        mFlexibleIndicator4.bind(mViewPager);
    }

    private class IndicatorAdapter extends PagerAdapter {

        private List<String> mDataList;

        public IndicatorAdapter(List<String> dataList) {
            mDataList = dataList;
        }

        @Override
        public int getCount() {
            return mDataList == null ? 0 : mDataList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(container.getContext());
            textView.setText(mDataList.get(position));
            textView.setGravity(Gravity.CENTER);
            textView.setTextColor(Color.BLACK);
            textView.setTextSize(24);
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getItemPosition(Object object) {
            TextView textView = (TextView) object;
            String text = textView.getText().toString();
            int index = mDataList.indexOf(text);
            if (index >= 0) {
                return index;
            }
            return POSITION_NONE;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mDataList.get(position);
        }
    }
}
