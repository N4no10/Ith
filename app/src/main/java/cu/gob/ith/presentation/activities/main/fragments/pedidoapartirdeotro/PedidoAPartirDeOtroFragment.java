package cu.gob.ith.presentation.activities.main.fragments.pedidoapartirdeotro;

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

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentPedidoAPartirDeOtroBinding;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.presentation.activities.main.fragments.completarpedido.recyclerview.PedidosIncompletosAdapter;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.OnClickDelegateRV;
import cu.gob.ith.presentation.activities.main.fragments.pedidoapartirdeotro.recyclerview.PedidosAPartirDeOtroAdapter;
import cu.gob.ith.presentation.activities.main.fragments.pedidoapartirdeotro.viewmodel.PedidoAPartirDeOtroViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class PedidoAPartirDeOtroFragment extends Fragment {

    private PedidoAPartirDeOtroViewModel mViewModel;
    private FragmentPedidoAPartirDeOtroBinding uiBind;
    private MainActivityViewModel mainActivityViewModel;
    private PedidosAPartirDeOtroAdapter adapter;
    private OnClickDelegateRV onClickDelegateRV;

    public static PedidoAPartirDeOtroFragment newInstance() {
        return new PedidoAPartirDeOtroFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater,R.layout.fragment_pedido_a_partir_de_otro, container, false);
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
                mViewModel.getAllPedidos()
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
                    uiBind.getRoot()).navigate(R.id.detallesPedidoFragment, bundle);
        };
    }

    private void setupViewComponents() {
        mainActivityViewModel.isColapsedMainContent().observe(getViewLifecycleOwner(), collapsedMainContent -> {
            Log.e("ColapsedMenu", "ColapsedMenu " + collapsedMainContent);
            if (!collapsedMainContent)
                loadContent();
        });

        mainActivityViewModel.setTitleToolBar(getString(R.string.menu_pedido_a_partir_de_otro));
        mainActivityViewModel.setShowMenuOrBack(true);
    }

    private void updateAdapter(List<DatosPedido> list) {
        if (adapter == null)
            adapter = new PedidosAPartirDeOtroAdapter(list, onClickDelegateRV);
        else
            adapter.loadList(list);

        uiBind.setAdapter(adapter);
    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(PedidoAPartirDeOtroViewModel.class);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }


}