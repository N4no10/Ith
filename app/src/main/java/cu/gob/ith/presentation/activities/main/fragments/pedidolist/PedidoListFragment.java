package cu.gob.ith.presentation.activities.main.fragments.pedidolist;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentPedidoListBinding;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.categorias.CategoriasAdapter;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos.ManageProductListUtil;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos.ProductosAdapter;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;


public class PedidoListFragment extends Fragment {

    private MainActivityViewModel mainActivityViewModel;
    private FragmentPedidoListBinding uiBind;
    private ManageProductListUtil manageProductListUtil;

    public PedidoListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_pedido_list, container, false);
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        initViewModel();
        currentToolBar();
        initManageProductListUtil();
        loadContent();
        configSolicitarPedidoButton();
    }

    private void configSolicitarPedidoButton() {

    }

    private void currentToolBar() {
        mainActivityViewModel.setTitleToolBar("Lista de Pedidos");
        mainActivityViewModel.setShowMenuOrBack(false);

    }

    public void initViewModel() {
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }

    private void loadContent() {
        Log.e("selected", "loadContent: " + mainActivityViewModel.getProductosParaPedidosList());
        ProductosAdapter productosAdapter = new ProductosAdapter(mainActivityViewModel.getProductosParaPedidosList(), manageProductListUtil);
        uiBind.setAdapter(productosAdapter);

    }

    private void initManageProductListUtil() {
        manageProductListUtil = new ManageProductListUtil() {
            @Override
            public boolean updateProduct(Producto producto) {
                List<Producto> productoList = mainActivityViewModel.getProductosParaPedidosList();

                for (Producto productoElement : productoList
                ) {
                    if (producto.getReferencia().equals(productoElement.getReferencia()))
                        productoElement.setCantProducto(producto.getCantProducto());
                    return true;
                }
                return false;
            }

            @Override
            public void addProduct(Producto producto) {
                mainActivityViewModel.getProductosParaPedidosList().add(producto);
            }

            @Override
            public boolean deleteProduct(Producto producto) {
                List<Producto> productoList = mainActivityViewModel.getProductosParaPedidosList();

                for (Producto productoElement : productoList
                ) {
                    if (producto.getReferencia().equals(productoElement.getReferencia())) {
                        productoList.remove(productoElement);
                        loadContent();
                        return true;
                    }
                }
                return false;
            }

            @Override
            public void updateApiList(List<Producto> apiProductoList) {
                for (Producto producto : mainActivityViewModel.getProductosParaPedidosList()
                ) {
                    for (Producto productoElement : apiProductoList
                    ) {
                        if (productoElement.getReferencia().equals(producto.getReferencia())) {
                            productoElement.setCantProducto(producto.getCantProducto());
                        }
                    }
                }
            }
        };

    }


}