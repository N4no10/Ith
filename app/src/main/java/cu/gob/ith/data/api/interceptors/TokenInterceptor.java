package cu.gob.ith.data.api.interceptors;

import android.util.Log;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

import cu.gob.ith.data.api.Api;
import cu.gob.ith.data.preferences.UserAppPreferences;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class TokenInterceptor implements Interceptor {

    private UserAppPreferences userAppPreferences;

    @Inject
    public TokenInterceptor(UserAppPreferences userAppPreferences) {
        this.userAppPreferences = userAppPreferences;
    }

    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Log.e("dime","dime " + request.url().toString());
        if (userAppPreferences.existAccessTokenPreference() && (
                !request.url().toString().contains(Api.urlLogin) &&
                !request.url().toString().contains(Api.urlRecuperarPass)))
            request = request.newBuilder()
                    .addHeader("Authorization", userAppPreferences.getAccessTokenPreferences(""))
                    .build();

        return chain.proceed(request);
    }
}
