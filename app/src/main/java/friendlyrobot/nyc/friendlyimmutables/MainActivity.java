package friendlyrobot.nyc.friendlyimmutables;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import javax.inject.Inject;

import friendlyrobot.nyc.friendlyimmutables.vo.School;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainPresenter mainPresenter;

    private RecyclerView recyclerView;
    private ClassroomsAdapter classroomsAdapter;
    private FloatingActionButton createClassroom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((FriendlyApplication) getApplication())
                .applicationComponent()
                .inject(this);

        mainPresenter.takeView(this);

        createClassroom = (FloatingActionButton) findViewById(R.id.createClassroom);
        createClassroom.setOnClickListener(v -> mainPresenter.createClassroomItem());

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        classroomsAdapter = new ClassroomsAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(classroomsAdapter);

        mainPresenter.loadDataFromAssets();
    }

    @Override
    protected void onDestroy() {
        mainPresenter.unBind();
        super.onDestroy();
    }

    public void setSchoolData(School school) {
        classroomsAdapter.setSchool(school);
    }
}
