package xyz.ibat.indicator.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * @author DongJr
 *
 * @date 2018/5/30.
 */
public class T {

    private T(){}

    public static void show(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

}
