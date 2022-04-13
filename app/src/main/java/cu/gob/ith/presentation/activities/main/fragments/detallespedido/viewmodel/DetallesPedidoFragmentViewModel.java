package cu.gob.ith.presentation.activities.main.fragments.detallespedido.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetDetallesPedidoITHUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class DetallesPedidoFragmentViewModel extends ViewModel {

    private GetDetallesPedidoITHUseCase DetallesPedidoITHUseCase;

    @Inject
    public DetallesPedidoFragmentViewModel(GetDetallesPedidoITHUseCase detallesPedidoITHUseCase) {
        DetallesPedidoITHUseCase = detallesPedidoITHUseCase;
    }

    public GetDetallesPedidoITHUseCase getDetallesPedidoITHUseCase() {
        return DetallesPedidoITHUseCase;
    }
}
