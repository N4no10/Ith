package cu.gob.ith.domain.interactors;

import javax.inject.Inject;

import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiPedidoResponseToInformePedido;
import cu.gob.ith.domain.model.InformePedido;
import io.reactivex.rxjava3.core.Observable;

public class GetPedidoByIdUseCase implements GlobalUseCase<Observable<InformePedido>, Integer> {

    private final ImplRepository implRepository;

    @Inject
    public GetPedidoByIdUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<InformePedido> execute(Integer param) {
        return implRepository.getPedidoById(param)
                .map(TransformApiPedidoResponseToInformePedido::map);
    }
}
