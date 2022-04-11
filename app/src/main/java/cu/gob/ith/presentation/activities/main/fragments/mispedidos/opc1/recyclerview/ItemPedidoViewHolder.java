package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1.recyclerview;

import androidx.annotation.NonNull;

import cu.gob.ith.databinding.ItemPedidoBinding;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;

public class ItemPedidoViewHolder extends ViewHolderGlobal<DatosPedido, ItemPedidoBinding> {
    public ItemPedidoViewHolder(@NonNull ItemPedidoBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(DatosPedido param) {
        getUiBind().setPedido(param);
        super.bind(param);
    }
}
