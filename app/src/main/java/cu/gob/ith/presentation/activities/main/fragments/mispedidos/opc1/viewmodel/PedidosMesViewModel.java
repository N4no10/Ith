package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
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
    private MutableLiveData<Long> inicFecha = new MutableLiveData<>();
    private MutableLiveData<Long> finFecha = new MutableLiveData<>();

    @Inject
    public PedidosMesViewModel() {
    }

    public void addCompositeDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public LiveData<Long> getInicFecha() {
        return inicFecha;
    }

    public LiveData<Long> getFinFecha() {
        return finFecha;
    }

    public void setInicFecha(Long inicFecha) {
        this.inicFecha.setValue(inicFecha);
    }

    public void setFinFecha(Long finFecha) {
        this.finFecha.setValue(finFecha);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if(compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}