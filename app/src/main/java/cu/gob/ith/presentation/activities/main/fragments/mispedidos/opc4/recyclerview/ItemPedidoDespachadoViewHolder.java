package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc4.recyclerview;

import androidx.annotation.NonNull;

import cu.gob.ith.databinding.ItemListPedidoDobleFechaBinding;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;

public class ItemPedidoDespachadoViewHolder extends ViewHolderGlobal<DatosPedido, ItemListPedidoDobleFechaBinding> {

    public ItemPedidoDespachadoViewHolder(@NonNull ItemListPedidoDobleFechaBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(DatosPedido param) {
        getUiBind().setDatosPedido(param);
        super.bind(param);
    }

}
