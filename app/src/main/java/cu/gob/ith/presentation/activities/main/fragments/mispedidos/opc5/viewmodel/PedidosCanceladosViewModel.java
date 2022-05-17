package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc5.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetFilterListPedidosCanceladosUseCase;
import cu.gob.ith.domain.model.DatosPedido;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class PedidosCanceladosViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private final GetFilterListPedidosCanceladosUseCase filterListPedidosCanceladosUseCase;

    @Inject
    public PedidosCanceladosViewModel(GetFilterListPedidosCanceladosUseCase filterListPedidosCanceladosUseCase) {
        this.filterListPedidosCanceladosUseCase = filterListPedidosCanceladosUseCase;
    }

    public void addCompositeDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public Observable<List<DatosPedido>> getListPedidosCancelados(){
        return filterListPedidosCanceladosUseCase.execute();
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        Log.e("destroy ","destroy");
        if(compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}