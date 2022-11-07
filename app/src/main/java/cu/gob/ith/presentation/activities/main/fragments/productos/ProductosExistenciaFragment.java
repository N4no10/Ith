package cu.gob.ith.presentation.activities.main.fragments.productos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;
import java.util.concurrent.TimeUnit;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentProductosExistenciaBinding;
import cu.gob.ith.domain.model.Categoria;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.categorias.CategoriasAdapter;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.categorias.ItemCategoriaClick;
import cu.gob.ith.presentation.activities.main.fragments.productos.existencia.recyclerview.ProductosExistenciaAdapter;
import cu.gob.ith.presentation.activities.main.fragments.productos.existencia.viewmodel.ProductosExistenciaViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;

@AndroidEntryPoint
public class ProductosExistenciaFragment extends Fragment implements ItemCategoriaClick {

    private FragmentProductosExistenciaBinding uiBind;
    private ProductosExistenciaViewModel viewModel;
    private MainActivityViewModel mainActivityViewModel;
    private ProductosExistenciaAdapter productosAdapter;
    private CategoriasAdapter categoriasAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_productos_existencia, container, false);
        // Inflate the layout for this fragment
        return uiBind.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initViewModel();

        mainActivityViewModel.isColapsedMainContent().observe(getViewLifecycleOwner(), collapsedMainContent -> {
            Log.e("ColapsedMenu", "ColapsedMenu " + collapsedMainContent);
            if (!collapsedMainContent /*&& !viewModel.isLoadedData()*/)
                loadContent();
        });

        currentToolBar();
        listenerMotionLayoutListProducts();
        iniFilter();
    }

    private void iniFilter() {
        searchEditText();

        uiBind.filterLayout.disponibilidadCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            Log.e("click", "click Chaked");
            viewModel.setDisponibles(isChecked);
            filterListProductos();
            //  loadProductosByCategoria();
        });

        uiBind.filterLayout.existenciaCB.setOnCheckedChangeListener((buttonView, isChecked) -> {
            viewModel.setExistencia(isChecked);
            filterListProductos();
            // loadProductosByCategoria();
        });
    }

    private void searchEditText() {
        // viewModel.addCompositeDisposable(
        viewModel.setSerialDisposable(
                Observable.create((ObservableOnSubscribe<String>) emitter -> {
                            Log.e("Emitter", "Emitter");
                            uiBind.buscarProductSV.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                                @Override
                                public boolean onQueryTextSubmit(String query) {
                                    return false;
                                }

                                @Override
                                public boolean onQueryTextChange(String newText) {
                                    emitter.onNext(newText);
                                    return false;
                                }
                            });
                        })
                        .debounce(2, TimeUnit.SECONDS)
                        .distinctUntilChanged()
                        .observeOn(AndroidSchedulers.mainThread())
                        .filter(text -> {
                            boolean result = !(text.equals("") || text.equals(" "));
                            showTransitionListProductosSearch(result);
                            return result;
                        })
                        .concatMap(text -> {
                            Log.e("concatMap", "concatMap " + text + " > Hilo: " + Thread.currentThread());
                            return viewModel.searchProductos(text, null);
                        })
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                productos -> {
                                    Log.e("onNext", "next " + productos.size());
                                    initAdapterProductos(productos);
                                },
                                throwable -> Log.e("Error", "error " + throwable.getMessage())
                        )
        );

        // );

    }


    private void showTransitionListProductosSearch(boolean show) {
        if (productosAdapter != null)
            productosAdapter.clearListProductos();

        if (categoriasAdapter != null)
            categoriasAdapter.clearCategoriaSelected();

        if (show) {
            uiBind.listProductosByCategoriaLayout.productosTitleTV.setVisibility(View.GONE);
            uiBind.listProductosByCategoriaLayout.arrowMoreIV.setVisibility(View.GONE);
            uiBind.contentMenuCL.transitionToState(R.id.opcionSearch);
        } else {
            uiBind.listProductosByCategoriaLayout.productosTitleTV.setVisibility(View.VISIBLE);
            uiBind.listProductosByCategoriaLayout.arrowMoreIV.setVisibility(View.VISIBLE);
            uiBind.contentMenuCL.transitionToStart();
        }
    }


    private void enabledFilter(boolean enabled) {
        uiBind.filterLayout.disponibilidadCB.setEnabled(enabled);
        uiBind.filterLayout.existenciaCB.setEnabled(enabled);
        // uiBind.buscarProductSV.setEnabled(enabled);
    }

    private void listenerMotionLayoutListProducts() {
        uiBind.contentMenuCL.addTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {
                uiBind.listProductosByCategoriaLayout.arrowMoreIV.setRotation(180 * progress);
            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });
    }

    public void initViewModel() {
        viewModel = new ViewModelProvider(this).get(ProductosExistenciaViewModel.class);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }

    private void currentToolBar() {
        mainActivityViewModel.setTitleToolBar(getString(R.string.productos_existentes));
        mainActivityViewModel.setShowMenuOrBack(true);
    }

    private void loadContent() {
        enabledFilter(false);
        viewModel.addCompositeDisposable(
                viewModel.getGetCategoriasUseCase()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(categorias -> {
                            viewModel.setLoadedData(true);
                            initAdapterCategoria(categorias);
                            enabledFilter(true);
                            Log.e("Load All", "success");
                        }, throwable -> {
                            Log.e("GetCategoriasList", "loadContent: " + throwable.getMessage());
                            enabledFilter(true);
                        })
        );
    }


    @Override
    public void clickEvent(Categoria categoria) {
        viewModel.setCurrentCodFamilia(categoria.getCodFamilia());
        loadProductosByCategoria();
    }

    private void loadProductosByCategoria() {
        enabledFilter(false);
        viewModel.addCompositeDisposable(
                viewModel.getGetProductosPorCategoriaUseCase()
                        .flatMapIterable(productos -> productos)
                        .filter(producto -> viewModel.filterDisponibilidadExistencia(producto))
                        .toList()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(productos -> {
                            Log.e("next productos", "productos");
                            initAdapterProductos(productos);
                            enabledFilter(true);
                        }, throwable -> {
                            Log.e("GetProductosXCategoria", "clickEvent: " + throwable.getMessage());
                            enabledFilter(true);
                        })
        );
    }

    private void filterListProductos() {
        if (!uiBind.buscarProductSV.getQuery().toString().equals("")) {
            enabledFilter(false);
            viewModel.addCompositeDisposable(
                    viewModel.searchProductos("" + uiBind.buscarProductSV.getQuery(), null)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    list -> {
                                        enabledFilter(true);
                                        initAdapterProductos(list);
                                    },
                                    e -> {
                                        enabledFilter(true);
                                        Log.e("error", "error");
                                    }
                            )
            );
        } else loadProductosByCategoria();
    }

    public void initAdapterCategoria(List<Categoria> categoriaList) {
        if (categoriasAdapter == null) {
            categoriasAdapter = new CategoriasAdapter(categoriaList);
            categoriasAdapter.setItemCategoriaClick(this);
        } else categoriasAdapter.addNewListCategorias(categoriaList);

        uiBind.listCategoriaLayout.listCategoriaRV.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));
        uiBind.listCategoriaLayout.setAdapter(categoriasAdapter);
    }

    public void initAdapterProductos(List<Producto> productoList) {
        if (productosAdapter == null)
            productosAdapter = new ProductosExistenciaAdapter(productoList);
        else productosAdapter.addNewListProductos(productoList);

        uiBind.listProductosByCategoriaLayout.setAdapter(productosAdapter);
    }

    @Override
    public void onDestroyView() {
        if (viewModel.getCompositeDisposable().isDisposed())
            viewModel.getCompositeDisposable().dispose();

        if (viewModel.getSerialCompositeDisposable().isDisposed())
            viewModel.getSerialCompositeDisposable().isDisposed();
        super.onDestroyView();
    }
}