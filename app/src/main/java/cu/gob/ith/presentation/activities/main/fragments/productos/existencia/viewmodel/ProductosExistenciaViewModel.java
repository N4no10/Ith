package cu.gob.ith.presentation.activities.main.fragments.productos.existencia.viewmodel;

import static cu.gob.ith.common.URLEnum.ALL_CATEGORIAS;
import static cu.gob.ith.common.URLEnum.ALL_PRODUCTOS;
import static cu.gob.ith.common.URLEnum.CATEGORIAS;
import static cu.gob.ith.common.URLEnum.PRODUCTOS;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetCategoriasUseCase;
import cu.gob.ith.domain.interactors.GetProductosPorCategoriaUseCase;
import cu.gob.ith.domain.model.Categoria;
import cu.gob.ith.domain.model.Producto;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class ProductosExistenciaViewModel extends ViewModel {
    private boolean isLoadedData;
    private final GetCategoriasUseCase getCategoriasUseCase;
    private final GetProductosPorCategoriaUseCase getProductosPorCategoriaUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public ProductosExistenciaViewModel(GetCategoriasUseCase getCategoriasUseCase, GetProductosPorCategoriaUseCase getProductosPorCategoriaUseCase) {
        this.getCategoriasUseCase = getCategoriasUseCase;
        this.getProductosPorCategoriaUseCase = getProductosPorCategoriaUseCase;
    }

    public boolean isLoadedData() {
        return isLoadedData;
    }

    public void setLoadedData(boolean loadedData) {
        isLoadedData = loadedData;
    }

    public Observable<List<Categoria>> getGetCategoriasUseCase() {
        return getCategoriasUseCase.execute(ALL_CATEGORIAS);
    }

    public void addCompositeDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public Observable<List<Producto>> getGetProductosPorCategoriaUseCase(String codFamilia) {
        Map<String,Object> params = new HashMap<>();
        params.put("url",ALL_PRODUCTOS);
        params.put("codFamilia",codFamilia);
        return getProductosPorCategoriaUseCase.execute(params);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        if(compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}
