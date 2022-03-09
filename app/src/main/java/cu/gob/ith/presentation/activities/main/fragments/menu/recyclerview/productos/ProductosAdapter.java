package cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
    private ManageProductListUtil manageProductListUtil;

    public ProductosAdapter(List<Producto> productoList,
                            ManageProductListUtil manageProductListUtil) {
        manageProductListUtil.updateApiList(productoList);
        this.productoList = productoList;
        this.manageProductListUtil = manageProductListUtil;
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

        holder.getUiBind().buttonAddLayout.exitCL.setOnClickListener(v -> {
            if (producto.getCantProducto() == 0.0) {
                producto.setCantProducto(1.0);
                manageProductListUtil.addProduct(producto);
                holder.getUiBind().buttonAddLayout.motionButtonAddML.transitionToEnd();
            } else {
                producto.setCantProducto(0.0);
                manageProductListUtil.deleteProduct(producto);
                holder.getUiBind().buttonAddLayout.motionButtonAddML.transitionToStart();
            }
            notifyItemChanged(position);
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
                    manageProductListUtil.updateProduct(producto);

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
        manageProductListUtil.updateProduct(producto);
    }

    private void lessProduct(ItemProductoViewHolder holder, Producto producto) {
        String textValue = holder.getUiBind().buttonAddLayout.totalSelectedTV.getText().toString();
        double value = Double.parseDouble(textValue) - 1;
        producto.setCantProducto(value);
        manageProductListUtil.updateProduct(producto);
    }
}
