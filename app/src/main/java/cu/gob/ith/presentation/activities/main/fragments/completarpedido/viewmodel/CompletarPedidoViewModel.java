package cu.gob.ith.presentation.activities.main.fragments.completarpedido.viewmodel;

import androidx.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetListPedidosConFaltantesUseCase;
import cu.gob.ith.domain.model.DatosPedido;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;


@HiltViewModel
public class CompletarPedidoViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final GetListPedidosConFaltantesUseCase getListPedidosConFaltantesUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public CompletarPedidoViewModel(GetListPedidosConFaltantesUseCase getListPedidosConFaltantesUseCase) {
        this.getListPedidosConFaltantesUseCase = getListPedidosConFaltantesUseCase;
    }

    public Observable<List<DatosPedido>> getPedidosConFaltantes() {
        return getListPedidosConFaltantesUseCase.execute()
                .observeOn(AndroidSchedulers.mainThread());
    }

    public void addCompositeDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}