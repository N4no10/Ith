package cu.gob.ith.data.repository.datasources;

import cu.gob.ith.data.api.model.ApiLoginBody;
import cu.gob.ith.data.api.model.ApiLoginResponse;
import io.reactivex.rxjava3.core.Observable;

public interface DataSourceApi {

    Observable<ApiLoginResponse> login(ApiLoginBody apiLoginBody);

}
