package cu.gob.ith.domain.interactors;

import static cu.gob.ith.common.URLEnum.ALL_PEDIDOS;
import static cu.gob.ith.common.URLEnum.PEDIDOS_DESPACHADOS_FALTANTES;

import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiDatosPedidoToDatosPedido;
import cu.gob.ith.domain.model.DatosPedido;
import io.reactivex.rxjava3.core.Observable;

public class GetListAllPedidosUseCase
        implements GlobalUseCase<Observable<List<DatosPedido>>, Void> {

    private final ImplRepository implRepository;

    @Inject
    public GetListAllPedidosUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<List<DatosPedido>> execute() {
        return implRepository.getListPedidos(ALL_PEDIDOS)
                .map(apiPedidoResponse ->
                        TransformApiDatosPedidoToDatosPedido.mapListSecondConstructor(apiPedidoResponse.getApiDatosPedido()));
    }
}
