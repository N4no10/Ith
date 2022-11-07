package cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.categorias;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.Categoria;
import cu.gob.ith.domain.model.Producto;

public class CategoriasAdapter extends RecyclerView.Adapter<ItemCategoriaViewHolder> {

    private LayoutInflater inflater;
    private final List<Categoria> categoriaList;
    private ItemCategoriaClick itemCategoriaClick;


    public CategoriasAdapter(List<Categoria> categoriaList) {
        this.categoriaList = categoriaList;
    }

    @NonNull
    @Override
    public ItemCategoriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.getContext());
        return new ItemCategoriaViewHolder(DataBindingUtil.inflate(inflater,
                R.layout.item_categoria, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemCategoriaViewHolder holder, int position) {
        holder.bind(categoriaList.get(position));

        holder.getUiBind().frameLayout.setOnClickListener(v -> {
            setSelectedCategoria(categoriaList.get(position));
            itemCategoriaClick.clickEvent(categoriaList.get(position));
            notifyDataSetChanged();
        });
    }

    @Override
    public int getItemCount() {
        return categoriaList == null ? 0 : categoriaList.size();
    }

    public void setItemCategoriaClick(ItemCategoriaClick itemCategoriaClick) {
        this.itemCategoriaClick = itemCategoriaClick;
    }

    public void addNewListCategorias(List<Categoria> categorias){
        if(this.categoriaList != null && !this.categoriaList.isEmpty())
            this.categoriaList.clear();
        this.categoriaList.addAll(categorias);
    }

    public void setSelectedCategoria(Categoria categoria) {
        Log.e("categoria", "selected " + categoria.getNombreFamilia());
        categoria.setSelected(true);
        for (Categoria c : categoriaList)
            if (!c.getNombreFamilia().equals(categoria.getNombreFamilia()) && c.isSelected())
                c.setSelected(false);
    }

    public String codFamiliaFromCategoriaSelected(){
        for(Categoria c : categoriaList)
            if(c.isSelected())
                return c.getCodFamilia();

        return null;
    }

    public void clearCategoriaSelected() {
        for (int i = 0; i < categoriaList.size(); i++) {
            Log.e("Categoria","categoria " + categoriaList.get(i).getCodFamilia() + "   " + categoriaList.get(i).isSelected());
            if (categoriaList.get(i).isSelected()) {
                categoriaList.get(i).setSelected(false);
                notifyItemChanged(i);
                break;
            }
        }
    }

}
