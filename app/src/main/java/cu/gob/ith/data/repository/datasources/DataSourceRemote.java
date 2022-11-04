package cu.gob.ith.data.repository.datasources;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import cu.gob.ith.common.URLEnum;
import cu.gob.ith.data.api.Api;
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
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;

@Singleton
public class DataSourceRemote implements DataSourceApi {

    private final Api api;

    @Inject
    public DataSourceRemote(Api api) {
        this.api = api;
    }

    @Override
    public Completable recuperarPass(ApiLoginBody apiLoginBody) {
        return api.recuperarPass(apiLoginBody);
    }

    @Override
    public Observable<ApiLoginResponse> login(ApiLoginBody apiLoginBody) {
        return api.login(apiLoginBody);
    }

    @Override
    public Observable<List<ApiCategoria>> getCategorias(URLEnum urlEnum) {
        return api.getCategorias(urlEnum == URLEnum.CATEGORIAS ? Api.urlCategorias : Api.urlCategoriasConExistencia);
    }

    @Override
    public Observable<List<ApiProducto>> getProductos(URLEnum urlEnum, String codFamilia) {
        return api.getProductos(urlEnum == URLEnum.PRODUCTOS ? Api.urlProductos :
                Api.urlProductosConExistencia, codFamilia);
    }

    @Override
    public Observable<List<ApiProducto>> searchProductos(URLEnum urlEnum, Map<String, String> filter) {
        return api.searchProductos( urlEnum == URLEnum.PRODUCTOS_CON_EXISTENCIA_SEARCH ?
                Api.urlSearchProductosConExistencia : Api.urlSearchProductos, filter);
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
    public Observable<ApiListDetallesPedido> findAllDetallesPedidoDespachadosyNoDespachados(int numeroPedido) {
        return api.findAllDetallesPedidoDespachadosyNoDespachados(numeroPedido);
    }

    @Override
    public Observable<ApiPedidoResponse> getPedidoById(int numeroPedido) {
        return api.getPedidoById(numeroPedido);
    }

    @Override
    public Observable<ApiListProductos> getProductosByDocument(int numeroPedido) {
        return api.getProductosByDocument(numeroPedido);
    }

    @Override
    public Completable updatePassword(Map<String, String> params) {
        return api.updatePassword(params);
    }

    @Override
    public Observable<ApiApkVersion> getApkVersion(int lastVersion) {
        return api.getApkVersion(lastVersion);
    }

    @Override
    public Observable<ResponseBody> getApkFile(String url) {
        return api.getApkFile(url);
    }

    @Override
    public Observable<ApiListPedidos> findListPedidos(URLEnum urlEnum) {
        if (urlEnum == URLEnum.ALL_PEDIDOS)
            return api.listAllPedidos(false);
        else
            return api.listPedidos(
                    urlEnum == URLEnum.PEDIDOS_DESPACHADOS ? Api.urlAllPedidosDesapachados :
                            urlEnum == URLEnum.PEDIDOS_CANCELADOS ? Api.urlAllPedidosCancelados :
                                    urlEnum == URLEnum.PEDIDOS_DESPACHADOS_FACTURADOS ? Api.urlPedidosDesapachadosFacturados :
                                            urlEnum == URLEnum.PEDIDOS_DESPACHADOS_FALTANTES ? Api.urlPedidosDesapachadosConFaltantes :
                                                    Api.urlPedidosPendientesPorDespachar
            );
    }
}
