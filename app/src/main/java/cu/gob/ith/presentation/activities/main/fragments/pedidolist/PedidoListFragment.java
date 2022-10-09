package cu.gob.ith.presentation.activities.main.fragments.pedidolist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentPedidoListBinding;
import cu.gob.ith.domain.model.Pedido;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos.ManageProductListUtil;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos.ProductosAdapter;
import cu.gob.ith.presentation.activities.main.fragments.pedidolist.viewmodel.PedidoListFragmentViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@AndroidEntryPoint
public class PedidoListFragment extends Fragment {

    private MainActivityViewModel mainActivityViewModel;
    private PedidoListFragmentViewModel viewModel;
    private FragmentPedidoListBinding uiBind;
    private ManageProductListUtil manageProductListUtil;
    private ProductosAdapter productosAdapter;
    private List<Pedido> pedidoList = new ArrayList<>();
    private final CompositeDisposable disposables = new CompositeDisposable();

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
                    BigDecimal bd = new BigDecimal(priceToPay).setScale(2, RoundingMode.HALF_UP);
                    float finalValue = bd.floatValue();
                    uiBind.totalToPayTV.setText("" + finalValue);
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
        uiBind.buttonSendList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                uiBind.buttonSendList.startAnimation();
                if (!pedidoList.isEmpty())
                    pedidoList.clear();
                for (Producto producto : mainActivityViewModel.getProductosParaPedidosList()) {
                    BigDecimal bd = BigDecimal.valueOf(producto.getPv() * producto.getCantProducto())
                            .setScale(2, RoundingMode.HALF_UP);
                    float finalValue = bd.floatValue();
                    pedidoList.add(new Pedido(producto.getReferencia(),
                            producto.getPv(),
                            producto.getCantProducto(),
                            finalValue));
                }
                disposables.add(
                        viewModel.getRequestOrderUseCase().execute(pedidoList)
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(informePedido -> {
                                            Snackbar.make(uiBind.getRoot(),
                                                    getString(R.string.success_solicitud_pedido),
                                                    Snackbar.LENGTH_LONG).show();
                                            mainActivityViewModel.setInformePedido(informePedido);
                                            Navigation.findNavController(uiBind.getRoot()).navigate(R.id.to_informePedidoFragment);
                                        },
                                        e -> {
                                            Toast.makeText(requireContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                            uiBind.buttonSendList.revertAnimation();
                                        }, () -> uiBind.buttonSendList.revertAnimation())
                );
            }
        });
    }

    private void currentToolBar() {
        mainActivityViewModel.setTitleToolBar(getString(R.string.list_producto));
        mainActivityViewModel.setShowMenuOrBack(false);
        mainActivityViewModel.showIconGoToNewPedido(requireArguments().getInt("action", 0));
    }

    public void initViewModel() {
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        viewModel = new ViewModelProvider(this).get(PedidoListFragmentViewModel.class);
    }

    private void loadContent() {
        if (productosAdapter == null) {
            if (requireArguments().getInt("pedidoId", 0) > 0) {
                viewModel.addCompositeDisposable(
                        viewModel.getGetPedidoByIdUseCase(requireArguments().getInt("pedidoId"))
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                        informePedido -> {
                                            mainActivityViewModel.setProductosParaPedidosList(
                                                    informePedido.getDatosPedido().getProductoList());
                                            visibilityButtonSolicitarPedido();
                                            productosAdapter = new ProductosAdapter(
                                                    mainActivityViewModel.getProductosParaPedidosList(), manageProductListUtil);
                                            uiBind.setAdapter(productosAdapter);
                                            viewModel.setTotalToPay(productosAdapter.totalPrice());
                                        },
                                        throwable -> {
                                            uiBind.buttonSendList.setVisibility(
                                                    mainActivityViewModel.getProductosParaPedidosList()
                                                            .isEmpty() ? View.GONE : View.VISIBLE);
                                            Log.e("Error", "error " + throwable.getMessage());
                                        })
                );
            } else {
                visibilityButtonSolicitarPedido();
                productosAdapter = new ProductosAdapter(
                        mainActivityViewModel.getProductosParaPedidosList(), manageProductListUtil);
                uiBind.setAdapter(productosAdapter);
                viewModel.setTotalToPay(productosAdapter.totalPrice());
            }
        }

    }

    private void visibilityButtonSolicitarPedido() {
        uiBind.buttonSendList.setVisibility(
                mainActivityViewModel.getProductosParaPedidosList()
                        .isEmpty() ? View.GONE : View.VISIBLE);
    }

    private void initManageProductListUtil() {
        manageProductListUtil = new ManageProductListUtil() {
            @Override
            public boolean updateProduct(Producto producto) {
                Log.e("update", "update " + viewModel.getTotalToPay().getValue() + " - " + producto.getCantProducto());

                List<Producto> productoList = mainActivityViewModel.getProductosParaPedidosList();

                boolean result = false;
                float priceTotal = 0;
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
                            productoList.get(i).getPv();
                }

                viewModel.setTotalToPay(priceTotal);

                return result;
            }

            @Override
            public void addProduct(Producto producto) {
                Log.e("Add", "ADD");
                mainActivityViewModel.getProductosParaPedidosList().add(producto);
                viewModel.setTotalToPay(viewModel.getTotalToPay().getValue() +
                        (producto.getCantProducto() * producto.getPv()));
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
                                (producto.getCantProducto() * producto.getPv()));
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

            @Override
            public void setSearchText(String textSearch) {

            }
        };

    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (requireArguments().getInt("action", 0) > 0)
            mainActivityViewModel.showIconGoToNewPedido(0);

        if (!disposables.isDisposed()) {
            disposables.dispose();
        }
    }
}