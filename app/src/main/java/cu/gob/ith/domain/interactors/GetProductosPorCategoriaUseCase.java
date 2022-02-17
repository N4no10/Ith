package cu.gob.ith.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiProductoToProducto;
import cu.gob.ith.domain.model.Producto;
import io.reactivex.rxjava3.core.Observable;

public class GetProductosPorCategoriaUseCase implements GlobalUseCase<Observable<List<Producto>>, String> {

    private final ImplRepository implRepository;

    @Inject
    public GetProductosPorCategoriaUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<List<Producto>> execute(String param) {
        return implRepository.getProductos(param)
                .map(TransformApiProductoToProducto::mapList);
    }
}
