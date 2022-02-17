package cu.gob.ith.data.api.interceptors;

import androidx.annotation.NonNull;

import java.io.IOException;

import javax.inject.Inject;

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
        if (userAppPreferences.existAccessTokenPreference() && !request.url().toString().contains("login/authenticate"))
            request = request.newBuilder()
                    .addHeader("Authorization", userAppPreferences.getAccessTokenPreferences(""))
                    .build();

        return chain.proceed(request);
    }
}
