package xyz.ibat.indicator.base;

/**
 * @author DongJr
 *
 * @date 2018/5/25.
 */
public interface IPagerTitle {

    /**
     * 被选中
     */
    void onSelected(int index, int totalCount);

    /**
     * 未被选中
     */
    void onDeselected(int index, int totalCount);


}
