package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetListPedidosUseCase;
import cu.gob.ith.domain.model.DatosPedido;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class PedidosMesViewModel extends ViewModel {

    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private String inicFecha;
    private String finFecha;

    @Inject
    public PedidosMesViewModel() {
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