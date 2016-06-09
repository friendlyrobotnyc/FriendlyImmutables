package friendlyrobot.nyc.friendlyimmutables;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;

import javax.inject.Inject;

import friendlyrobot.nyc.friendlyimmutables.vo.School;
import rx.Observable;

public class MainPresenter {

    @NonNull
    private final Gson gson;
    @NonNull
    private final Application context;

    @Inject
    public MainPresenter(@NonNull Gson gson,
                         @NonNull Application context) {
        this.gson = gson;
        this.context = context;
    }

    @NonNull
    Observable<School> getSchoolObservable() {
        return Observable.fromCallable(this::getSchoolData);
    }

    @Nullable
    private School getSchoolData() {
        InputStreamReader inputStreamReader = null;

        try {
            inputStreamReader  = new InputStreamReader(context.getAssets().open("school.json"));
            return gson.fromJson(inputStreamReader, School.class);
        } catch (IOException e) {
            Log.e("Error", e.getMessage(), e);

        } finally {
            closeStream(inputStreamReader);
        }

        return null;
    }

    private void closeStream(InputStreamReader inputStreamReader) {
        if (inputStreamReader != null)
            try {
                inputStreamReader.close();
            } catch (IOException e) {
                Log.e("Error", e.getMessage(), e);
            }
    }
}