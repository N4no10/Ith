package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentPedidosMesBinding;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1.viewmodel.PedidosMesViewModel;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.viewmodel.MisPedidosViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PedidosMesFragment extends Fragment {

    private FragmentPedidosMesBinding uiBind;
    private MainActivityViewModel mainActivityViewModel;
    private MisPedidosViewModel misPedidosViewModel;
    private PedidosMesViewModel mViewModel;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater,R.layout.fragment_pedidos_mes, container, false );
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewModel();
       // misPedidosViewModel.setText("sisisisisis");


        mainActivityViewModel.isColapsedMainContent().observe(getViewLifecycleOwner(), collapsedMainContent -> {
            Log.e("ColapsedMenu", "ColapsedMenu " + collapsedMainContent);
            if (!collapsedMainContent)
                loadContent();
        });
    }

    private void loadContent() {
        Map<String, Object> param = new HashMap<>();
        param.put("options.mesActual",true);

       /* mViewModel.addCompositeDisposable(
                misPedidosViewModel.getListPedidos(param)
                .subscribe(
                        datosPedidos -> Log.e("onNext", "next" + datosPedidos.size())
                       ,
                        e-> Log.e("Error","error cargando pedidos " + e.getMessage())
                ));*/
    }

    private void initViewModel() {
        misPedidosViewModel = new ViewModelProvider(requireParentFragment().requireParentFragment()).get(MisPedidosViewModel.class);
        mViewModel = new ViewModelProvider(this).get(PedidosMesViewModel.class);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }
}