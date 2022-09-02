package cu.gob.ith.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiProductoToProducto;
import cu.gob.ith.domain.model.Producto;
import io.reactivex.rxjava3.core.Observable;

public class GetListProductosByDocumentUseCase
        implements GlobalUseCase<Observable<List<Producto>>, Integer> {

    private final ImplRepository implRepository;

    @Inject
    public GetListProductosByDocumentUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<List<Producto>> execute(Integer numDoc) {
        return implRepository.getProductosByDocument(numDoc)
                .map(TransformApiProductoToProducto::mapApiListProductoToProductos);
    }
}
