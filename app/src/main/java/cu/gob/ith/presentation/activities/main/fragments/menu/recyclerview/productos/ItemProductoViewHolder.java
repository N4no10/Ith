package cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos;

import androidx.annotation.NonNull;

import cu.gob.ith.databinding.ItemProductosBinding;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;

public class ItemProductoViewHolder extends ViewHolderGlobal<Producto, ItemProductosBinding> {
    public ItemProductoViewHolder(@NonNull ItemProductosBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(Producto param) {
        getUiBind().setProducto(param);
        super.bind(param);
    }
}
