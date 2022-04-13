package cu.gob.ith.presentation.activities.main.fragments.mispedidos.viewmodel;

import android.util.Log;

import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetListPedidosUseCase;
import cu.gob.ith.domain.model.DatosPedido;
import dagger.assisted.Assisted;
import dagger.assisted.AssistedInject;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Observable;

@HiltViewModel
public class MisPedidosViewModel extends ViewModel {
    // TODO: Implement the ViewModel
    private final GetListPedidosUseCase getListPedidosUseCase;

    @Inject
    public MisPedidosViewModel(GetListPedidosUseCase getListPedidosUseCase) {
        this.getListPedidosUseCase = getListPedidosUseCase;
    }

    public Observable<List<DatosPedido>> getListPedidos(Map<String,Object> params){
        return getListPedidosUseCase.execute(params);
    }

}