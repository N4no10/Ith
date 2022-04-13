package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc4;

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
import cu.gob.ith.databinding.FragmentPedidosProcesadosBinding;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc4.recyclerview.PedidoDespachadoAdapter;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc4.viewmodel.PedidosProcesadosViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@AndroidEntryPoint
public class PedidosProcesadosFragment extends Fragment {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private FragmentPedidosProcesadosBinding uiBind;
    private PedidosProcesadosViewModel mViewModel;

    public static PedidosProcesadosFragment newInstance() {
        return new PedidosProcesadosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_pedidos_procesados, container, false);
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
        mViewModel = new ViewModelProvider(this).get(PedidosProcesadosViewModel.class);
    }

    private void getPedidosDespachadosList() {
        compositeDisposable.add(
                mViewModel.getFilterListPedidosDespachadosUseCase().execute()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(datosPedidos ->
                                        uiBind.pedidosDespachadosRV.setAdapter(
                                                new PedidoDespachadoAdapter(datosPedidos)
                                        ),
                                throwable -> Log.e("PedidosDespachadosList", "getPedidosDespachadosList: " + throwable.getMessage())
                        )
        );
    }

    @Override
    public void onDestroyView() {
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
        super.onDestroyView();
    }
}