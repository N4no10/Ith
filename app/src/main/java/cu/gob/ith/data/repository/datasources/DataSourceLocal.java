package cu.gob.ith.data.repository.datasources;

import cu.gob.ith.data.api.model.ApiLoginResponse;

public interface DataSourceLocal {

    void saveApiLoginBodyResponsePreference(ApiLoginResponse apiLoginResponse);

}
