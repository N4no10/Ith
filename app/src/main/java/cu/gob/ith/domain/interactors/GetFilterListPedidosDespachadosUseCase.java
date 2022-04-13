package cu.gob.ith.domain.interactors;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiDatosPedidoToDatosPedido;
import cu.gob.ith.domain.model.Categoria;
import cu.gob.ith.domain.model.DatosPedido;
import io.reactivex.rxjava3.core.Observable;

public class GetFilterListPedidosDespachadosUseCase
        implements GlobalUseCase<Observable<List<DatosPedido>>, Void> {

    private final ImplRepository implRepository;

    @Inject
    public GetFilterListPedidosDespachadosUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<List<DatosPedido>> execute() {
        return implRepository.filterListPedidosDespachados()
                .map(apiPedidoResponse ->
                        TransformApiDatosPedidoToDatosPedido.mapListSecondConstructor(apiPedidoResponse.getApiDatosPedido()));
    }
}
