package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc2;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cu.gob.ith.R;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1.viewmodel.PedidosMesViewModel;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc2.viewmodel.PedidosSinProcesarViewModel;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.viewmodel.MisPedidosViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PedidosSinProcesarFragment extends Fragment {

    private MisPedidosViewModel misPedidosViewModel;
    private MainActivityViewModel mainActivityViewModel;
    private PedidosSinProcesarViewModel mViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pedidos_sin_procesar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewModel();

        Log.e("dato","dato " + misPedidosViewModel.text);
    }

    private void initViewModel() {
       // misPedidosViewModel = new ViewModelProvider(requireParentFragment()).get(MisPedidosViewModel.class);
        mViewModel = new ViewModelProvider(this).get(PedidosSinProcesarViewModel.class);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }
}