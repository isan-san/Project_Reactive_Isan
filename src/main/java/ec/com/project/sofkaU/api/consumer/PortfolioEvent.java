package ec.com.project.sofkaU.api.consumer;

import ec.com.project.sofkaU.api.domain.dto.ProjectDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PortfolioEvent {
    private ProjectDTO projectDTO;
    private String eventType;
}
