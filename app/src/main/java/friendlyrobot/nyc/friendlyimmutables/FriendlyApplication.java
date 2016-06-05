package friendlyrobot.nyc.friendlyimmutables;

import android.app.Application;
import android.support.annotation.NonNull;

import friendlyrobot.nyc.friendlyimmutables.di.ApplicationComponent;
import friendlyrobot.nyc.friendlyimmutables.di.ApplicationModule;
import friendlyrobot.nyc.friendlyimmutables.di.DaggerApplicationComponent;

public class FriendlyApplication extends Application {

    @NonNull
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        applicationComponent = prepareApplicationComponent().build();
    }

    @NonNull
    protected DaggerApplicationComponent.Builder prepareApplicationComponent() {
        return DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule());
    }

    @NonNull
    public ApplicationComponent applicationComponent() {
        return applicationComponent;
    }

}
