package cu.gob.ith.data.preferences;


import android.content.SharedPreferences;

import javax.inject.Inject;
import javax.inject.Singleton;

import cu.gob.ith.data.api.model.ApiLoginResponse;

@Singleton
public class UserAppPreferences {

    private SharedPreferences sharedPreferences;

    @Inject
    public UserAppPreferences(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    public void clearPreferences() {
        sharedPreferences.edit().clear().apply();
    }

    public void clearPreferences(String key) {
        sharedPreferences.edit().remove(key).apply();
    }

    public void setPreferenceString(String key, String value) {
        sharedPreferences.edit().putString(key, value).apply();
    }

    public void setPreferenceBoolean(String key, boolean value) {
        sharedPreferences.edit().putBoolean(key, value).apply();
    }

    public String getPreferenceString(String key, String msg) {
        return sharedPreferences.getString(key, msg);
    }

    private void getPreferenceBoolean(String key) {
        sharedPreferences.getBoolean(key, false);
    }

    public void setPreferenceInt(String key, int value) {
        sharedPreferences.edit().putInt(key, value).apply();
    }

    public int getPreferenceInt(String key) {
        return sharedPreferences.getInt(key, 0);
    }

    public void setApiLoginBodyResponsePreference(ApiLoginResponse apiLoginResponse) {
        setUsernamePreferences(apiLoginResponse.getUsername());
        setNamePreferences(apiLoginResponse.getName());
        setEmailPreferences(apiLoginResponse.getEmail());
        setAccessTokenPreferences(apiLoginResponse.getToken());
    }

    public void setUsernamePreferences(String username) {
        setPreferenceString(PreferenceConstants.USERNAME, username);
    }

    public String getUsernamePreferences(String msg) {
        return getPreferenceString(PreferenceConstants.USERNAME, msg);
    }

    public void setNamePreferences(String name) {
        setPreferenceString(PreferenceConstants.NAME, name);
    }

    public String getNamePreferences(String msg) {
        return getPreferenceString(PreferenceConstants.NAME, msg);
    }

    public void setEmailPreferences(String email) {
        setPreferenceString(PreferenceConstants.EMAIL, email);
    }

    public String getEmailPreferences(String msg) {
        return getPreferenceString(PreferenceConstants.EMAIL, msg);
    }

    public void setAccessTokenPreferences(String accessToken) {
        setPreferenceString(PreferenceConstants.ACCESS_TOKEN, accessToken);
    }

    public String getAccessTokenPreferences(String msg) {
        return getPreferenceString(PreferenceConstants.ACCESS_TOKEN, msg);
    }

    public boolean existAccessTokenPreference() {
        return !getAccessTokenPreferences("").equals("");
    }
}
