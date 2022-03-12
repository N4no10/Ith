package cu.gob.ith.presentation.activities.main.ui.viewmodel;

import android.content.Context;

import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.InformePedido;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.model.ItemMenuNavView;
import dagger.hilt.android.lifecycle.HiltViewModel;
import dagger.hilt.android.qualifiers.ApplicationContext;

@HiltViewModel
public class MainActivityViewModel extends ViewModel {

    public MutableLiveData<String> titleToolBar = new MutableLiveData<>();
    private final MutableLiveData<Boolean> colapsedMainContent = new MutableLiveData<>();
    private final MutableLiveData<Boolean> showMenuOrBack = new MutableLiveData<>();
    private final List<ItemMenuNavView> itemMenuNavViewList = new ArrayList<>();
    private final List<Producto> productosParaPedidosList = new ArrayList<>();
    private final MutableLiveData<Integer> cantProductos = new MutableLiveData<>();
    private InformePedido informePedido;

    @Inject
    public MainActivityViewModel(@ApplicationContext Context context) {
        initMenuItems(context);
        colapsedMainContent.setValue(false);
    }

    public LiveData<String> getTitleToolBar() {
        return titleToolBar;
    }

    public void setTitleToolBar(String titleToolBar) {
        this.titleToolBar.setValue(titleToolBar);
    }

    public List<ItemMenuNavView> getItemMenuNavViewList() {
        return itemMenuNavViewList;
    }

    public LiveData<Boolean> isColapsedMainContent() {
        return colapsedMainContent;
    }

    public void setColapsedMainContent(boolean colapsedMenu) {
        this.colapsedMainContent.setValue(colapsedMenu);
    }

    public List<Producto> getProductosParaPedidosList() {
        return productosParaPedidosList;
    }

    public MutableLiveData<Boolean> getShowMenuOrBack() {
        return showMenuOrBack;
    }

    public void setShowMenuOrBack(Boolean showMenuOrBack) {
        this.showMenuOrBack.setValue(showMenuOrBack);
    }

    private List<ItemMenuNavView> listSubMenuPedido(Context context) {
        return new ArrayList<ItemMenuNavView>() {{
            add(new ItemMenuNavView(context.getString(R.string.menu_nuevo_pedido),
                    ContextCompat.getDrawable(context, R.drawable.ic_pedido_en_linea)));
            add(new ItemMenuNavView(context.getString(R.string.menu_completar_pedido),
                    ContextCompat.getDrawable(context, R.drawable.ic_pedido_en_linea)));
            add(new ItemMenuNavView(context.getString(R.string.menu_historial_pedido),
                    ContextCompat.getDrawable(context, R.drawable.ic_pedido_en_linea)));
        }};
    }

    private void initMenuItems(Context context) {
        ItemMenuNavView inicio = new ItemMenuNavView(
                context.getString(R.string.menu_inicio),
                ContextCompat.getDrawable(context, R.drawable.ic_menu_home));
        inicio.setSelected(true);
        itemMenuNavViewList.add(inicio);
        itemMenuNavViewList.add(new ItemMenuNavView(
                context.getString(R.string.menu_pedido),
                null, listSubMenuPedido(context)));
        itemMenuNavViewList.add(new ItemMenuNavView(
                context.getString(R.string.menu_settings),
                ContextCompat.getDrawable(context, R.drawable.ic_settings_black_24dp)
        ));
    }

    public MutableLiveData<Integer> getCantProductos() {
        return cantProductos;
    }

    public InformePedido getInformePedido() {
        return informePedido;
    }

    public void setInformePedido(InformePedido informePedido) {
        this.informePedido = informePedido;
    }
}
