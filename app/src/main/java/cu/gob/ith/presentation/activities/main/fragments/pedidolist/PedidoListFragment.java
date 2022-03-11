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
import cu.gob.ith.presentation.activities.main.fragments.pedidolist.viewmodel.PedidoListFragmentViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;


public class PedidoListFragment extends Fragment {

    private MainActivityViewModel mainActivityViewModel;
    private PedidoListFragmentViewModel viewModel;
    private FragmentPedidoListBinding uiBind;
    private ManageProductListUtil manageProductListUtil;
    private ProductosAdapter productosAdapter;

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
        viewModel.getTotalToPay().observe(getViewLifecycleOwner(),
                priceToPay -> {
                    uiBind.totalToPayTV.setText("" + priceToPay);
                    Log.e("total a pagar", "$" + priceToPay);
                });
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
        viewModel = new ViewModelProvider(this).get(PedidoListFragmentViewModel.class);
    }

    private void loadContent() {
        Log.e("selected", "loadContent: " + mainActivityViewModel.getProductosParaPedidosList());
        if (productosAdapter == null)
            productosAdapter = new ProductosAdapter(
                    mainActivityViewModel.getProductosParaPedidosList(), manageProductListUtil);

        uiBind.setAdapter(productosAdapter);
        viewModel.setTotalToPay(productosAdapter.totalPrice());
    }

    private void initManageProductListUtil() {
        manageProductListUtil = new ManageProductListUtil() {
            @Override
            public boolean updateProduct(Producto producto) {
                Log.e("update", "update " + viewModel.getTotalToPay().getValue() + " - " + producto.getCantProducto());

                List<Producto> productoList = mainActivityViewModel.getProductosParaPedidosList();

                boolean result = false;
                Double priceTotal = 0.0;
                for (int i = 0; i < productoList.size(); i++
                ) {
                    if (producto.getReferencia().equals(productoList.get(i).getReferencia())) {
                        productoList.get(i).setCantProducto(producto.getCantProducto());

                        if (productoList.get(i).getCantProducto() == 0.0) {
                            deleteProduct(producto);
                            return true;
                        }

                        result = true;
                    }

                        priceTotal += productoList.get(i).getCantProducto() *
                                Double.parseDouble(productoList.get(i).getPv());
                }

                viewModel.setTotalToPay(priceTotal);

                return result;
            }

            @Override
            public void addProduct(Producto producto) {
                Log.e("Add", "ADD");
                mainActivityViewModel.getProductosParaPedidosList().add(producto);
                viewModel.setTotalToPay(viewModel.getTotalToPay().getValue() +
                        (producto.getCantProducto() * Double.parseDouble(producto.getPv())));
            }

            @Override
            public boolean deleteProduct(Producto producto) {
                Log.e("delete", "delete");

                List<Producto> productoList = mainActivityViewModel.getProductosParaPedidosList();

                for (Producto productoElement : productoList
                ) {
                    if (producto.getReferencia().equals(productoElement.getReferencia())) {
                        productoList.remove(productoElement);
                        loadContent();
                        viewModel.setTotalToPay(viewModel.getTotalToPay().getValue() -
                                (producto.getCantProducto() * Double.parseDouble(producto.getPv())));
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