package cu.gob.ith.presentation.activities.main.fragments.menu.recyclerview.productos;

import java.util.List;

import cu.gob.ith.domain.model.Producto;

public interface ManageProductListUtil {

    boolean updateProduct(Producto producto);

    void addProduct(Producto producto);

    boolean deleteProduct(Producto producto);

    void updateApiList(List<Producto> apiProductoList);

}
