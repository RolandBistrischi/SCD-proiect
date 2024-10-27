package package_tracking.pkg;

import lombok.Data;

@Data
public class UpdatePackageRequest {
    private Integer id;
    private Status status;
}
