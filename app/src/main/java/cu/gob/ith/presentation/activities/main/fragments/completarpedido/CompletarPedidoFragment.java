package cu.gob.ith.presentation.activities.main.fragments.completarpedido;

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
import androidx.navigation.Navigation;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentCompletarPedidoBinding;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.presentation.activities.main.fragments.completarpedido.recyclerview.PedidosIncompletosAdapter;
import cu.gob.ith.presentation.activities.main.fragments.completarpedido.viewmodel.CompletarPedidoViewModel;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.OnClickDelegateRV;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class CompletarPedidoFragment extends Fragment {

    private FragmentCompletarPedidoBinding uiBind;
    private CompletarPedidoViewModel mViewModel;
    private MainActivityViewModel mainActivityViewModel;
    private PedidosIncompletosAdapter adapter;
    private OnClickDelegateRV onClickDelegateRV;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_completar_pedido, container, false);
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        initOnClickInViewHolderItem();
        setupViewComponents();
    }

    private void loadContent() {
        mViewModel.addCompositeDisposable(
                mViewModel.getPedidosConFaltantes()
                        .subscribe(
                                this::updateAdapter
                                ,
                                throwable -> Log.e("GetPedidosConFaltantes",
                                        "loadContent: " + throwable.getMessage())
                        )
        );
    }

    private void initOnClickInViewHolderItem() {
        onClickDelegateRV = id -> {
            Log.e("Click", "click " + id);
            Bundle bundle = new Bundle();
            bundle.putString("pedidoId", id);
            Navigation.findNavController(
                    uiBind.getRoot()).navigate(R.id.detallePedidoIncompletoFragment, bundle);
        };
    }

    private void setupViewComponents() {
        mainActivityViewModel.isColapsedMainContent().observe(getViewLifecycleOwner(), collapsedMainContent -> {
            Log.e("ColapsedMenu", "ColapsedMenu " + collapsedMainContent);
            if (!collapsedMainContent)
                loadContent();
        });

        mainActivityViewModel.setTitleToolBar(getString(R.string.menu_completar_pedido));
        mainActivityViewModel.setShowMenuOrBack(true);
    }

    private void updateAdapter(List<DatosPedido> list) {
        if (adapter == null)
            adapter = new PedidosIncompletosAdapter(list, onClickDelegateRV);
        else
            adapter.loadList(list);

        uiBind.setAdapter(adapter);
    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(CompletarPedidoViewModel.class);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }
}