package cu.gob.ith.presentation.activities.main.fragments.pedidolist.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Index;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.RequestOrderUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PedidoListFragmentViewModel extends ViewModel {

    private MutableLiveData<Float> totalToPay = new MutableLiveData<>();
    private final RequestOrderUseCase requestOrderUseCase;

    @Inject
    public PedidoListFragmentViewModel(RequestOrderUseCase requestOrderUseCase) {
        this.totalToPay.setValue(0f);
        this.requestOrderUseCase = requestOrderUseCase;
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
}
