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
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

import xyz.ibat.indicator.TabSelectedListener;
import xyz.ibat.indicator.base.CommonContainer;
import xyz.ibat.indicator.base.IPagerTitle;
import xyz.ibat.indicator.base.IndicatorParameter;
import xyz.ibat.indicator.base.KwIndicator;
import xyz.ibat.indicator.simple.SimpleContainer;
import xyz.ibat.indicator.simple.SimplePagerTitleView;
import xyz.ibat.indicator.titles.GradientTitleView;
import xyz.ibat.indicator.utils.IndicatorHelper;
import xyz.ibat.indicator.utils.T;

public class MainActivity extends AppCompatActivity {

    private KwIndicator mKwIndicator;
    private KwIndicator mKwIndicatorCircle;
    private KwIndicator mKwIndicatorTop;
    private ViewPager mViewPager;

    private static final String[] CHANNELS = new String[]{"CUPCAKE", "DONUT", "ECLAIR", "GINGERBREAD", "HONEYCOMB", "ICE_CREAM_SANDWICH", "JELLY_BEAN", "KITKAT", "LOLLIPOP", "M", "NOUGAT"};
    private List<String> mDataList = Arrays.asList(CHANNELS);
    private IndicatorAdapter mAdapter = new IndicatorAdapter(mDataList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mKwIndicator = (KwIndicator) findViewById(R.id.kw_indicator);
        mKwIndicatorCircle = (KwIndicator) findViewById(R.id.kw_indicator_circle);
        mKwIndicatorTop = (KwIndicator) findViewById(R.id.kw_indicator_top);
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mAdapter);
        initFirstIndicator();
        initSecondIndicator();
        initThirdIndicator();
    }

    private void initFirstIndicator() {
        SimpleContainer simpleContainer = new SimpleContainer(this){
            @Override
            public IPagerTitle getTitleView(Context context, int index) {
                SimplePagerTitleView simplePagerTitleView = new GradientTitleView(context);
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
        mKwIndicator.setOnTabSelectedListener(new TabSelectedListener() {
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
        mKwIndicator.setContainer(simpleContainer);
        mKwIndicator.bind(mViewPager);
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
        mKwIndicatorCircle.setContainer(commonContainer);
        mKwIndicatorCircle.bind(mViewPager);
    }

    private void initThirdIndicator() {
        SimpleContainer commonContainer = new SimpleContainer(this){
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
        mKwIndicatorTop.setContainer(commonContainer);
        mKwIndicatorTop.bind(mViewPager);
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
