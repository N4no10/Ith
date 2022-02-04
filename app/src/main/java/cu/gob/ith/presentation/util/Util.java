package cu.gob.ith.presentation.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;

public class Util {

    public static float widthDp(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }

    public static float heightDp(Context context){
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }


}
