package friendlyrobot.nyc.friendlyimmutables;

import android.app.Application;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.common.collect.ImmutableList;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Random;

import javax.inject.Inject;

import friendlyrobot.nyc.friendlyimmutables.vo.Classroom;
import friendlyrobot.nyc.friendlyimmutables.vo.ImmutableClassroom;
import friendlyrobot.nyc.friendlyimmutables.vo.ImmutableSchool;
import friendlyrobot.nyc.friendlyimmutables.vo.School;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainPresenter {

    @NonNull
    private final Gson gson;
    @NonNull
    private final Application context;
    @NonNull
    private final Random random = new Random();

    private MainActivity activity;

    private School school;

    @Inject
    public MainPresenter(@NonNull Gson gson,
                         @NonNull Application context) {
        this.gson = gson;
        this.context = context;
    }

    public void createClassroomItem() {
        Classroom newClassroom = ImmutableClassroom.builder()
                .capacity(random.nextInt(30))
                .id(SystemClock.elapsedRealtime())
                .location("Floor:" + random.nextInt(4))
                .build();

        List<Classroom> classrooms = ImmutableList.<Classroom>builder()
                .addAll(school.classrooms())
                .add(newClassroom)
                .build();

        school = ImmutableSchool.copyOf(school).withClassrooms(classrooms);
        activity.setSchoolData(school);
    }

    public void loadDataFromAssets() {
        getSchoolObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .filter(school -> school != null)
                .subscribe(new Observer<School>() {
                    @Override
                    public void onCompleted() {}

                    @Override
                    public void onError(Throwable e) {}

                    @Override
                    public void onNext(School school) {
                        MainPresenter.this.school = school;
                        activity.setSchoolData(school);
                    }
                });
    }

    public void takeView(MainActivity activity) {
        this.activity = activity;
    }

    public void unBind() {
        this.activity = null;
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