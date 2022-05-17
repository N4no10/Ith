package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc5;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.datepicker.MaterialDatePicker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cu.gob.ith.R;
import cu.gob.ith.common.Util;
import cu.gob.ith.databinding.FragmentPedidosCanceladosBinding;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.OnClickDelegateRV;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1.recyclerview.PedidosAdapter;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1.viewmodel.PedidosMesViewModel;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc5.viewmodel.PedidosCanceladosViewModel;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.viewmodel.MisPedidosViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

@AndroidEntryPoint
public class PedidosCanceladosFragment extends Fragment {

    private FragmentPedidosCanceladosBinding uiBind;
    private PedidosCanceladosViewModel mViewModel;
    private MainActivityViewModel mainActivityViewModel;
    private MisPedidosViewModel misPedidosViewModel;
    private PedidosAdapter pedidosAdapter;
    private OnClickDelegateRV onClickDelegateRV;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_pedidos_cancelados, container, false);
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewModel();
        initOnClickInViewHolderItem();
        observers();
    }

    private void updateAdapter(List<DatosPedido> datosPedidoList) {
        if (pedidosAdapter == null)
            pedidosAdapter = new PedidosAdapter(datosPedidoList, onClickDelegateRV);
        else
            pedidosAdapter.loadList(datosPedidoList);

        uiBind.listPedidosRV.setAdapter(pedidosAdapter);
    }

    private void observers() {
        mainActivityViewModel.isColapsedMainContent().observe(getViewLifecycleOwner(), collapsedMainContent -> {
            Log.e("ColapsedMenu", "ColapsedMenu " + collapsedMainContent);
            if (!collapsedMainContent)
                loadContent();
        });
    }

    private void loadContent() {
        mViewModel.addCompositeDisposable(
                mViewModel.getListPedidosCancelados()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                datosPedidos -> {
                                    Log.e("onNext", "next" + datosPedidos.size());
                                    updateAdapter(datosPedidos);
                                }
                                ,
                                e -> Log.e("Error", "error cargando pedidos " + e.getMessage())
                        ));
    }

    private void initOnClickInViewHolderItem() {
        onClickDelegateRV = id -> {
            Bundle bundle = new Bundle();
            bundle.putString("pedidoId", id);
            Navigation.findNavController(requireParentFragment().requireParentFragment().requireView())
                    .navigate(R.id.to_detallesPedidoFragment, bundle);
            Log.e("TAG", "onClick: RV: " + id);
        };
    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(PedidosCanceladosViewModel.class);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        misPedidosViewModel = new ViewModelProvider(requireParentFragment().requireParentFragment())
                .get(MisPedidosViewModel.class);
    }

}