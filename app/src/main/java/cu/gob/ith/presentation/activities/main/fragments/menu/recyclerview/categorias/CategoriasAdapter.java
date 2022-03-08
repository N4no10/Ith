package cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.categorias;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.Categoria;

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

    public void setSelectedCategoria(Categoria categoria) {
        categoria.setSelected(true);
        for (Categoria c : categoriaList)
            if (!c.getNombreFamilia().equals(categoria.getNombreFamilia()) && c.isSelected())
                c.setSelected(false);
    }
}
