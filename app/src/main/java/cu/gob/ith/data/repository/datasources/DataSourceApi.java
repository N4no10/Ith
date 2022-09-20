package cu.gob.ith.data.repository.datasources;

import java.util.List;
import java.util.Map;

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
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Query;

public interface DataSourceApi {

    Observable<ApiLoginResponse> login(ApiLoginBody apiLoginBody);
    Completable recuperarPass(ApiLoginBody apiLoginBody);

    Observable<List<ApiCategoria>> getCategorias(URLEnum urlEnum);

    Observable<List<ApiProducto>> getProductos(URLEnum urlEnum, String codFamilia);

    Observable<ApiPedidoResponse> requestOrder(List<ApiPedido> apiPedidoList);

    Observable<ApiListPedidos> filterListPedidos(Map<String, Object> params);

    Observable<ApiListDetallesPedido> findAllDetallesPedidoDespachadosyNoDespachados(int numeroPedido);

    Observable<ApiListPedidos> findListPedidos(URLEnum urlEnum);

    Observable<ApiListProductos> getProductosByDocument(int numeroPedido);

    Observable<ApiPedidoResponse> getPedidoById(int numeroPedido);

    Completable updatePassword(Map<String, String> params);

    Observable<ApiApkVersion> getApkVersion(int lastVersion);

    Observable<ResponseBody> getApkFile(String url);
}
