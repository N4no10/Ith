package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc4;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cu.gob.ith.R;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc4.viewmodel.PedidosProcesadosViewModel;

public class PedidosProcesadosFragment extends Fragment {

    private PedidosProcesadosViewModel mViewModel;

    public static PedidosProcesadosFragment newInstance() {
        return new PedidosProcesadosFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_pedidos_procesados, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(PedidosProcesadosViewModel.class);
        // TODO: Use the ViewModel
    }

}