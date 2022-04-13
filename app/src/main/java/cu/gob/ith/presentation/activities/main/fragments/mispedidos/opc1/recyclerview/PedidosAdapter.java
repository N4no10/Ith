package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.OnClickDelegateRV;

public class PedidosAdapter extends RecyclerView.Adapter<ItemPedidoViewHolder> {

    private LayoutInflater layoutInflater;
    private List<DatosPedido> datosPedidoList;
    private OnClickDelegateRV onClickDelegateRV;

    public PedidosAdapter(List<DatosPedido> datosPedidoList, OnClickDelegateRV onClickDelegateRV) {
        this.datosPedidoList = datosPedidoList;
        this.onClickDelegateRV = onClickDelegateRV;
    }

    @NonNull
    @Override
    public ItemPedidoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        return new ItemPedidoViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_pedido, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPedidoViewHolder holder, int position) {
        holder.bind(datosPedidoList.get(position));
        holder.getUiBind().getRoot().setOnClickListener(v -> onClickDelegateRV.onclickItem(datosPedidoList
                .get(position).getNumber()));
    }

    @Override
    public int getItemCount() {
        return datosPedidoList == null ? 0 : datosPedidoList.size();
    }

    public void loadList(List<DatosPedido> newListPedidos) {
        datosPedidoList.clear();
        datosPedidoList.addAll(newListPedidos);
        notifyDataSetChanged();
    }
}
