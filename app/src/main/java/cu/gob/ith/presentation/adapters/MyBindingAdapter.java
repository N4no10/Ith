package cu.gob.ith.presentation.adapters;

import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.FontRes;
import androidx.annotation.IntegerRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BindingAdapter;

import cu.gob.ith.R;

public class MyBindingAdapter {

    @BindingAdapter("bindadapter:fontFamily")
    public static void setFontSearchView(SearchView searchView, @FontRes int font){
        TextView tv = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        tv.setTextSize(14);
        tv.setTypeface(ResourcesCompat.getFont(searchView.getContext(),font));
    }

}
