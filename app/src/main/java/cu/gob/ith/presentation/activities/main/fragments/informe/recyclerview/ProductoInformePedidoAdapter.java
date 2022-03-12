package cu.gob.ith.presentation.activities.main.fragments.informe.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos.ItemProductoViewHolder;

public class ProductoInformePedidoAdapter extends RecyclerView.Adapter<ItemProductoInformePedidoViewHolder> {

    private List<Producto> productoList;
    private LayoutInflater layoutInflater;

    public ProductoInformePedidoAdapter(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @NonNull
    @Override
    public ItemProductoInformePedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        return new ItemProductoInformePedidoViewHolder(
                DataBindingUtil.inflate(layoutInflater,
                        R.layout.item_productos_informe_pedido, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemProductoInformePedidoViewHolder holder, int position) {
        holder.bind(productoList.get(position));
    }

    @Override
    public int getItemCount() {
        return productoList == null ? 0 : productoList.size();
    }
}
