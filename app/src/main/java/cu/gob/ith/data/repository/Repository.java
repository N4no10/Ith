package cu.gob.ith.data.repository;

import java.util.List;
import java.util.Map;

import cu.gob.ith.data.api.model.ApiCategoria;
import cu.gob.ith.data.api.model.ApiListDetallesPedido;
import cu.gob.ith.data.api.model.ApiListPedidos;
import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiLoginResponse;
import cu.gob.ith.data.api.model.ApiPedido;
import cu.gob.ith.data.api.model.ApiPedidoResponse;
import cu.gob.ith.data.api.model.ApiProducto;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface Repository {

    Observable<ApiLoginResponse> login(ApiLoginBody apiLoginBody);

    Observable<List<ApiCategoria>> getCategorias();

    Observable<List<ApiProducto>> getProductos(String codFamilia);

    Observable<ApiPedidoResponse> requestOrder(List<ApiPedido> apiPedidoList);

    Observable<ApiListPedidos> filterListPedidos(Map<String, Object> params);

    Observable<ApiListPedidos> filterListPedidosDespachados();

    Observable<ApiListPedidos> filterListPedidosDespachadosFacturados();

    Observable<ApiListDetallesPedido> findAllDetallesPedidoDespachadosyNoDespachados(int numeroPedido);

}
