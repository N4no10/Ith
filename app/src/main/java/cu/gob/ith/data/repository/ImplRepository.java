package cu.gob.ith.data.repository;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import cu.gob.ith.data.api.model.ApiCategoria;
import cu.gob.ith.data.api.model.ApiListDetallesPedido;
import cu.gob.ith.data.api.model.ApiListPedidos;
import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiLoginResponse;
import cu.gob.ith.data.api.model.ApiPedido;
import cu.gob.ith.data.api.model.ApiPedidoResponse;
import cu.gob.ith.data.api.model.ApiProducto;
import cu.gob.ith.data.repository.datasources.DataSourcePreferences;
import cu.gob.ith.data.repository.datasources.DataSourceRemote;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class ImplRepository implements Repository {

    private final DataSourceRemote dataSourceRemote;
    private final DataSourcePreferences dataSourcePreferences;


    @Inject
    public ImplRepository(DataSourceRemote dataSourceRemote, DataSourcePreferences dataSourcePreferences) {
        this.dataSourceRemote = dataSourceRemote;
        this.dataSourcePreferences = dataSourcePreferences;
    }

    @Override
    public Observable<ApiLoginResponse> login(ApiLoginBody apiLoginBody) {
        return dataSourceRemote.login(apiLoginBody)
                .subscribeOn(Schedulers.io())
                .doOnNext(dataSourcePreferences::saveApiLoginBodyResponsePreference
                );
    }

    @Override
    public Observable<List<ApiCategoria>> getCategorias() {
        return dataSourceRemote.getCategorias()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<ApiProducto>> getProductos(String codFamilia) {
        return dataSourceRemote.getProductos(codFamilia)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ApiPedidoResponse> requestOrder(List<ApiPedido> apiPedidoList) {
        return dataSourceRemote.requestOrder(apiPedidoList)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ApiListPedidos> filterListPedidos(Map<String, Object> params) {
        return dataSourceRemote.filterListPedidos(params)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ApiListPedidos> filterListPedidosDespachados() {
        return dataSourceRemote.filterListPedidosDespachados()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ApiListPedidos> filterListPedidosDespachadosFacturados() {
        return dataSourceRemote.filterListPedidosDespachadosFacturados()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ApiListDetallesPedido> findAllDetallesPedidoDespachadosyNoDespachados(int numeroPedido) {
        return dataSourceRemote.findAllDetallesPedidoDespachadosyNoDespachados(numeroPedido)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ApiListPedidos> filterListPedidosPendientesDespachar() {
        return dataSourceRemote.filterListPedidosPendientesDespachar()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ApiListPedidos> filterListPedidosCancelados() {
        return dataSourceRemote.filterListPedidosCancelados()
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ApiPedidoResponse> getPedidoById(int numeroPedido) {
        return dataSourceRemote.getPedidoById(numeroPedido)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable updatePassword(Map<String, String> params) {
        return dataSourceRemote.updatePassword(params)
                .subscribeOn(Schedulers.io());
    }
}
