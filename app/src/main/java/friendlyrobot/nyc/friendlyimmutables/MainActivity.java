package friendlyrobot.nyc.friendlyimmutables;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.common.collect.ImmutableMultimap;
import com.google.gson.Gson;

import javax.inject.Inject;

import friendlyrobot.nyc.friendlyimmutables.vo.Classroom;
import friendlyrobot.nyc.friendlyimmutables.vo.ImmutableClassroom;
import friendlyrobot.nyc.friendlyimmutables.vo.ImmutableSchool;
import friendlyrobot.nyc.friendlyimmutables.vo.ImmutableStudent;
import friendlyrobot.nyc.friendlyimmutables.vo.School;
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

        Student bob = ImmutableStudent.builder()
                .name("bob").build();
        Student heather = ImmutableStudent.builder()
                .name("heather").build();
        Classroom classroom = ImmutableClassroom.builder()
                .id(2l).capacity(25).location("1st Floor").build();


        School school = ImmutableSchool.builder().schoolName("Cass High")
                .putClassroomsAndStudents(classroom.id(), heather, bob)
                .addClassrooms(classroom)
                .build();

        String literal = gson.toJson(school);
        Log.e("#####","literal::" + literal);
    }

}
