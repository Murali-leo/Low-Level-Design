package placements;

import java.util.function.Supplier;

public class Utils {

    public static Placement getIfNull(Placement placement, Supplier<Placement> supplier) {
        if (placement == null) {
            return supplier.get();
        }
        return placement;
    }
}
