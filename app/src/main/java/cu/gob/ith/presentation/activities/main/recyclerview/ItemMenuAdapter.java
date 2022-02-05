package cu.gob.ith.presentation.activities.main.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.presentation.model.ItemMenuNavView;

public class ItemMenuAdapter extends RecyclerView.Adapter<ItemMenuViewHolder> {

    private LayoutInflater inflater;
    public List<ItemMenuNavView> list;

    public ItemMenuAdapter(List<ItemMenuNavView> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ItemMenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (inflater == null)
            inflater = LayoutInflater.from(parent.getContext());
        return new ItemMenuViewHolder(
                DataBindingUtil.inflate(inflater, R.layout.item_menu_nav_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemMenuViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }
}
