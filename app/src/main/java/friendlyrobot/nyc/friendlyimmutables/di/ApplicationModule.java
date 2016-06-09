package friendlyrobot.nyc.friendlyimmutables.di;

import android.app.Application;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import friendlyrobot.nyc.friendlyimmutables.vo.GsonAdaptersVo;

@Module
public class ApplicationModule {

    @NonNull
    private final Application context;

    public ApplicationModule(@NonNull Application context) {
        this.context = context;
    }

    @Provides
    @NonNull
    public Application provideApplication() {
        return context;
    }

    @Provides
    @NonNull
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new GsonAdaptersVo())
                .create();
    }

}
