package cu.gob.ith.presentation.adapters;

import android.widget.TextView;

import androidx.annotation.FontRes;
import androidx.appcompat.widget.SearchView;
import androidx.core.content.res.ResourcesCompat;
import androidx.databinding.BindingAdapter;

public class MyBindingAdapter {

    @BindingAdapter("bindadapter:fontFamily")
    public static void setFontSearchView(SearchView searchView, @FontRes int font){
        TextView tv = searchView.findViewById(androidx.appcompat.R.id.search_src_text);
        tv.setTextSize(14);
        tv.setTypeface(ResourcesCompat.getFont(searchView.getContext(),font));
    }

    @BindingAdapter("bindadapter:fontFamily")
    public static void setFontTextView(TextView textView, @FontRes int font){
       textView.setTypeface(ResourcesCompat.getFont(textView.getContext(),font));
    }

}
