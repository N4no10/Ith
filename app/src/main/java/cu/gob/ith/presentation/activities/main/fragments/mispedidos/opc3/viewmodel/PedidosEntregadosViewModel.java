package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc3.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetFilterListPedidosFacturadosUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class PedidosEntregadosViewModel extends ViewModel {
    private final GetFilterListPedidosFacturadosUseCase filterListPedidosFacturadosUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public PedidosEntregadosViewModel(GetFilterListPedidosFacturadosUseCase filterListPedidosFacturadosUseCase) {
        this.filterListPedidosFacturadosUseCase = filterListPedidosFacturadosUseCase;
    }

    public void addCompositeDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public GetFilterListPedidosFacturadosUseCase getFilterListPedidosFacturadosUseCase() {
        return filterListPedidosFacturadosUseCase;
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if(compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}