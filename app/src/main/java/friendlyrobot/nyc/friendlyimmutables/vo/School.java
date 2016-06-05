package friendlyrobot.nyc.friendlyimmutables.vo;

import com.google.common.collect.Multimap;

import org.immutables.value.Value;


@Value.Immutable
public abstract class School {

    public abstract String schoolName();

    public abstract Multimap<Classroom, Student> classroomsAndStudents();

    @Value.Default
    public String mascotName() {
        return "No Mascot";
    }

}
