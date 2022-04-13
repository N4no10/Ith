package cu.gob.ith.presentation.activities.main.fragments.detallespedido.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.Producto;

public class ItemDetalleProductoAdapter extends RecyclerView.Adapter<ItemDetalleProductoViewHolder> {

    private List<Producto> productoList;
    private LayoutInflater layoutInflater;

    public ItemDetalleProductoAdapter(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @NonNull
    @Override
    public ItemDetalleProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        return new ItemDetalleProductoViewHolder(
                DataBindingUtil.inflate(layoutInflater,
                        R.layout.item_productos_detalles_pedido, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemDetalleProductoViewHolder holder, int position) {
        holder.bind(productoList.get(position));

    }

    @Override
    public int getItemCount() {
        return productoList == null ? 0 : productoList.size();
    }
}
