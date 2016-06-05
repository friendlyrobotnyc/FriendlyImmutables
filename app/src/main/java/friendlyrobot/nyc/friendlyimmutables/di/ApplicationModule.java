package friendlyrobot.nyc.friendlyimmutables.di;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import friendlyrobot.nyc.friendlyimmutables.vo.GsonAdaptersVo;

@Module
public class ApplicationModule {

    @Provides
    @NonNull
    @Singleton
    public Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapterFactory(new GsonAdaptersVo())
                .create();
    }
}
