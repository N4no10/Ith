package cu.gob.ith.presentation.activities.main.fragments.productos.existencia.recyclerview;

import androidx.annotation.NonNull;

import cu.gob.ith.databinding.ItemProductosExistenciaBinding;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;

public class ItemProductoExistenciaViewHolder extends ViewHolderGlobal<Producto, ItemProductosExistenciaBinding> {
    public ItemProductoExistenciaViewHolder(@NonNull ItemProductosExistenciaBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(Producto param) {
        getUiBind().setProducto(param);

        super.bind(param);
    }
}
