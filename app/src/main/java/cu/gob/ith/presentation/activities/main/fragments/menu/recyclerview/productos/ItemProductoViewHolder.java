package cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.constraintlayout.motion.widget.MotionLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
        BigDecimal bd = BigDecimal.valueOf(param.getPv() * param.getCantProducto()).setScale(2, RoundingMode.HALF_UP);
        float finalValue = bd.floatValue();
        getUiBind().totalPagarPriceTV.setText("$" + finalValue);
        Log.e("cant", "cant " + param.getCantProducto());
        if (param.getCantProducto() == 0) {
            getUiBind().buttonAddLayout.totalSelectedTV.setText("1.0");
            getUiBind().buttonAddLayout.motionButtonAddML.setProgress(0);

        } else {
            getUiBind().buttonAddLayout.totalSelectedTV.setText("" + param.getCantProducto());
            getUiBind().buttonAddLayout.motionButtonAddML.setProgress(1);
        }

        super.bind(param);
    }
}
