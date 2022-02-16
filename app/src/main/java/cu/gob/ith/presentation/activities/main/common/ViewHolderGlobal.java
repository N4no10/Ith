package cu.gob.ith.presentation.activities.main.common;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public abstract class ViewHolderGlobal<T, B extends ViewDataBinding> extends RecyclerView.ViewHolder {

    private final B uiBind;

    public ViewHolderGlobal(@NonNull B uiBind) {
        super(uiBind.getRoot());
        this.uiBind = uiBind;
    }

    public void bind(T param){
        uiBind.executePendingBindings();
    }

    public B getUiBind() {
        return uiBind;
    }
}
