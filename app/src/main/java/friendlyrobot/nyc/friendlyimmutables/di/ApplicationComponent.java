package friendlyrobot.nyc.friendlyimmutables.di;

import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Component;
import friendlyrobot.nyc.friendlyimmutables.MainActivity;

@Singleton
@Component(modules = {
        ApplicationModule.class})
public interface ApplicationComponent {
        void inject(@NonNull MainActivity mainActivity);
}
