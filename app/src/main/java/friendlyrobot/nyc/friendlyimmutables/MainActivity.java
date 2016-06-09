package friendlyrobot.nyc.friendlyimmutables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import javax.inject.Inject;

import friendlyrobot.nyc.friendlyimmutables.vo.School;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((FriendlyApplication) getApplication())
                .applicationComponent()
                .inject(this);

        start();
    }

    private void start() {

        mainPresenter.getSchoolObservable()
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
                        Log.e("###","test");
                    }
                });

    }


}
