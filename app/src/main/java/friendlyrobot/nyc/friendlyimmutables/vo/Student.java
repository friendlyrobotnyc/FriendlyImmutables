package friendlyrobot.nyc.friendlyimmutables.vo;

import org.immutables.gson.Gson;
import org.immutables.value.Value;

@Value.Immutable
@Gson.TypeAdapters
public interface Student {
    String name();
}
