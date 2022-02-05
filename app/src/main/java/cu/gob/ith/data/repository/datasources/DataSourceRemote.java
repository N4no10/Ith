package cu.gob.ith.data.repository.datasources;

import javax.inject.Inject;
import javax.inject.Singleton;

import cu.gob.ith.data.api.Api;
import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiLoginResponse;
import io.reactivex.rxjava3.core.Observable;

@Singleton
public class DataSourceRemote implements DataSourceApi {

    private Api api;

    @Inject
    public DataSourceRemote(Api api) {
        this.api = api;
    }

    @Override
    public Observable<ApiLoginResponse> login(ApiLoginBody apiLoginBody) {
        return api.login(apiLoginBody);
    }
}
