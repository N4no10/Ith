package cu.gob.ith.presentation.activities.main.fragments.mispedidos.opc1.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.InformePedido;

public class PedidosAdapter extends RecyclerView.Adapter<ItemPedidoViewHolder> {

    private LayoutInflater layoutInflater;
    private List<InformePedido> informePedidoList;

    public PedidosAdapter(List<InformePedido> informePedidoList) {
        this.informePedidoList = informePedidoList;
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
         holder.bind(informePedidoList.get(position).getDatosPedido());
    }

    @Override
    public int getItemCount() {
        return informePedidoList == null ? 0 : informePedidoList.size();
    }
}
