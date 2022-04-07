package cu.gob.ith.presentation.activities.main.fragments.mispedidos;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cu.gob.ith.databinding.MisPedidosFragmentBinding;
import cu.gob.ith.presentation.activities.main.fragments.menu.viewmodel.MenuFragmentViewModel;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.viewmodel.MisPedidosViewModel;
import cu.gob.ith.R;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MisPedidosFragment extends Fragment {

    private MisPedidosFragmentBinding uiBind;
    private MisPedidosViewModel mViewModel;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater,R.layout.mis_pedidos_fragment, container, false);
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewModel();
        currentToolBar();

    }

    public void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(MisPedidosViewModel.class);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }

    private void currentToolBar() {
        mainActivityViewModel.setTitleToolBar(getString(R.string.mis_pedidos));
        mainActivityViewModel.setShowMenuOrBack(true);
    }
}