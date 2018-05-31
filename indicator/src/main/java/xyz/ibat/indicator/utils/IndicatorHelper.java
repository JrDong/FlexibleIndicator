package xyz.ibat.indicator.utils;

import android.content.Context;

import java.util.List;

import xyz.ibat.indicator.model.LocationModel;


/**
 * @author DongJr
 *
 * @date 2018/5/28.
 */
public class IndicatorHelper {


    private IndicatorHelper(){}

    /**
     *获取正确的坐标，防止数组越界
     */
    public static LocationModel getCorrectLocation(List<LocationModel> locationModels, int index){
        if (index >= 0 && index < locationModels.size()){
            return locationModels.get(index);
        } else {
            if (index < 0){
                return locationModels.get(0);
            }else {
                return locationModels.get(locationModels.size() - 1);
            }
        }
    }


    public static int dip2px(Context context, double dpValue) {
        float density = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * density + 0.5);
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

}
