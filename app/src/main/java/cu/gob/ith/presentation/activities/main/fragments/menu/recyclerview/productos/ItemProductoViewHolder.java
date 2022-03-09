package cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;

import cu.gob.ith.databinding.ItemProductosBinding;
import cu.gob.ith.domain.model.Producto;
import cu.gob.ith.presentation.activities.main.common.ViewHolderGlobal;

public class ItemProductoViewHolder extends ViewHolderGlobal<Producto, ItemProductosBinding> {
    public ItemProductoViewHolder(@NonNull ItemProductosBinding uiBind) {
        super(uiBind);
    }

    @Override
    public void bind(Producto param) {
        getUiBind().setProducto(param);
        Log.e("cant", "cant " + param.getCantProducto());
        if (param.getCantProducto() == 0.0) {
            getUiBind().buttonAddLayout.totalSelectedTV.setText("1.0");
            getUiBind().buttonAddLayout.motionButtonAddML.transitionToStart();

        } else {
            getUiBind().buttonAddLayout.totalSelectedTV.setText("" + param.getCantProducto());
            getUiBind().buttonAddLayout.motionButtonAddML.transitionToEnd();


        }

        super.bind(param);
    }
}
