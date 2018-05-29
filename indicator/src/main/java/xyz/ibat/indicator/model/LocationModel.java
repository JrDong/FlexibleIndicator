package xyz.ibat.indicator.model;

/**
 * @author DongJr
 *
 * @date 2018/5/28.
 */
public class LocationModel {

    public int left;
    public int right;
    public int top;
    public int bottom;

    public int getWidth(){
        return right - left;
    }

    public int getHeight(){
        return bottom - top;
    }

    public int horizontalCenter() {
        return left + getWidth() / 2;
    }

    public int verticalCenter(){
        return top + getHeight() / 2;
    }


}
