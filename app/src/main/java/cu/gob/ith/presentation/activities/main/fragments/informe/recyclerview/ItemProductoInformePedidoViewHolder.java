package cu.gob.ith.presentation.activities.main.fragments.informe.recyclerview;

import androidx.annotation.NonNull;

import cu.gob.ith.databinding.ItemProductosInformePedidoBinding;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;

public class ItemProductoInformePedidoViewHolder extends ViewHolderGlobal<Producto, ItemProductosInformePedidoBinding> {
    public ItemProductoInformePedidoViewHolder(@NonNull ItemProductosInformePedidoBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(Producto param) {
        getUiBind().setProducto(param);

        super.bind(param);
    }


}
