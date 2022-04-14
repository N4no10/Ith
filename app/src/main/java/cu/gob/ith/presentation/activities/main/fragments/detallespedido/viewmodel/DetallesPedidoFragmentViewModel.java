package cu.gob.ith.presentation.activities.main.fragments.detallespedido.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.io.IOException;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetDetallesPedidoITHUseCase;
import cu.gob.ith.domain.interactors.GetPedidoByIdUseCase;
import cu.gob.ith.domain.model.InformePedido;
import cu.gob.ith.presentation.activities.main.fragments.informe.pdf.InformePedidoPDFGenerator;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Observable;

@HiltViewModel
public class DetallesPedidoFragmentViewModel extends ViewModel {

    private final GetDetallesPedidoITHUseCase detallesPedidoITHUseCase;
    private final InformePedidoPDFGenerator informePedidoPDFGenerator;
    private final GetPedidoByIdUseCase getPedidoByIdUseCase;

    @Inject
    public DetallesPedidoFragmentViewModel(GetDetallesPedidoITHUseCase detallesPedidoITHUseCase,
                                           InformePedidoPDFGenerator informePedidoPDFGenerator, GetPedidoByIdUseCase getPedidoByIdUseCase) {
        this.detallesPedidoITHUseCase = detallesPedidoITHUseCase;
        this.informePedidoPDFGenerator = informePedidoPDFGenerator;
        this.getPedidoByIdUseCase = getPedidoByIdUseCase;
    }

    public GetDetallesPedidoITHUseCase getDetallesPedidoITHUseCase() {
        return this.detallesPedidoITHUseCase;
    }

    public void createInformePDF(String nombrePDF, InformePedido informePedido) throws IOException {
        Log.e("createPDF","pdf " + informePedidoPDFGenerator);
        informePedidoPDFGenerator.PDFInformeBuild(nombrePDF, informePedido );
    }

    public Observable<InformePedido> getGetPedidoByIdUseCase(int idPedido) {
        return getPedidoByIdUseCase.execute(idPedido);
    }
}
