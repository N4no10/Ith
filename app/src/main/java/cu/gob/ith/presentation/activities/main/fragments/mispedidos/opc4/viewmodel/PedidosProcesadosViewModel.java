package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc4.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetFilterListPedidosDespachadosUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class PedidosProcesadosViewModel extends ViewModel {
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final GetFilterListPedidosDespachadosUseCase filterListPedidosDespachadosUseCase;

    @Inject
    public PedidosProcesadosViewModel(GetFilterListPedidosDespachadosUseCase filterListPedidosDespachadosUseCase) {
        this.filterListPedidosDespachadosUseCase = filterListPedidosDespachadosUseCase;
    }

    public GetFilterListPedidosDespachadosUseCase getFilterListPedidosDespachadosUseCase() {
        return filterListPedidosDespachadosUseCase;
    }

    public void addCompositeDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if(compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}