package friendlyrobot.nyc.friendlyimmutables.vo;

import org.immutables.value.Value;


@Value.Immutable
public interface Classroom {
    String location();
    int capacity();

}
