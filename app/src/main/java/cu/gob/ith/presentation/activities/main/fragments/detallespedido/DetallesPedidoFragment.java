package cu.gob.ith.presentation.activities.main.fragments.detallespedido;

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

import com.google.android.material.snackbar.Snackbar;

import java.io.IOException;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentDetallesPedidoBinding;
import cu.gob.ith.presentation.activities.main.fragments.detallespedido.recyclerview.ItemDetalleProductoAdapter;
import cu.gob.ith.presentation.activities.main.fragments.detallespedido.viewmodel.DetallesPedidoFragmentViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;

@AndroidEntryPoint
public class DetallesPedidoFragment extends Fragment {

    private FragmentDetallesPedidoBinding uiBind;
    private String id;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private DetallesPedidoFragmentViewModel mViewModel;
    private MainActivityViewModel mainActivityViewModel;

    public DetallesPedidoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_detalles_pedido, container, false);
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initViewModel() {
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        mViewModel = new ViewModelProvider(this).get(DetallesPedidoFragmentViewModel.class);
    }

    private void initView() {
        initViewModel();
        setupViewComponents();
        loadContent();
        initDownloadPDF();
    }

    private void initDownloadPDF() {
        uiBind.downloadPdfFAB.setOnClickListener(v -> compositeDisposable.add(
                mViewModel.getGetPedidoByIdUseCase(Integer.parseInt(id))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                informePedido -> {

                                    try {
                                        mViewModel.createInformePDF(getString(R.string.solicitud_file_name_pdf)
                                                + " " + (informePedido.getDatosPedido()
                                                .getTipoCliente().equals("2") ? "TCP" : "Empresa") + "-" +
                                                informePedido.getDatosPedido().getNumber(), informePedido);
                                        Snackbar.make(uiBind.getRoot(), getString(R.string.success_build_pdf),
                                                Snackbar.LENGTH_LONG).show();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                        Log.e("createPDF", "pdf error " + e.getMessage());
                                        Snackbar.make(uiBind.getRoot(), "Error " + e.getMessage(),
                                                Snackbar.LENGTH_LONG).show();
                                    }

                                },
                                throwable -> Log.e("Error", "error " + throwable.getMessage()))
        ));
    }

    private void setupViewComponents() {
        mainActivityViewModel.setTitleToolBar(getString(R.string.detalle_pedido));
        mainActivityViewModel.getShowMenuOrBack().setValue(false);

        if (getArguments() != null && getArguments().getString("pedidoId") != null)
            id = getArguments().getString("pedidoId");
        uiBind.setPedidoNo(id);
    }

    private void loadContent() {
        compositeDisposable.add(
                mViewModel.getDetallesPedidoITHUseCase().execute(Integer.parseInt(id))
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(productoList ->
                                        uiBind.detalleProductosRV.setAdapter(
                                                new ItemDetalleProductoAdapter(productoList)),
                                throwable -> Log.e("TAG", "initView: " + throwable.getMessage()))
        );
    }

    @Override
    public void onDestroyView() {
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
        super.onDestroyView();
    }
}