package cu.gob.ith.presentation.activities.main.fragments.productos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentMenuBinding;
import cu.gob.ith.databinding.FragmentProductosExistenciaBinding;
import cu.gob.ith.domain.model.Categoria;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.categorias.CategoriasAdapter;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.categorias.ItemCategoriaClick;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos.ManageProductListUtil;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos.ProductosAdapter;
import cu.gob.ith.presentation.activities.main.fragments.menu.viewmodel.MenuFragmentViewModel;
import cu.gob.ith.presentation.activities.main.fragments.productos.existencia.recyclerview.ProductosExistenciaAdapter;
import cu.gob.ith.presentation.activities.main.fragments.productos.existencia.viewmodel.ProductosExistenciaViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;

@AndroidEntryPoint
public class ProductosExistenciaFragment extends Fragment implements ItemCategoriaClick {

    private FragmentProductosExistenciaBinding uiBind;
    private ProductosExistenciaViewModel viewModel;
    private MainActivityViewModel mainActivityViewModel;

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
        viewModel.addCompositeDisposable(
                viewModel.getGetCategoriasUseCase()
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(categorias -> {
                            CategoriasAdapter categoriasAdapter = new CategoriasAdapter(categorias);
                            categoriasAdapter.setItemCategoriaClick(this);
                            viewModel.setLoadedData(true);
                            uiBind.listCategoriaLayout.setAdapter(categoriasAdapter);
                            uiBind.listCategoriaLayout.listCategoriaRV.setLayoutManager(
                                    new LinearLayoutManager(getContext(),
                                            LinearLayoutManager.HORIZONTAL,
                                            false));
                            Log.e("Load All", "success");
                        }, throwable -> Log.e("GetCategoriasList", "loadContent: " + throwable.getMessage()))
        );
    }


    @Override
    public void clickEvent(Categoria categoria) {
        viewModel.addCompositeDisposable(viewModel.getGetProductosPorCategoriaUseCase(categoria.getCodFamilia())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(productos -> {
                    ProductosExistenciaAdapter productosAdapter = new ProductosExistenciaAdapter(productos);
                    uiBind.listProductosByCategoriaLayout.setAdapter(productosAdapter);
                }, throwable -> Log.e("GetProductosXCategoria", "clickEvent: " + throwable.getMessage())));
    }
}