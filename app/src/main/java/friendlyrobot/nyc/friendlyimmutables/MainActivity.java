package friendlyrobot.nyc.friendlyimmutables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;

import javax.inject.Inject;

import friendlyrobot.nyc.friendlyimmutables.vo.ImmutableStudent;
import friendlyrobot.nyc.friendlyimmutables.vo.Student;

public class MainActivity extends AppCompatActivity {

    @Inject
    Gson gson;

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

        Student student = ImmutableStudent.builder().name("bob").build();
        String literal = gson.toJson(student);

        Log.e("#####","literal::" + literal);
    }

}
