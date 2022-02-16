package cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.Producto;

public class ProductosAdapter extends RecyclerView.Adapter<ItemProductoViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Producto> productoList;

    public ProductosAdapter(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @NonNull
    @Override
    public ItemProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        return new ItemProductoViewHolder(
                DataBindingUtil.inflate(layoutInflater,
                        R.layout.item_productos,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemProductoViewHolder holder, int position) {
        holder.bind(productoList.get(position));
    }

    @Override
    public int getItemCount() {
        return productoList == null ? 0 : productoList.size();
    }
}
