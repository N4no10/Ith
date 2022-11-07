package cu.gob.ith.presentation.activities.main.fragments.productos.existencia.viewmodel;

import static cu.gob.ith.common.URLEnum.CATEGORIAS_CON_EXISTENCIA;
import static cu.gob.ith.common.URLEnum.PRODUCTOS_CON_EXISTENCIA;
import static cu.gob.ith.common.URLEnum.PRODUCTOS_CON_EXISTENCIA_SEARCH;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetCategoriasUseCase;
import cu.gob.ith.domain.interactors.GetProductosPorCategoriaUseCase;
import cu.gob.ith.domain.interactors.SearchProductosUseCase;
import cu.gob.ith.domain.model.Categoria;
import cu.gob.ith.domain.model.Producto;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.disposables.SerialDisposable;

@HiltViewModel
public class ProductosExistenciaViewModel extends ViewModel {
    private boolean isLoadedData;
    private final GetCategoriasUseCase getCategoriasUseCase;
    private final GetProductosPorCategoriaUseCase getProductosPorCategoriaUseCase;
    private final SearchProductosUseCase searchProductosUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    private boolean disponibles;
    private boolean existencia;
    private String currentCodFamilia;

    private SerialDisposable serialDisposable = new SerialDisposable();

    @Inject
    public ProductosExistenciaViewModel(GetCategoriasUseCase getCategoriasUseCase,
                                        GetProductosPorCategoriaUseCase getProductosPorCategoriaUseCase,
                                        SearchProductosUseCase searchProductosUseCase) {
        this.getCategoriasUseCase = getCategoriasUseCase;
        this.getProductosPorCategoriaUseCase = getProductosPorCategoriaUseCase;
        this.searchProductosUseCase = searchProductosUseCase;
    }

    public boolean isLoadedData() {
        return isLoadedData;
    }

    public void setLoadedData(boolean loadedData) {
        isLoadedData = loadedData;
    }

    public Observable<List<Categoria>> getGetCategoriasUseCase() {
        return getCategoriasUseCase.execute(CATEGORIAS_CON_EXISTENCIA);
    }

    public void addCompositeDisposable(Disposable disposable) {
        /*if(compositeDisposable.size() > 2) {
            Log.e("composite","composite > 2");
            compositeDisposable.clear();
        }*/
        compositeDisposable.clear();
        compositeDisposable.add(disposable);
    }

    public void setSerialDisposable(Disposable disposable){
        serialDisposable.set(disposable);
    }

    public CompositeDisposable getCompositeDisposable() {
        return compositeDisposable;
    }

    public CompositeDisposable getSerialCompositeDisposable() {
        return compositeDisposable;
    }

    public Observable<List<Producto>> getGetProductosPorCategoriaUseCase() {
        Map<String, Object> params = new HashMap<>();
        params.put("url", PRODUCTOS_CON_EXISTENCIA);
        params.put("codFamilia", currentCodFamilia);
        return getProductosPorCategoriaUseCase.execute(params);
    }

    public Observable<List<Producto>> searchProductos(String descripcion, String codigo) {
        Map<String, Object> params = new HashMap<>();
        params.put("url", PRODUCTOS_CON_EXISTENCIA_SEARCH);

        if (descripcion != null)
            params.put("descripcion", descripcion);

        if (codigo != null)
            params.put("codigo", codigo);

        return searchProductosUseCase.execute(params)
                .flatMapIterable(productos -> productos)
                .filter(this::filterDisponibilidadExistencia)
                .toList()
                .toObservable();
    }

    public boolean isDisponibles() {
        return disponibles;
    }

    public void setDisponibles(boolean disponibles) {
        this.disponibles = disponibles;
    }

    public boolean isExistencia() {
        return existencia;
    }

    public void setExistencia(boolean existencia) {
        this.existencia = existencia;
    }

    public String getCurrentCodFamilia() {
        return currentCodFamilia;
    }

    public void setCurrentCodFamilia(String currentCodFamilia) {
        this.currentCodFamilia = currentCodFamilia;
    }

    public boolean filterDisponibilidadExistencia(Producto producto) {
        if (isDisponibles()) {
            if (isExistencia()) {
              //  Log.e("Disponibles Existencia", "Disponibles y Existentes");
                return producto.getDisponibilidadProducto() > 0.0 && producto.getCantProducto() > 0.0;
            }
            return producto.getDisponibilidadProducto() > 0.0;
        } else {
            if (isExistencia()) {
               // Log.e("N0 Disponibles Exist", "No Disponibles y Existentes " + producto.getCantProducto());
                return producto.getCantProducto() > 0.0;
            }

          //  Log.e("N0 Disponibles No Exis", "No Disponibles y No existentes");

            return true;
        }
    }

    @Override
    protected void onCleared() {
        super.onCleared();


    }
}
