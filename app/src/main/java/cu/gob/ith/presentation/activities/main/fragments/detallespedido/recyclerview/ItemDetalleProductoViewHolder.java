package cu.gob.ith.presentation.activities.main.fragments.detallespedido.recyclerview;

import androidx.annotation.NonNull;

import cu.gob.ith.databinding.ItemProductosDetallesPedidoBinding;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;

public class ItemDetalleProductoViewHolder extends ViewHolderGlobal<Producto, ItemProductosDetallesPedidoBinding> {
    public ItemDetalleProductoViewHolder(@NonNull ItemProductosDetallesPedidoBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(Producto param) {
        getUiBind().setProducto(param);
        super.bind(param);
    }
}
