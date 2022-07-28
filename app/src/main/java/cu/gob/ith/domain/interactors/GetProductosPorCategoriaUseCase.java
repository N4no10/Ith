package cu.gob.ith.domain.interactors;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cu.gob.ith.common.URLEnum;
import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiProductoToProducto;
import cu.gob.ith.domain.model.Producto;
import io.reactivex.rxjava3.core.Observable;

public class GetProductosPorCategoriaUseCase implements GlobalUseCase<Observable<List<Producto>>, Map<String, Object>> {

    private final ImplRepository implRepository;

    @Inject
    public GetProductosPorCategoriaUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<List<Producto>> execute(Map<String,Object> param) {
        URLEnum urlEnum = (URLEnum) param.get("url");
        String codFamilia = (String) param.get("codFamilia");
        return implRepository.getProductos(urlEnum,codFamilia)
                .map(TransformApiProductoToProducto::mapList);
    }
}
