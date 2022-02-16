package cu.gob.ith.presentation.activities.main.recyclerview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cu.gob.ith.databinding.ItemMenuNavViewBinding;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;
import cu.gob.ith.presentation.model.ItemMenuNavView;

public class ItemMenuViewHolder extends ViewHolderGlobal<ItemMenuNavView, ItemMenuNavViewBinding>/*RecyclerView.ViewHolder*/ {
    public ItemMenuViewHolder(@NonNull ItemMenuNavViewBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(ItemMenuNavView param) {
        super.bind(param);
        getUiBind().setTitleItemMenu(param.getTitle());
        getUiBind().setIcono(param.getIcono());
    }

    /*private final ItemMenuNavViewBinding uiBind;

    public ItemMenuViewHolder(ItemMenuNavViewBinding uiBind) {
        super(uiBind.getRoot());
        this.uiBind = uiBind;
    }

    public void bind(final ItemMenuNavView param){
        uiBind.setTitleItemMenu(param.getTitle());
        uiBind.setIcono(param.getIcono());
        uiBind.executePendingBindings();
    }*/
}
