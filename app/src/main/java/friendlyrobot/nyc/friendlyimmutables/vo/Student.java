package friendlyrobot.nyc.friendlyimmutables.vo;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
public interface Student {
    String name();
}
