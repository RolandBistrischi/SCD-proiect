package package_tracking.Courier.EmailFolder;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailRequest {
    private List<String> emails;
    private String subject;
    private String body;
}

