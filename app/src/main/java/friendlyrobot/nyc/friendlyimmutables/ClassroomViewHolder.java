package friendlyrobot.nyc.friendlyimmutables;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import java.util.Collection;

import friendlyrobot.nyc.friendlyimmutables.vo.Classroom;
import friendlyrobot.nyc.friendlyimmutables.vo.Student;

/**
 * Created by brianplummer on 6/9/16.
 */
public class ClassroomViewHolder  extends RecyclerView.ViewHolder {

    @NonNull
    TextView location, capacity;

    public ClassroomViewHolder(View itemView) {
        super(itemView);
        location = (TextView) itemView.findViewById(R.id.location);
        capacity = (TextView) itemView.findViewById(R.id.capacity);
    }

    public void bind(Classroom classroom, Collection<Student> students) {
        location.setText(classroom.location());
        capacity.setText(classroom.capacity() + "");
    }
}
