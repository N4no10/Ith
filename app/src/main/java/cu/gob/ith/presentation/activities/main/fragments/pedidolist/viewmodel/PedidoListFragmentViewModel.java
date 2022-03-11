package cu.gob.ith.presentation.activities.main.fragments.pedidolist.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.room.Index;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class PedidoListFragmentViewModel extends ViewModel {

    private MutableLiveData<Double> totalToPay = new MutableLiveData<>();

    @Inject
    public PedidoListFragmentViewModel() {
        this.totalToPay.setValue(0.0);
    }

    public LiveData<Double> getTotalToPay() {
        return totalToPay;
    }

    public void setTotalToPay(Double totalToPay) {
        this.totalToPay.setValue(totalToPay);
    }
}
