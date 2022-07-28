package cu.gob.ith.presentation.activities.main.fragments.productos.existencia.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.Producto;

public class ProductosExistenciaAdapter extends RecyclerView.Adapter<ItemProductoExistenciaViewHolder> {

    private LayoutInflater layoutInflater;
    private final List<Producto> productoList;

    public ProductosExistenciaAdapter(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @NonNull
    @Override
    public ItemProductoExistenciaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        return new ItemProductoExistenciaViewHolder(
                DataBindingUtil.inflate(layoutInflater,
                        R.layout.item_productos_existencia, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemProductoExistenciaViewHolder holder, int position) {
        holder.bind(productoList.get(position));
    }

    @Override
    public int getItemCount() {
        return productoList == null ? 0 : productoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

}
