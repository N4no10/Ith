package cu.gob.ith.presentation.activities.main.fragments.completarpedido.detalle;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentDetallePedidoIncompletoBinding;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.fragments.completarpedido.detalle.viewmodel.DetallePedidoIncompletoViewModel;
import cu.gob.ith.presentation.activities.main.fragments.detallespedido.recyclerview.ItemDetalleProductoAdapter;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

@AndroidEntryPoint
public class DetallePedidoIncompletoFragment extends Fragment {

    private FragmentDetallePedidoIncompletoBinding uiBind;
    private DetallePedidoIncompletoViewModel mViewModel;
    private MainActivityViewModel mainActivityViewModel;
    private ItemDetalleProductoAdapter adapter;

    public static DetallePedidoIncompletoFragment newInstance() {
        return new DetallePedidoIncompletoFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_detalle_pedido_incompleto,
                container, false);
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewModel();
        setupViewComponents();
    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(DetallePedidoIncompletoViewModel.class);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }

    private void setupViewComponents() {
        mainActivityViewModel.isColapsedMainContent().observe(getViewLifecycleOwner(), collapsedMainContent -> {
            Log.e("ColapsedMenu", "ColapsedMenu " + collapsedMainContent);
            if (!collapsedMainContent)
                loadContent();
        });

        mainActivityViewModel.setTitleToolBar(getString(R.string.completar_pedido)
                + " " + requireArguments().getString("pedidoId"));
        mainActivityViewModel.setShowMenuOrBack(false);

        uiBind.buttonSendList.setOnClickListener(v -> solicitarPedido());
    }

    private void loadContent() {
        mViewModel.addCompositeDisposable(
                mViewModel.getListProductos(Integer.parseInt(requireArguments().getString("pedidoId")))
                        .subscribe(
                                this::updateAdapter,
                                throwable -> Log.e("GetProductosPedido",
                                        "loadContent: " + throwable.getMessage())
                        )
        );
    }

    private void solicitarPedido() {
        if (adapter != null && adapter.getItemCount() > 0) {
            uiBind.buttonSendList.startAnimation();
            mViewModel.addCompositeDisposable(
                    mViewModel.solicitarPedido(adapter.getProductoList())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(informePedido -> {
                                        Snackbar.make(uiBind.getRoot(),
                                                getString(R.string.success_solicitud_pedido),
                                                Snackbar.LENGTH_LONG).show();
                                        mainActivityViewModel.setInformePedido(informePedido);
                                        Navigation.findNavController(uiBind.getRoot())
                                                .navigate(R.id.action_detallePedidoIncompletoFragment_to_informePedidoFragment);
                                    },
                                    e -> {
                                        Toast.makeText(requireContext(), "Error: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        uiBind.buttonSendList.revertAnimation();
                                    }, () -> uiBind.buttonSendList.revertAnimation())
            );
        }
    }

    private void updateAdapter(List<Producto> productoList) {
        if (adapter == null)
            adapter = new ItemDetalleProductoAdapter(productoList,
                    R.layout.item_productos_detalles_pedido_incompleto);
        else adapter.loadList(productoList);

        uiBind.setAdapter(adapter);
    }
}