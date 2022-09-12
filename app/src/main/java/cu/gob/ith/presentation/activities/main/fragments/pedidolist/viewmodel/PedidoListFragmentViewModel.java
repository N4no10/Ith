package cu.gob.ith.presentation.activities.main.fragments.pedidolist.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetPedidoByIdUseCase;
import cu.gob.ith.domain.interactors.RequestOrderUseCase;
import cu.gob.ith.domain.model.InformePedido;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class PedidoListFragmentViewModel extends ViewModel {

    private MutableLiveData<Float> totalToPay = new MutableLiveData<>();
    private final RequestOrderUseCase requestOrderUseCase;
    private final GetPedidoByIdUseCase getPedidoByIdUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();


    @Inject
    public PedidoListFragmentViewModel(RequestOrderUseCase requestOrderUseCase, GetPedidoByIdUseCase getPedidoByIdUseCase) {
        this.totalToPay.setValue(0f);
        this.requestOrderUseCase = requestOrderUseCase;
        this.getPedidoByIdUseCase = getPedidoByIdUseCase;
    }

    public LiveData<Float> getTotalToPay() {
        return totalToPay;
    }

    public void setTotalToPay(float totalToPay) {
        this.totalToPay.setValue(totalToPay);
    }

    public RequestOrderUseCase getRequestOrderUseCase() {
        return requestOrderUseCase;
    }

    public Observable<InformePedido> getGetPedidoByIdUseCase(int idPedido) {
        return getPedidoByIdUseCase.execute(idPedido);
    }

    public void addCompositeDisposable(Disposable disposable){
        compositeDisposable.add(disposable);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if (compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}
