package cu.gob.ith.presentation.activities.main.recyclerview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.presentation.model.ItemMenuNavView;

public class ItemMenuAdapter extends RecyclerView.Adapter<ItemMenuViewHolder> {

    private LayoutInflater inflater;
    public List<ItemMenuNavView> list;
    private ClickItemMenuInterface clickItemMenuInterface;
    public boolean isSubMenuList;

    public ItemMenuAdapter(List<ItemMenuNavView> list, boolean isSubMenuList) {
        this.list = list;
        this.isSubMenuList = isSubMenuList;
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
        holder.getUiBind().setIsSubMenu(isSubMenuList);
        ItemMenuNavView itemMenuNavView = list.get(position);
        holder.bind(itemMenuNavView);

        if (itemMenuNavView.getListSubMenu() != null) {
            if(holder.getUiBind().getAdapterSubMenu() == null) {
                ItemMenuAdapter itemMenuAdapter = new ItemMenuAdapter(new ArrayList<>(), true);
                itemMenuAdapter.setClickItemMenuInterface(clickItemMenuInterface);
                holder.getUiBind().setAdapterSubMenu(itemMenuAdapter);
            }

            holder.getUiBind().getAdapterSubMenu().loadData(itemMenuNavView.getListSubMenu());
        }else holder.getUiBind().getRoot().setOnClickListener(v -> clickItemMenuInterface
                .clickItemMenu(list.get(position)));
    }

    @Override
    public int getItemCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

    public void setClickItemMenuInterface(ClickItemMenuInterface clickItemMenuInterface) {
        this.clickItemMenuInterface = clickItemMenuInterface;
    }

    public void loadData(List<ItemMenuNavView> newListMenu) {
        this.list.clear();
        this.list.addAll(newListMenu);
        notifyDataSetChanged();
    }

    public void selectedItemMenu(ItemMenuNavView param) {
        for (ItemMenuNavView itemMenuView : list) {
            if (itemMenuView.isSelected())
                itemMenuView.setSelected(false);

            if (itemMenuView.getListSubMenu() != null && !itemMenuView.getListSubMenu().isEmpty())
                for (ItemMenuNavView itemMenuView2 : itemMenuView.getListSubMenu())
                    itemMenuView2.setSelected(itemMenuView2.getTitle().equals(param.getTitle()));
        }

        param.setSelected(true);
        notifyDataSetChanged();
    }
}
