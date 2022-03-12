package cu.gob.ith.domain.mappers;

import java.util.ArrayList;
import java.util.List;

import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiPedido;
import cu.gob.ith.domain.model.Pedido;
import cu.gob.ith.domain.model.login.LoginBody;

public class TransformPedidoToApiPedido {

    public static ApiPedido map(Pedido pedido) {
        return new ApiPedido(
                pedido.getReference(),
                pedido.getPvp(),
                pedido.getCantidad(),
                pedido.getImporte());
    }

    public static List<ApiPedido> mapList(List<Pedido> pedidoList) {

        List<ApiPedido> apiPedidoList = new ArrayList<>();

        for (Pedido pedido : pedidoList
        ) {
            apiPedidoList.add(map(pedido));
        }
        return apiPedidoList;
    }
}
