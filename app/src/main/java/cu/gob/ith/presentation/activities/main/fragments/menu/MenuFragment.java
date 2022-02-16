package cu.gob.ith.presentation.activities.main.fragments.menu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;
import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.databinding.FragmentMenuBinding;
import cu.gob.ith.domain.model.Categoria;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.categorias.CategoriasAdapter;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.categorias.ItemCategoriaClick;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos.ProductosAdapter;
import cu.gob.ith.presentation.activities.main.fragments.menu.viewmodel.MenuFragmentViewModel;
import cu.gob.ith.presentation.activities.main.ui.viewmodel.MainActivityViewModel;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MenuFragment extends Fragment implements ItemCategoriaClick {

    private FragmentMenuBinding uiBind;
    private MenuFragmentViewModel viewModel;
    private MainActivityViewModel mainActivityViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        uiBind = DataBindingUtil.inflate(inflater, R.layout.fragment_menu, container, false);
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
    }

    public void initViewModel() {
        viewModel = new ViewModelProvider(this).get(MenuFragmentViewModel.class);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
    }

    private void loadContent() {
        List<Categoria> categoriaList = new ArrayList<Categoria>() {{
            add(new Categoria("Vegetales"));
            add(new Categoria("Aceites y Grasa"));
            add(new Categoria("Frutas"));
            add(new Categoria("Bebidas"));
            add(new Categoria("Dulces"));
            add(new Categoria("Panes"));

        }};
        CategoriasAdapter categoriasAdapter = new CategoriasAdapter(categoriaList);
        categoriasAdapter.setItemCategoriaClick(this);
        viewModel.setLoadedData(true);
        uiBind.listCategoriaLayout.setAdapter(categoriasAdapter);
        uiBind.listCategoriaLayout.listCategoriaRV.setLayoutManager(
                new LinearLayoutManager(getContext(),
                        LinearLayoutManager.HORIZONTAL,
                        false));
    }


    @Override
    public void clickEvent(Categoria categoria) {
        Log.e("AAA", "AAA");

        List<Producto> productoList = new ArrayList<>();
        for (int i = 0 ; i < 200 ; i++)
            productoList.add(new Producto("Producto No. " + i ));
        ProductosAdapter productosAdapter = new ProductosAdapter(productoList);
        uiBind.listProductosByCategoriaLayout.setAdapter(productosAdapter);
    }
}