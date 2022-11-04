package cu.gob.ith.domain.interactors;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cu.gob.ith.common.URLEnum;
import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiProductoToProducto;
import cu.gob.ith.domain.model.Producto;
import io.reactivex.rxjava3.core.Observable;

public class SearchProductosUseCase implements GlobalUseCase<Observable<List<Producto>>, Map<String, Object>> {

    private final ImplRepository implRepository;

    @Inject
    public SearchProductosUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<List<Producto>> execute(Map<String, Object> param) {
        URLEnum urlEnum = (URLEnum) param.get("url");
        Map<String, String> filter = new HashMap<>();

        if (param.get("descripcion") != null)
            filter.put("Descripcion", (String) param.get("descripcion"));

        if (param.get("codigo") != null)
            filter.put("Codigo", (String) param.get("Codigo"));

        return implRepository.searchProductos(urlEnum, filter)
                .map(TransformApiProductoToProducto::mapList);
    }
}
