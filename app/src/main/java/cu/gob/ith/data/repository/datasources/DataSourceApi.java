package cu.gob.ith.data.repository.datasources;

import java.util.List;

import cu.gob.ith.data.api.model.ApiCategoria;
import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiLoginResponse;
import cu.gob.ith.data.api.model.ApiProducto;
import io.reactivex.rxjava3.core.Observable;

public interface DataSourceApi {

    Observable<ApiLoginResponse> login(ApiLoginBody apiLoginBody);

    Observable<List<ApiCategoria>> getCategorias();

    Observable<List<ApiProducto>> getProductos(String codFamilia);

}
