package cu.gob.ith.presentation.activities.main.fragments.pedidoapartirdeotro.recyclerview;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import cu.gob.ith.R;
import cu.gob.ith.domain.model.DatosPedido;
import cu.gob.ith.presentation.activities.main.fragments.completarpedido.recyclerview.ItemPedidoIncompletoViewHolder;
import cu.gob.ith.presentation.activities.main.fragments.mispedidos.OnClickDelegateRV;

public class PedidosAPartirDeOtroAdapter extends RecyclerView.Adapter<ItemPedidoAPartirDeOtroViewHolder> {

    private LayoutInflater layoutInflater;
    private List<DatosPedido> datosPedidoList;
    private OnClickDelegateRV onClickDelegateRV;

    public PedidosAPartirDeOtroAdapter(List<DatosPedido> datosPedidoList, OnClickDelegateRV onClickDelegateRV) {
        this.datosPedidoList = datosPedidoList;
        this.onClickDelegateRV = onClickDelegateRV;
    }

    @NonNull
    @Override
    public ItemPedidoAPartirDeOtroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());

        return new ItemPedidoAPartirDeOtroViewHolder(DataBindingUtil.inflate(layoutInflater,
                R.layout.item_pedido_a_partir_de_otro, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemPedidoAPartirDeOtroViewHolder holder, int position) {
        holder.bind(datosPedidoList.get(position));
        holder.getUiBind().getRoot().setOnClickListener(v -> onClickDelegateRV.onclickItem(datosPedidoList
                .get(position).getNumber()));

        holder.getUiBind().imageLL.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("pedidoId", Integer.parseInt(datosPedidoList.get(position).getNumber()));
            bundle.putInt("action", R.id.action_pedidoListFragment_to_menuFragment);
            Log.e("ID", "ID " + bundle.getInt("pedidoId"));

            //  Navigation.findNavController(v).navigate(R.id.action_pedidoAPartirDeOtroFragment_to_pedidoListFragment,bundle);
            Navigation.findNavController(v).navigate(R.id.pedidoListFragment,bundle);
        });
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
