# FlexibleIndicator
一个易扩展，可定制的ViewPager指示器  
标题和指示器与框架完全解耦，可以随意组合定制  
设计思想参考自https://github.com/hackware1993/MagicIndicator

## usage

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
                mKwIndicator.setBackgroundColor(Color.parseColor("#455a64"));
                mKwIndicator.setContainer(commonContainer);
                mKwIndicator.bind(mViewPager);
        
 可以看出标题与指示器可以随意定制重写,同时也进行了简单封装，满足一般需求
