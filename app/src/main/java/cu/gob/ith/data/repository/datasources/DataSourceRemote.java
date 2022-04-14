package cu.gob.ith.data.repository.datasources;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import cu.gob.ith.data.api.Api;
import cu.gob.ith.data.api.model.ApiCategoria;
import cu.gob.ith.data.api.model.ApiListDetallesPedido;
import cu.gob.ith.data.api.model.ApiListPedidos;
import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiLoginResponse;
import cu.gob.ith.data.api.model.ApiPedido;
import cu.gob.ith.data.api.model.ApiPedidoResponse;
import cu.gob.ith.data.api.model.ApiProducto;
import io.reactivex.rxjava3.core.Observable;

@Singleton
public class DataSourceRemote implements DataSourceApi {

    private final Api api;

    @Inject
    public DataSourceRemote(Api api) {
        this.api = api;
    }

    @Override
    public Observable<ApiLoginResponse> login(ApiLoginBody apiLoginBody) {
        return api.login(apiLoginBody);
    }

    @Override
    public Observable<List<ApiCategoria>> getCategorias() {
        return api.getCategorias();
    }

    @Override
    public Observable<List<ApiProducto>> getProductos(String codFamilia) {
        return api.getProductos(codFamilia);
    }

    @Override
    public Observable<ApiPedidoResponse> requestOrder(List<ApiPedido> apiPedidoList) {
        return api.requestOrder(apiPedidoList);
    }

    @Override
    public Observable<ApiListPedidos> filterListPedidos(Map<String, Object> params) {
        return api.filterListPedidos(params);
    }

    @Override
    public Observable<ApiListPedidos> filterListPedidosDespachados() {
        return api.filterListPedidosDespachados();
    }

    @Override
    public Observable<ApiListPedidos> filterListPedidosDespachadosFacturados() {
        return api.filterListPedidosDespachadosFacturados();
    }

    @Override
    public Observable<ApiListDetallesPedido> findAllDetallesPedidoDespachadosyNoDespachados(int numeroPedido) {
        return api.findAllDetallesPedidoDespachadosyNoDespachados(numeroPedido);
    }

    @Override
    public Observable<ApiListPedidos> filterListPedidosPendientesDespachar() {
        return api.filterListPedidosPendientesDespachar();
    }

    @Override
    public Observable<ApiPedidoResponse> getPedidoById(int numeroPedido) {
        return api.getPedidoById(numeroPedido);
    }
}
