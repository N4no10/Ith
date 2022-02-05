package cu.gob.ith.data.repository;

import javax.inject.Inject;
import javax.inject.Singleton;

import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiLoginResponse;
import cu.gob.ith.data.repository.datasources.DataSourcePreferences;
import cu.gob.ith.data.repository.datasources.DataSourceRemote;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

@Singleton
public class ImplRepository implements Repository {

    private DataSourceRemote dataSourceRemote;
    private DataSourcePreferences dataSourcePreferences;


    @Inject
    public ImplRepository(DataSourceRemote dataSourceRemote, DataSourcePreferences dataSourcePreferences) {
        this.dataSourceRemote = dataSourceRemote;
        this.dataSourcePreferences = dataSourcePreferences;
    }

    @Override
    public Observable<ApiLoginResponse> login(ApiLoginBody apiLoginBody) {
        return dataSourceRemote.login(apiLoginBody)
                .subscribeOn(Schedulers.io())
                .doOnNext(apiLoginResponse ->
                    dataSourcePreferences.saveApiLoginBodyResponsePreference(apiLoginResponse)
                );
    }
}
