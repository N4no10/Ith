package cu.gob.ith.data.api;

import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiLoginResponse;
import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {

    @POST("login/authenticate")
    Observable<ApiLoginResponse> login(@Body ApiLoginBody apiLoginBody);

//    @GET("categoria")
//    Observable<ApiLoginResponse> getCategorias(@Body ApiLoginBody loginBody);
//
//    @GET("categoria/productos")
//    Observable<ApiLoginResponse> getProductos(@Body ApiLoginBody loginBody);

}
