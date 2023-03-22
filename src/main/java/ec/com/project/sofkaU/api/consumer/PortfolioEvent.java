package ec.com.project.sofkaU.api.consumer;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PortfolioEvent {
    private String projectID;
    private String eventType;
}
