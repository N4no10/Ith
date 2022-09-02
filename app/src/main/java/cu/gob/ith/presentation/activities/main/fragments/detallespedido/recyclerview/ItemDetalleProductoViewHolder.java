package cu.gob.ith.presentation.activities.main.fragments.detallespedido.recyclerview;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import cu.gob.ith.databinding.ItemProductosDetallesPedidoBinding;
import cu.gob.ith.databinding.ItemProductosDetallesPedidoIncompletoBinding;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;

public class ItemDetalleProductoViewHolder extends ViewHolderGlobal<Producto, ViewDataBinding> {
    public ItemDetalleProductoViewHolder(@NonNull ViewDataBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(Producto param) {
        if (getUiBind() instanceof ItemProductosDetallesPedidoBinding)
            ((ItemProductosDetallesPedidoBinding) getUiBind()).setProducto(param);

        if (getUiBind() instanceof ItemProductosDetallesPedidoIncompletoBinding)
            ((ItemProductosDetallesPedidoIncompletoBinding) getUiBind()).setProducto(param);

        super.bind(param);
    }
}
