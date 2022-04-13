package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc4.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1.recyclerview.ItemPedidoViewHolder;

public class PedidoDespachadoAdapter extends RecyclerView.Adapter<ItemPedidoDespachadoViewHolder> {

    private LayoutInflater layoutInflater;
    private List<DatosPedido> datosPedidoList;

    public PedidoDespachadoAdapter(List<DatosPedido> datosPedidoList) {
        this.datosPedidoList = datosPedidoList;
    }

    @NonNull
    @Override
    public ItemPedidoDespachadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        return new ItemPedidoDespachadoViewHolder(DataBindingUtil.inflate(layoutInflater, R.layout.item_list_pedido_doble_fecha, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPedidoDespachadoViewHolder holder, int position) {
        holder.bind(datosPedidoList.get(position));

    }

    @Override
    public int getItemCount() {
        return datosPedidoList == null ? 0 : datosPedidoList.size();
    }
}
