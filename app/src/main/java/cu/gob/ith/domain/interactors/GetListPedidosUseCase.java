package cu.gob.ith.domain.interactors;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import cu.gob.ith.data.api.model.ApiListPedidos;
import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiDatosPedidoToDatosPedido;
import cu.gob.ith.domain.model.DatosPedido;
import io.reactivex.rxjava3.core.Observable;

public class GetListPedidosUseCase implements GlobalUseCase<Observable<List<DatosPedido>>, Map<String,Object>>{

    private final ImplRepository implRepository;

    @Inject
    public GetListPedidosUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<List<DatosPedido>> execute(Map<String,Object> param) {
        return implRepository.filterListPedidos(param)
                .map(apiPedidoResponse ->
                        TransformApiDatosPedidoToDatosPedido.mapList(apiPedidoResponse.getApiDatosPedido()));
    }
}
