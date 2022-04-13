package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc4.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetFilterListPedidosDespachadosUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PedidosProcesadosViewModel extends ViewModel {
    private final GetFilterListPedidosDespachadosUseCase filterListPedidosDespachadosUseCase;

    @Inject
    public PedidosProcesadosViewModel(GetFilterListPedidosDespachadosUseCase filterListPedidosDespachadosUseCase) {
        this.filterListPedidosDespachadosUseCase = filterListPedidosDespachadosUseCase;
    }

    public GetFilterListPedidosDespachadosUseCase getFilterListPedidosDespachadosUseCase() {
        return filterListPedidosDespachadosUseCase;
    }

}