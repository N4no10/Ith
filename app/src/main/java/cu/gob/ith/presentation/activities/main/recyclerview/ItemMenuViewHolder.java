package cu.gob.ith.presentation.activities.main.recyclerview;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cu.gob.ith.databinding.ItemMenuNavViewBinding;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;
import cu.gob.ith.presentation.model.ItemMenuNavView;

public class ItemMenuViewHolder extends ViewHolderGlobal<ItemMenuNavView, ItemMenuNavViewBinding> {
    public ItemMenuViewHolder(@NonNull ItemMenuNavViewBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(ItemMenuNavView param) {
        super.bind(param);
        getUiBind().setTitleItemMenu(param.getTitle());
        getUiBind().setIcono(param.getIcono());

    }
}
