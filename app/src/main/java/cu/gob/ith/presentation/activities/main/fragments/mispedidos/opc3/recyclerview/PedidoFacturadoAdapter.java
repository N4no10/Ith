package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc3.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import javax.inject.Inject;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.OnClickDelegateRV;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc4.recyclerview.ItemPedidoDespachadoViewHolder;

public class PedidoFacturadoAdapter extends RecyclerView.Adapter<ItemPedidoFacturadoViewHolder> {

    private LayoutInflater layoutInflater;
    private List<DatosPedido> datosPedidoList;
    private OnClickDelegateRV onClickDelegateRV;

    public PedidoFacturadoAdapter(List<DatosPedido> datosPedidoList, OnClickDelegateRV onClickDelegateRV) {
        this.datosPedidoList = datosPedidoList;
        this.onClickDelegateRV = onClickDelegateRV;
    }

    @NonNull
    @Override
    public ItemPedidoFacturadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        return new ItemPedidoFacturadoViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_list_pedido_doble_fecha, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPedidoFacturadoViewHolder holder, int position) {
        holder.bind(datosPedidoList.get(position));
        holder.getUiBind().getRoot().setOnClickListener(v -> onClickDelegateRV.onclickItem(datosPedidoList
                .get(position).getNumber()));

    }

    @Override
    public int getItemCount() {
        return datosPedidoList == null ? 0 : datosPedidoList.size();
    }

    public void setOnClickDelegateRV(OnClickDelegateRV onClickDelegateRV) {
        this.onClickDelegateRV = onClickDelegateRV;
    }
}
