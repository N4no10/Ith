package cu.gob.ith.presentation.activities.main.fragments.detallespedido.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.databinding.ItemProductosDetallesPedidoBinding;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;

public class ItemDetalleProductoAdapter extends RecyclerView.Adapter</*ItemDetalleProductoViewHolder*/ViewHolderGlobal<Producto, ?>> {
    private List<Producto> productoList;
    private LayoutInflater layoutInflater;
    private int layoutid;

    public ItemDetalleProductoAdapter(List<Producto> productoList, int layoutId) {
        this.productoList = productoList;
        this.layoutid = layoutId;
    }

    @NonNull
    @Override
    public /*ItemDetalleProductoViewHolder*/ViewHolderGlobal<Producto, ?> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        return new ItemDetalleProductoViewHolder(
                DataBindingUtil.inflate(layoutInflater,
                        layoutid, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderGlobal<Producto,?> holder, int position) {
        holder.bind(productoList.get(position));
    }

    @Override
    public int getItemCount() {
        return productoList == null ? 0 : productoList.size();
    }

    public void loadList(List<Producto> newListProductos) {
        productoList.clear();
        productoList.addAll(newListProductos);
        notifyDataSetChanged();
    }

    public List<Producto> getProductoList() {
        return productoList;
    }
}
