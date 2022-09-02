package cu.gob.ith.presentation.activities.main.fragments.completarpedido.recyclerview;

import androidx.annotation.NonNull;

import cu.gob.ith.databinding.ItemPedidoIncompletoBinding;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;

public class ItemPedidoIncompletoViewHolder extends ViewHolderGlobal<DatosPedido, ItemPedidoIncompletoBinding> {
    public ItemPedidoIncompletoViewHolder(@NonNull ItemPedidoIncompletoBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(DatosPedido param) {
        getUiBind().setPedido(param);
        super.bind(param);
    }
}
