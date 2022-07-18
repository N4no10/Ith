package cu.gob.ith.data.api;

import java.util.List;
import java.util.Map;

import cu.gob.ith.data.api.model.ApiApkVersion;
import cu.gob.ith.data.api.model.ApiCategoria;
import cu.gob.ith.data.api.model.ApiListDetallesPedido;
import cu.gob.ith.data.api.model.ApiListPedidos;
import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiLoginResponse;
import cu.gob.ith.data.api.model.ApiPedido;
import cu.gob.ith.data.api.model.ApiPedidoResponse;
import cu.gob.ith.data.api.model.ApiProducto;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface Api {

    @POST("login/authenticate")
    Observable<ApiLoginResponse> login(@Body ApiLoginBody apiLoginBody);

    @GET("categoria")
    Observable<List<ApiCategoria>> getCategorias();

    @GET("categoria/productos")
    Observable<List<ApiProducto>> getProductos(@Query("CodFamilia") String codFamilia);

    @POST("pedido/guardarPedido")
    Observable<ApiPedidoResponse> requestOrder(@Body List<ApiPedido> apiPedidoList);

    @GET("pedido/findAllPedidos")
    Observable<ApiListPedidos> filterListPedidos(@QueryMap Map<String, Object> params);

    @GET("pedido/findAllPedidosDespachados")
    Observable<ApiListPedidos> filterListPedidosDespachados();

    @GET("pedido/findAllPedidosDespachadosFacturados")
    Observable<ApiListPedidos> filterListPedidosDespachadosFacturados();

    @GET("pedido/findAllDetallesPedidoDespachadosyNoDespachados")
    Observable<ApiListDetallesPedido> findAllDetallesPedidoDespachadosyNoDespachados(@Query("numero") int numeroPedido);

    @GET("pedido/findAllPedidosPendientesDespachar")
    Observable<ApiListPedidos> filterListPedidosPendientesDespachar();

    @GET("pedido/findAllPedidosCancelados")
    Observable<ApiListPedidos> findAllPedidosCancelados();

    @GET("pedido")
    Observable<ApiPedidoResponse> getPedidoById(@Query("numero") int numeroPedido);

    @PUT("servicios/generales/cambiarPassword")
    Completable updatePassword(@QueryMap Map<String, String> params);

    //Apk Info
    @GET("apk/versiones")
    Observable<List<ApiApkVersion>> getApkAllVersions();

    @GET("apk/ultimaVersion")
    Observable<List<ApiApkVersion>> getApkVersion(@Query("version") int lastVersion);

    @Streaming
    @GET
    Observable<ResponseBody> getApkFile(@Url String url);
}
