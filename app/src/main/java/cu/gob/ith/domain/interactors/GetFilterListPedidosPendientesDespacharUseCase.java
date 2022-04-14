package cu.gob.ith.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiDatosPedidoToDatosPedido;
import cu.gob.ith.domain.model.DatosPedido;
import io.reactivex.rxjava3.core.Observable;

public class GetFilterListPedidosPendientesDespacharUseCase
        implements GlobalUseCase<Observable<List<DatosPedido>>, Void> {

    private final ImplRepository implRepository;

    @Inject
    public GetFilterListPedidosPendientesDespacharUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<List<DatosPedido>> execute() {
        return implRepository.filterListPedidosPendientesDespachar()
                .map(apiPedidoResponse ->
                        TransformApiDatosPedidoToDatosPedido.mapListSecondConstructor(apiPedidoResponse.getApiDatosPedido()));
    }
}
