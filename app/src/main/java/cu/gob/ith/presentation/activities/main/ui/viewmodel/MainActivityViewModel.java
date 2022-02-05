package cu.gob.ith.presentation.activities.main.ui.viewmodel;

import android.content.Context;


import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.R;
import cu.gob.ith.presentation.model.ItemMenuNavView;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {

    private final List<ItemMenuNavView> itemMenuNavViewList = new ArrayList<>();


    @Inject
    public MainActivityViewModel(@ApplicationContext Context context) {
        itemMenuNavViewList.add(new ItemMenuNavView(
                context.getString(R.string.menu_pedido),
                ContextCompat.getDrawable(context,R.drawable.ic_pedido_en_linea)));
        itemMenuNavViewList.add(new ItemMenuNavView(
                context.getString(R.string.menu_settings),
                ContextCompat.getDrawable(context, R.drawable.ic_settings_black_24dp)
        ));
    }

    public List<ItemMenuNavView> getItemMenuNavViewList() {
        return itemMenuNavViewList;
    }
}
