package cu.gob.ith.domain.mappers;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import cu.gob.ith.data.api.model.ApiProducto;
import cu.gob.ith.domain.model.Producto;

public class TransformApiProductoToProducto {

    public static Producto map(ApiProducto apiProducto) {
        double precio = Double.parseDouble(apiProducto.getPv());
        BigDecimal value = new BigDecimal(precio, new MathContext(2, RoundingMode.HALF_UP));
        return new Producto(apiProducto.getDescripcion(),
                apiProducto.getReferencia(),
                apiProducto.getCodUm(),
                apiProducto.getCodFamilia(),
                apiProducto.getNombreFamilia(),
                value.toString());
    }

    public static List<Producto> mapList(List<ApiProducto> apiProductoList) {
        List<Producto> productoList = new ArrayList<>();
        for (ApiProducto apiProducto : apiProductoList
        ) {
            productoList.add(map(apiProducto));
        }
        return productoList;
    }
}
