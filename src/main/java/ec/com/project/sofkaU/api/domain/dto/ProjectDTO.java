package ec.com.project.sofkaU.api.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDTO {

    @Id
    private String projectID;

    private String name;

    private String subject;

    private Boolean isPublished = false;

    public ProjectDTO(String projectID){
        this.projectID = projectID;
    }

}
