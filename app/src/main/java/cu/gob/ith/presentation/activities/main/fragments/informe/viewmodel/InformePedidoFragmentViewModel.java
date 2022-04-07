package cu.gob.ith.presentation.activities.main.fragments.informe.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.domain.model.InformePedido;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.fragments.informe.pdf.InformePedidoPDFGenerator;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class InformePedidoFragmentViewModel extends ViewModel {

    private  InformePedidoPDFGenerator informePedidoPDFGenerator;

    @Inject
    public InformePedidoFragmentViewModel(InformePedidoPDFGenerator informePedidoPDFGenerator) {
        this.informePedidoPDFGenerator = informePedidoPDFGenerator;
    }

    public void createInformePDF(String nombrePDF, InformePedido informePedido) throws IOException {
        Log.e("createPDF","pdf " + informePedidoPDFGenerator);
        informePedidoPDFGenerator.PDFInformeBuild(nombrePDF, informePedido );
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }
}
