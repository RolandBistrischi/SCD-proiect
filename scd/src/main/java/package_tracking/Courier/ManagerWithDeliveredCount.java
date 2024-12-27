package package_tracking.Courier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ManagerWithDeliveredCount {
    private Courier courier;
    private int deliveredCount;
}

