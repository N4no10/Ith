package cu.gob.ith.domain.mappers;

import android.util.Log;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import cu.gob.ith.data.api.model.ApiPedidoResponse;
import cu.gob.ith.data.api.model.ApiProducto;
import cu.gob.ith.domain.model.InformePedido;
import cu.gob.ith.domain.model.Producto;

public class TransformApiPedidoResponseToInformePedido {
    public static InformePedido map(ApiPedidoResponse apiPedidoResponse) {
        Log.e("informePedido map","informe " + apiPedidoResponse);
        return new InformePedido(apiPedidoResponse.isSuccess(),
                TransformApiDatosPedidoToDatosPedido.map(
                        apiPedidoResponse.getApiDatosPedido()
                ));
    }

    public static List<InformePedido> map(List<ApiPedidoResponse> apiPedidoResponseList) {
        List<InformePedido> informePedidoList = new ArrayList<>();

        for (ApiPedidoResponse apiPedidoResponse : apiPedidoResponseList
        ) {
            informePedidoList.add(map(apiPedidoResponse));
        }
        return informePedidoList;
    }
}
