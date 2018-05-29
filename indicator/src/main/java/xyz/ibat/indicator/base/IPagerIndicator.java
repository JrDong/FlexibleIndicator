package xyz.ibat.indicator.base;

import java.util.List;

import xyz.ibat.indicator.model.LocationModel;


/**
 * @author DongJr
 *
 * @date 2018/5/25.
 */
public interface IPagerIndicator {

    void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    void onPageSelected(int position);

    void onPageScrollStateChanged(int state);

    void onProvideLocation(List<LocationModel> locationModels);

}
