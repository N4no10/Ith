package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc3;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentPedidosEntregadosBinding;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc3.recyclerview.PedidoFacturadoAdapter;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc3.viewmodel.PedidosEntregadosViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@AndroidEntryPoint
public class PedidosEntregadosFragment extends Fragment {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private PedidosEntregadosViewModel mViewModel;
    private FragmentPedidosEntregadosBinding uiBind;

    public static PedidosEntregadosFragment newInstance() {
        return new PedidosEntregadosFragment();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_pedidos_entregados, container, false);
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        initViewModel();
        getPedidosDespachadosList();

    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(PedidosEntregadosViewModel.class);
    }

    private void getPedidosDespachadosList() {
        compositeDisposable.add(
                mViewModel.getFilterListPedidosFacturadosUseCase().execute()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(datosPedidos -> uiBind.pedidosFacturadosRV
                                        .setAdapter(new PedidoFacturadoAdapter(datosPedidos)),
                                throwable -> Log.e("PedidoFacturadoAdapter",
                                        "getPedidosDespachadosList: " + throwable.getMessage()))
        );
    }
}