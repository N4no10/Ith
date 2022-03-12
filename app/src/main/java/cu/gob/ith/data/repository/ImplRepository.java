package cu.gob.ith.data.repository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import cu.gob.ith.data.api.model.ApiCategoria;
import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiLoginResponse;
import cu.gob.ith.data.api.model.ApiPedido;
import cu.gob.ith.data.api.model.ApiPedidoResponse;
import cu.gob.ith.data.api.model.ApiProducto;
import cu.gob.ith.data.repository.datasources.DataSourcePreferences;
import cu.gob.ith.data.repository.datasources.DataSourceRemote;
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
}
