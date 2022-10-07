package cu.gob.ith.domain.mappers;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import cu.gob.ith.data.api.model.ApiListProductos;
import cu.gob.ith.data.api.model.ApiProducto;
import cu.gob.ith.domain.model.Producto;

public class TransformApiProductoToProducto {

    public static Producto map(ApiProducto apiProducto) {
        BigDecimal bd = BigDecimal.valueOf(apiProducto.getPv()).setScale(2, RoundingMode.HALF_UP);
        float finalValue = bd.floatValue();
        Producto producto = new Producto(apiProducto.getDescripcion(),
                apiProducto.getReferencia(),
                apiProducto.getCodUm(),
                apiProducto.getCodFamilia(),
                apiProducto.getNombreFamilia(),
                finalValue, apiProducto.getCantidad());
        producto.setDisponibilidadProducto(apiProducto.getDisponibilidad());
        return producto;
    }

    public static Producto mapToProductoInforme(ApiProducto apiProducto) {
        BigDecimal bd = BigDecimal.valueOf(apiProducto.getPv()).setScale(2, RoundingMode.HALF_UP);
        float finalValue = bd.floatValue();
        return new Producto(apiProducto.getDescripcion(), apiProducto.getReferencia(),
                apiProducto.getCodUm(), apiProducto.getPv(),
                apiProducto.getCantidad(), apiProducto.getImporte());
    }

    public static Producto mapToDetalleProducto(ApiProducto apiProducto) {
        BigDecimal bd = BigDecimal.valueOf(apiProducto.getPv()).setScale(2, RoundingMode.HALF_UP);
        float finalValue = bd.floatValue();
        return new Producto(apiProducto.getDescripcion(), apiProducto.getReferencia(), apiProducto.getCodUm(),
                finalValue, apiProducto.getImporte(), apiProducto.getEstado(), apiProducto.getNumDocumento(),
                apiProducto.getCantPedida(), apiProducto.getCantDespachada());
    }

    public static List<Producto> mapApiListProductoToProductos(ApiListProductos apiListProductos) {
        List<Producto> productoList = new ArrayList<>();
        for (ApiProducto apiProducto : apiListProductos.getApiProductoList())
            productoList.add(mapToProductoDePedidoFaltante(apiProducto));
        return productoList;
    }

    public static Producto mapToProductoDePedidoFaltante(ApiProducto apiProducto) {
        return new Producto(apiProducto.getDescripcion(), apiProducto.getReferencia(),
                apiProducto.getCodUm(), apiProducto.getPv(), apiProducto.getCantNecesaria());
    }

    public static List<Producto> mapList(List<ApiProducto> apiProductoList) {
        List<Producto> productoList = new ArrayList<>();
        for (ApiProducto apiProducto : apiProductoList
        ) {
            productoList.add(map(apiProducto));
        }
        return productoList;
    }

    public static List<Producto> mapToProductoInformeList(List<ApiProducto> apiProductoList) {
        Log.e("mao DatosPedido", "mapToProductoInformeList " + apiProductoList);

        if (apiProductoList != null && !apiProductoList.isEmpty()) {
            List<Producto> productoList = new ArrayList<>();
            for (ApiProducto apiProducto : apiProductoList
            ) {
                productoList.add(mapToProductoInforme(apiProducto));
            }
            return productoList;
        }
        return null;
    }

    public static List<Producto> mapToDetalleProductoList(List<ApiProducto> apiProductoList) {
        if (apiProductoList != null && !apiProductoList.isEmpty()) {
            List<Producto> productoList = new ArrayList<>();
            for (ApiProducto apiProducto : apiProductoList
            ) {
                productoList.add(mapToDetalleProducto(apiProducto));
            }
            return productoList;
        }
        return null;
    }
}
