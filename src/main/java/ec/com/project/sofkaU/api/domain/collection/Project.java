package ec.com.project.sofkaU.api.domain.collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ProjectDB")
public class Project {

    @Id
    private String projectID = UUID.randomUUID().toString().substring(0, 10);

    private String name;

    private String subject;

    private Boolean isPublished = false;

    public Project publishProject(){
        this.isPublished=true;
        return this;
    }

}

