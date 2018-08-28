package xyz.ibat.indicator.reddot;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import xyz.ibat.indicator.R;
import xyz.ibat.indicator.base.IPagerTitle;
import xyz.ibat.indicator.simple.SimpleContainer;


/**
 * @author DongJr
 *
 * @date 2018/8/27.
 *
 */
public class RedDotContainer extends SimpleContainer {

    private List<Integer> mIndexes = new ArrayList<>();

    public RedDotContainer(@NonNull Context context) {
        super(context);
    }

    public void setRedDotIndex(int index){
        if (!mIndexes.contains(index)){
            mIndexes.add(index);
        }
    }

    public void setRedDotIndexes(List<Integer> indexes){
        if (indexes != null){
            mIndexes = indexes;
        }
    }

    public void disappearIndex(int index){
        if (mIndexes.contains(index)){
            mIndexes.remove(Integer.valueOf(index));
        }
    }

    public void disappearAllIndexes(){
        mIndexes.clear();
    }


    @Override
    public IPagerTitle getTitleView(Context context, int index) {
        RedDotTitleView redDotTitleView = new RedDotTitleView(context);
        IPagerTitle titleView = super.getTitleView(context, index);
        redDotTitleView.setPagerTitleView(titleView);
        if (mIndexes.contains(index)){
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(R.drawable.red_point);
            redDotTitleView.setRedDotAnchor(new Random().nextInt(7));
            redDotTitleView.setRedDot(imageView);
        } else {
            redDotTitleView.setRedDot(null);
        }
        return redDotTitleView;
    }
}
