package cu.gob.ith.presentation.activities.main.fragments.informe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentInformePedidoBinding;
import cu.gob.ith.presentation.activities.main.fragments.informe.recyclerview.ProductoInformePedidoAdapter;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InformePedidoFragment extends Fragment {

    private MainActivityViewModel mainActivityViewModel;
    private FragmentInformePedidoBinding uiBind;

    public InformePedidoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_informe_pedido, container, false);
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        initViewModels();
        setupViewComponents();
        initAdapter();
    }

    private void initViewModels() {
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }

    private void setupViewComponents() {
        mainActivityViewModel.setTitleToolBar(getString(R.string.informe_pedido_fragment));
        mainActivityViewModel.getProductosParaPedidosList().clear();
//        mainActivityViewModel.getShowMenuOrBack().setValue(true);
        uiBind.setDatosPedido(mainActivityViewModel.getInformePedido().getDatosPedido());
    }

    private void initAdapter() {
        ProductoInformePedidoAdapter adapter = new ProductoInformePedidoAdapter(
                mainActivityViewModel.getInformePedido().getDatosPedido().getProductoList());
        uiBind.productosListRV.setAdapter(adapter);
    }
}