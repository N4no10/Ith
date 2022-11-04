package cu.gob.ith.data.repository;

import android.util.Log;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import cu.gob.ith.common.URLEnum;
import cu.gob.ith.data.api.model.ApiApkVersion;
import cu.gob.ith.data.api.model.ApiCategoria;
import cu.gob.ith.data.api.model.ApiListDetallesPedido;
import cu.gob.ith.data.api.model.ApiListPedidos;
import cu.gob.ith.data.api.model.ApiListProductos;
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
import okhttp3.ResponseBody;

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
    public Completable recuperarPass(ApiLoginBody apiLoginBody) {
        return dataSourceRemote.recuperarPass(apiLoginBody);
    }

    @Override
    public Observable<List<ApiCategoria>> getCategorias(URLEnum urlEnum) {
        return dataSourceRemote.getCategorias(urlEnum)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<ApiProducto>> getProductos(URLEnum urlEnum, String codFamilia) {
        return dataSourceRemote.getProductos(urlEnum, codFamilia)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<List<ApiProducto>> searchProductos(URLEnum urlEnum, Map<String, String> filter) {
        return dataSourceRemote.searchProductos(urlEnum, filter)
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
    public Observable<ApiListPedidos> getListPedidos(URLEnum urlEnum) {
        return dataSourceRemote.findListPedidos(urlEnum)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ApiListDetallesPedido> findAllDetallesPedidoDespachadosyNoDespachados(int numeroPedido) {
        return dataSourceRemote.findAllDetallesPedidoDespachadosyNoDespachados(numeroPedido)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ApiPedidoResponse> getPedidoById(int numeroPedido) {
        return dataSourceRemote.getPedidoById(numeroPedido)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ApiListProductos> getProductosByDocument(int numeroPedido) {
        return dataSourceRemote.getProductosByDocument(numeroPedido)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Completable updatePassword(Map<String, String> params) {
        return dataSourceRemote.updatePassword(params)
                .subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ApiApkVersion> getApkVersion(int lastVersion) {
        return dataSourceRemote.getApkVersion(lastVersion).subscribeOn(Schedulers.io());
    }

    @Override
    public Observable<ResponseBody> getApkFile(String url) {
        return dataSourceRemote.getApkFile(url);
    }
}
