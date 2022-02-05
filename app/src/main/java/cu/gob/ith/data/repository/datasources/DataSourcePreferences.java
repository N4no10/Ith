package cu.gob.ith.data.repository.datasources;

import javax.inject.Inject;
import javax.inject.Singleton;

import cu.gob.ith.data.api.model.ApiLoginResponse;
import cu.gob.ith.data.preferences.UserAppPreferences;

@Singleton
public class DataSourcePreferences implements DataSourceLocal {

    private UserAppPreferences userAppPreferences;

    @Inject
    public DataSourcePreferences(UserAppPreferences userAppPreferences) {
        this.userAppPreferences = userAppPreferences;
    }

    @Override
    public void saveApiLoginBodyResponsePreference(ApiLoginResponse apiLoginResponse) {
        userAppPreferences.setApiLoginBodyResponsePreference(apiLoginResponse);
    }
}
