package friendlyrobot.nyc.friendlyimmutables.vo;

import com.google.common.collect.Multimap;

import org.immutables.value.Value;

import java.util.List;


@Value.Immutable
public abstract class School {

    public abstract String schoolName();

    public abstract Multimap<Long, Student> classroomsAndStudents();

    public abstract List<Classroom> classrooms();

    @Value.Default
    public String mascotName() {
        return "No Mascot";
    }

}
