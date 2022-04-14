package cu.gob.ith.presentation.activities.main.fragments.informe;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentInformePedidoBinding;
import cu.gob.ith.presentation.activities.main.fragments.informe.recyclerview.ProductoInformePedidoAdapter;
import cu.gob.ith.presentation.activities.main.fragments.informe.viewmodel.InformePedidoFragmentViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class InformePedidoFragment extends Fragment {

    private MainActivityViewModel mainActivityViewModel;
    private InformePedidoFragmentViewModel informePedidoFragmentViewModel;
    private FragmentInformePedidoBinding uiBind;

    public InformePedidoFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_informe_pedido, container, false);
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
    }

    private void initView() {
        initViewModels();
        setupViewComponents();
        initAdapter();
        listenerMotionLayoutListProducts();
        downloadPDF();
    }

    private void downloadPDF() {
        try {
            informePedidoFragmentViewModel.createInformePDF(getString(R.string.solicitud_file_name_pdf)
                            + " " + (mainActivityViewModel.getInformePedido().getDatosPedido()
                                    .getTipoCliente().equals("2") ? "TCP" : "Empresa") + "-" +
                            mainActivityViewModel.getInformePedido().getDatosPedido().getNumber(),
                    mainActivityViewModel.getInformePedido());
            Snackbar.make(uiBind.getRoot(), getString(R.string.success_build_pdf),
                    Snackbar.LENGTH_LONG).show();
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("createPDF", "pdf error " + e.getMessage());
        }
    }

    private void initViewModels() {
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        informePedidoFragmentViewModel = new ViewModelProvider(this).get(InformePedidoFragmentViewModel.class);
    }

    private void setupViewComponents() {
        mainActivityViewModel.setTitleToolBar(getString(R.string.informe_pedido_fragment));
        mainActivityViewModel.getProductosParaPedidosList().clear();
        mainActivityViewModel.getShowMenuOrBack().setValue(true);
        uiBind.setDatosPedido(mainActivityViewModel.getInformePedido().getDatosPedido());
    }

    private void initAdapter() {
        ProductoInformePedidoAdapter adapter = new ProductoInformePedidoAdapter(
                mainActivityViewModel.getInformePedido().getDatosPedido().getProductoList());
        uiBind.listProductosLayout.productosListRV.setAdapter(adapter);
    }

    private void listenerMotionLayoutListProducts() {
        uiBind.constraintLayout2.addTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
                uiBind.listProductosLayout.arrowMoreIV.setRotation(180 * progress);
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });
    }
}