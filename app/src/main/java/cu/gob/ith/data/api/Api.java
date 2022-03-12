package cu.gob.ith.data.api;

import java.util.List;

import cu.gob.ith.data.api.model.ApiCategoria;
import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiLoginResponse;
import cu.gob.ith.data.api.model.ApiPedido;
import cu.gob.ith.data.api.model.ApiProducto;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface Api {

    @POST("login/authenticate")
    Observable<ApiLoginResponse> login(@Body ApiLoginBody apiLoginBody);

    @GET("categoria")
    Observable<List<ApiCategoria>> getCategorias();

    @GET("categoria/productos")
    Observable<List<ApiProducto>> getProductos(@Query("CodFamilia") String codFamilia);

    @POST("api/pedido/guardarPedido")
    Observable<String> requestOrder(@Body List<ApiPedido> apiPedidoList);

}
