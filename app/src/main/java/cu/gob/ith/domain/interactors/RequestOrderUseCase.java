package cu.gob.ith.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.data.api.model.ApiPedidoResponse;
import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiPedidoResponseToInformePedido;
import cu.gob.ith.domain.mappers.TransformPedidoToApiPedido;
import cu.gob.ith.domain.model.InformePedido;
import cu.gob.ith.domain.model.Pedido;
import io.reactivex.rxjava3.core.Observable;

public class RequestOrderUseCase implements GlobalUseCase<Observable<InformePedido>, List<Pedido>> {
    private final ImplRepository implRepository;

    @Inject
    public RequestOrderUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<InformePedido> execute(List<Pedido> param) {
        return implRepository.requestOrder(TransformPedidoToApiPedido.mapList(param))
                .map(TransformApiPedidoResponseToInformePedido::map);
    }
}
