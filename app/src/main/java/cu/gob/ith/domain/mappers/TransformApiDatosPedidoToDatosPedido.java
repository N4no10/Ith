package cu.gob.ith.domain.mappers;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import cu.gob.ith.common.Util;
import cu.gob.ith.data.api.model.ApiDatosPedido;
import cu.gob.ith.domain.model.DatosPedido;

public class TransformApiDatosPedidoToDatosPedido {
    public static DatosPedido map(ApiDatosPedido apiDatosPedido) {
        Log.e("mao DatosPedido","map " + apiDatosPedido);
        return new DatosPedido(apiDatosPedido.getNumber(),
                apiDatosPedido.getProvider(),
                apiDatosPedido.getCodeITH(),
                apiDatosPedido.getAddressITH(),
                apiDatosPedido.getCode(),
                apiDatosPedido.getAddress(),
                Util.changeDateFormat(apiDatosPedido.getDate()),
                apiDatosPedido.getClient(),
                apiDatosPedido.getBankAccount(),
                apiDatosPedido.getSucursal(),
                apiDatosPedido.getImporteTotal(),
                apiDatosPedido.getTipoCliente(),
                apiDatosPedido.getDescripcionTipoCliente(),
                TransformApiProductoToProducto
                        .mapToProductoInformeList(apiDatosPedido.getApiProductoList()),
                apiDatosPedido.getObservaciones(),
                apiDatosPedido.getDetalle(),
                apiDatosPedido.getEstado());

    }

    public static DatosPedido mapSecondConstructor(ApiDatosPedido apiDatosPedido) {
        return new DatosPedido(apiDatosPedido.getNumber(),
                Util.changeDateFormat(apiDatosPedido.getDate()),
                Util.changeDateFormat(apiDatosPedido.getDateDespacho()),
                apiDatosPedido.getClient());
    }

    public static List<DatosPedido> mapList(List<ApiDatosPedido> apiDatosPedidoList) {
        List<DatosPedido> datosPedidoList = new ArrayList<>();
        for (ApiDatosPedido apiDatosPedidos : apiDatosPedidoList)
            datosPedidoList.add(map(apiDatosPedidos));

        return datosPedidoList;
    }

    public static List<DatosPedido> mapListSecondConstructor(List<ApiDatosPedido> apiDatosPedidoList) {
        List<DatosPedido> datosPedidoList = new ArrayList<>();

        for (ApiDatosPedido apiDatosPedidos : apiDatosPedidoList
        ) {
            datosPedidoList.add(mapSecondConstructor(apiDatosPedidos));
        }
        return datosPedidoList;
    }
}
