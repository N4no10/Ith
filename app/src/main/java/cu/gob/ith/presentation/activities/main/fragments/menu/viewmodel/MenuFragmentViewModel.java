package cu.gob.ith.presentation.activities.main.fragments.menu.viewmodel;

import androidx.lifecycle.ViewModel;

import javax.inject.Inject;

import cu.gob.ith.domain.interactors.GetCategoriasUseCase;
import cu.gob.ith.domain.interactors.GetProductosPorCategoriaUseCase;
import dagger.hilt.android.lifecycle.HiltViewModel;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;

@HiltViewModel
public class MenuFragmentViewModel extends ViewModel {
    private boolean isLoadedData;
    private final GetCategoriasUseCase getCategoriasUseCase;
    private final GetProductosPorCategoriaUseCase getProductosPorCategoriaUseCase;
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Inject
    public MenuFragmentViewModel(GetCategoriasUseCase getCategoriasUseCase, GetProductosPorCategoriaUseCase getProductosPorCategoriaUseCase) {
        this.getCategoriasUseCase = getCategoriasUseCase;
        this.getProductosPorCategoriaUseCase = getProductosPorCategoriaUseCase;
    }

    public boolean isLoadedData() {
        return isLoadedData;
    }

    public void setLoadedData(boolean loadedData) {
        isLoadedData = loadedData;
    }

    public GetCategoriasUseCase getGetCategoriasUseCase() {
        return getCategoriasUseCase;
    }

    public void addCompositeDisposable(Disposable disposable) {
        compositeDisposable.add(disposable);
    }

    public GetProductosPorCategoriaUseCase getGetProductosPorCategoriaUseCase() {
        return getProductosPorCategoriaUseCase;
    }
}
