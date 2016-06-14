package friendlyrobot.nyc.friendlyimmutables;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import friendlyrobot.nyc.friendlyimmutables.vo.Classroom;
import friendlyrobot.nyc.friendlyimmutables.vo.School;

/**
 * Created by brianplummer on 6/9/16.
 */
public class ClassroomsAdapter extends RecyclerView.Adapter<ClassroomViewHolder> {

    @NonNull
    private final LayoutInflater inflater;

    @Nullable
    private School school;

    public ClassroomsAdapter(@NonNull Context context) {
        inflater = (LayoutInflater)context.getSystemService
                (Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public ClassroomViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ClassroomViewHolder(inflater.inflate(R.layout.classroom, parent, false));
    }

    @Override
    public void onBindViewHolder(ClassroomViewHolder holder, int position) {
        Classroom classroom = school.classrooms().get(position);
        holder.bind(classroom, school.classroomsAndStudents().get(classroom.id()));
    }

    @Override
    public int getItemCount() {
        return school == null ? 0 : school.classrooms().size();
    }

    public void setSchool(@Nullable School school) {
        this.school = school;
        notifyDataSetChanged();
    }
}
