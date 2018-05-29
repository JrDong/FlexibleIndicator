# FlexibleIndicator
一个易扩展，可定制的ViewPager指示器  
标题和指示器与框架完全解耦，可以随意组合定制  
设计思想参考自https://github.com/hackware1993/MagicIndicator

## usage

        CommonContainer commonContainer = new CommonContainer(this) {
              @Override
              public IPagerTitle getTitleView(Context context, int index) {
                  SimplePagerTitleView simplePagerTitleView = new SimplePagerTitleView(context);
                  simplePagerTitleView.setPadding(16,0,16,0);
                  simplePagerTitleView.setNormalColor(Color.BLACK);
                  simplePagerTitleView.setSelectedColor(Color.YELLOW);
                  simplePagerTitleView.setText(mDataList.get(index));
                  return simplePagerTitleView;
              }

              @Override
              public IPagerIndicator getIndicator(Context context) {
                  CircleIndicatorView indicatorView = new CircleIndicatorView(context);
                  indicatorView.setIndicatorColor(Color.BLUE);
                  indicatorView.setIndicatorPadding(10);
                  indicatorView.setIndicatorHeight(40);
                  return indicatorView;
              }
          };
        commonContainer.setTabMode(CommonContainer.MODE_SCROLLABLE);
        mKwIndicatorCircle.setContainer(commonContainer);
        mKwIndicatorCircle.bind(mViewPager);  
        
 可以看出标题与指示器可以随意定制重写
      
      
        SimpleContainer simpleContainer = new SimpleContainer(this);
        simpleContainer.setTitles(mDataList);
        simpleContainer.setTabMode(CommonContainer.MODE_SCROLLABLE);
        mKwIndicator.setOnTabSelectedListener(new TabSelectedListener() {
            @Override
            public void onTabSelected(int position) {
                Toast.makeText(MainActivity.this, " onTabSelected " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabUnselected(int position) {
                Toast.makeText(MainActivity.this, " onTabUnselected " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onTabReselected(int position) {
                Toast.makeText(MainActivity.this, " onTabReselected " + position, Toast.LENGTH_SHORT).show();
            }
        });
        mKwIndicator.setContainer(simpleContainer);
        mKwIndicator.bind(mViewPager);
        
   同时也进行了简单封装，满足一般需求
