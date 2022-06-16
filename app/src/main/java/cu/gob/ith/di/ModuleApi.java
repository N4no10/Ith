package cu.gob.ith.di;

import android.util.Log;

import com.google.gson.ExclusionStrategy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import cu.gob.ith.BuildConfig;
import cu.gob.ith.data.api.Api;
import cu.gob.ith.data.api.interceptors.TokenInterceptor;
import cu.gob.ith.data.preferences.UserAppPreferences;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.components.SingletonComponent;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
@InstallIn(SingletonComponent.class)
public class ModuleApi {
    public static final String BASE_URL = BuildConfig.BASE_URL;

    @Singleton
    @Provides
    static HttpLoggingInterceptor provideInteceptor() {
        return new HttpLoggingInterceptor(message -> Log.d("Api Rest", message))
                .setLevel(HttpLoggingInterceptor.Level.BODY);
    }


//    @Singleton
//    @Provides
//    static Gson provideGSON(ExclusionStrategy exclusionStrategy){
//        return new GsonBuilder()
//                //.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
////                .addDeserializationExclusionStrategy(exclusionStrategy)
//                .addSerializationExclusionStrategy(exclusionStrategy)
//                .setPrettyPrinting()
//                .create();
//    }

    @Singleton
    @Provides
    static OkHttpClient provideHttpClient(HttpLoggingInterceptor interceptor, TokenInterceptor tokenInterceptor) {
//        Log.e("OkHttpClient","OkHttpClient Inject ..." + myInterceptor);
        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(30, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .addInterceptor(tokenInterceptor)
                .build();
    }

    @Singleton
    @Provides
    static TokenInterceptor provideTokenInterceptor(UserAppPreferences userAppPreferences) {
        return new TokenInterceptor(userAppPreferences);
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofit(/*Gson gson,*/ OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
//                .baseUrl(/*"http://ith-local.cu/api/"*/"http://ithapi.local.cu:80/api/")
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
    }

    @Singleton
    @Provides
    static Api provideApiRetrofit(Retrofit retrofit) {
        return retrofit.create(Api.class);
    }

}
