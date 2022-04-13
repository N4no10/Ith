package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc3.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetFilterListPedidosFacturadosUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PedidosEntregadosViewModel extends ViewModel {
    private final GetFilterListPedidosFacturadosUseCase filterListPedidosFacturadosUseCase;

    @Inject
    public PedidosEntregadosViewModel(GetFilterListPedidosFacturadosUseCase filterListPedidosFacturadosUseCase) {
        this.filterListPedidosFacturadosUseCase = filterListPedidosFacturadosUseCase;
    }

    public GetFilterListPedidosFacturadosUseCase getFilterListPedidosFacturadosUseCase() {
        return filterListPedidosFacturadosUseCase;
    }
}