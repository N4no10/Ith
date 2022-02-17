package cu.gob.ith.domain.interactors;

import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.data.repository.ImplRepository;
import cu.gob.ith.domain.mappers.TransformApiCategoriaToCategoria;
import cu.gob.ith.domain.model.Categoria;
import io.reactivex.rxjava3.core.Observable;

public class GetCategoriasUseCase implements GlobalUseCase<Observable<List<Categoria>>, Void> {

    private final ImplRepository implRepository;

    @Inject
    public GetCategoriasUseCase(ImplRepository implRepository) {
        this.implRepository = implRepository;
    }

    @Override
    public Observable<List<Categoria>> execute() {
        return implRepository.getCategorias()
                .map(TransformApiCategoriaToCategoria::mapList);
    }
}
