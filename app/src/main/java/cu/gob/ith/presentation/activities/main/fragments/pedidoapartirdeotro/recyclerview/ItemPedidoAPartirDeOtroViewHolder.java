package cu.gob.ith.presentation.activities.main.fragments.pedidoapartirdeotro.recyclerview;

import androidx.annotation.NonNull;

import cu.gob.ith.databinding.ItemPedidoAPartirDeOtroBinding;
import cu.gob.ith.databinding.ItemPedidoIncompletoBinding;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;

public class ItemPedidoAPartirDeOtroViewHolder extends ViewHolderGlobal<DatosPedido, ItemPedidoAPartirDeOtroBinding> {
    public ItemPedidoAPartirDeOtroViewHolder(@NonNull ItemPedidoAPartirDeOtroBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(DatosPedido param) {
        getUiBind().setPedido(param);
        super.bind(param);
    }
}
