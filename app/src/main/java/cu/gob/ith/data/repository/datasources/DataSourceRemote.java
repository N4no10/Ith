package cu.gob.ith.data.repository.datasources;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import cu.gob.ith.data.api.Api;
import cu.gob.ith.data.api.model.ApiCategoria;
import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiLoginResponse;
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
}
