package cu.gob.ith.di;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

import javax.inject.Singleton;

import cu.gob.ith.R;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;

@Module
@InstallIn(SingletonComponent.class)
public class ModuleApp {

    @Singleton
    @Provides
    static SharedPreferences provideSharedPreferences(@ApplicationContext Context context) {
        return context.getSharedPreferences(context.getResources().getString(R.string.preferences_file_name), Context.MODE_PRIVATE);
    }

    @Singleton
    @Provides
    static ConnectivityManager provideConnectivityManager(@ApplicationContext Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

}
