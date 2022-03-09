package cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.motion.widget.MotionLayout;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import cu.gob.ith.R;
import cu.gob.ith.databinding.AlertDialogCantProductoLayoutBinding;
import cu.gob.ith.domain.model.Producto;

public class ProductosAdapter extends RecyclerView.Adapter<ItemProductoViewHolder> {

    private LayoutInflater layoutInflater;
    private List<Producto> productoList;
    private ViewGroup viewGroup;

    public ProductosAdapter(List<Producto> productoList) {
        this.productoList = productoList;
    }

    @NonNull
    @Override
    public ItemProductoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        viewGroup = parent;
        if (layoutInflater == null)
            layoutInflater = LayoutInflater.from(parent.getContext());
        return new ItemProductoViewHolder(
                DataBindingUtil.inflate(layoutInflater,
                        R.layout.item_productos, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemProductoViewHolder holder, int position) {
        Producto producto = productoList.get(position);
        holder.bind(producto);

        /*holder.getUiBind().buttonAddLayout.exitCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getRotation() == 315) {
                    holder.getUiBind().buttonAddLayout.totalSelectedTV.setText("0.0");
                } else {
                    holder.getUiBind().buttonAddLayout.totalSelectedTV.setText("1.0");
                }
            }
        });*/
        /*holder.getUiBind().buttonAddLayout.motionButtonAddML.addTransitionListener(new MotionLayout.TransitionListener() {
            @Override
            public void onTransitionStarted(MotionLayout motionLayout, int startId, int endId) {

            }

            @Override
            public void onTransitionChange(MotionLayout motionLayout, int startId, int endId, float progress) {

            }

            @Override
            public void onTransitionCompleted(MotionLayout motionLayout, int currentId) {
                if (motionLayout.getProgress() > 0.0)
                    producto.setCantProducto(1.0);
                else
                    producto.setCantProducto(0.0);
            }

            @Override
            public void onTransitionTrigger(MotionLayout motionLayout, int triggerId, boolean positive, float progress) {

            }
        });*/

        holder.getUiBind().buttonAddLayout.exitCL.setOnClickListener(v->{
            if(producto.getCantProducto() == 0.0) {
                producto.setCantProducto(1.0);
                holder.getUiBind().buttonAddLayout.motionButtonAddML.transitionToEnd();
                notifyItemChanged(position);
            }else {
                producto.setCantProducto(0.0);
                holder.getUiBind().buttonAddLayout.motionButtonAddML.transitionToStart();
                notifyItemChanged(position);
            }
        });

        holder.getUiBind().buttonAddLayout.totalSelectedTV.setOnClickListener(v -> createAlertDialog(holder, producto));
        holder.getUiBind().buttonAddLayout.addButtonIV.setOnClickListener(v -> {
            addProduct(holder, producto);
            notifyItemChanged(position);
        });
        holder.getUiBind().buttonAddLayout.restButtonIV.setOnClickListener(v -> {
            lessProduct(holder, producto);
            notifyItemChanged(position);
        });

    }

    @Override
    public int getItemCount() {
        return productoList == null ? 0 : productoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void setHasStableIds(boolean hasStableIds) {
        super.setHasStableIds(true);
    }

    private void createAlertDialog(ItemProductoViewHolder holder, Producto producto) {

        AlertDialogCantProductoLayoutBinding alertUIBind = DataBindingUtil.inflate(layoutInflater,
                R.layout.alert_dialog_cant_producto_layout,
                viewGroup, false);
        AlertDialog dialog = new AlertDialog.Builder(holder.getUiBind().getRoot().getContext())
                .setView(alertUIBind.getRoot())
                .create();
        alertUIBind.cantProductoET.setText(holder.getUiBind().buttonAddLayout.totalSelectedTV.getText());
        alertUIBind.cancelBtn.setOnClickListener(v -> dialog.dismiss());
        alertUIBind.acceptBtn.setOnClickListener(v -> {
                    String textValue = alertUIBind.cantProductoET.getText().toString();
                    if (textValue.isEmpty()) {
                        producto.setCantProducto(1.0);
                        holder.getUiBind().buttonAddLayout.totalSelectedTV.setText("1.0");
                    } else {
                        double value = Double.parseDouble(textValue);
                        producto.setCantProducto(value);
                        holder.getUiBind().buttonAddLayout.totalSelectedTV.setText(String.valueOf(value));
                    }
                    dialog.dismiss();

                }
        );

        dialog.setCancelable(false);
        dialog.show();

    }

    private void addProduct(ItemProductoViewHolder holder, Producto producto) {
        String textValue = holder.getUiBind().buttonAddLayout.totalSelectedTV.getText().toString();
        double value = Double.parseDouble(textValue) + 1;
        producto.setCantProducto(value);
    }

    private void lessProduct(ItemProductoViewHolder holder, Producto producto) {
        String textValue = holder.getUiBind().buttonAddLayout.totalSelectedTV.getText().toString();
        double value = Double.parseDouble(textValue) - 1;
        producto.setCantProducto(value);
    }
}
