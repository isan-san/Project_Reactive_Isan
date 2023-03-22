package ec.com.project.sofkaU.api.usecases;

import ec.com.project.sofkaU.api.domain.collection.Project;
import ec.com.project.sofkaU.api.repository.IProjectRepository;
import ec.com.project.sofkaU.api.domain.dto.ProjectDTO;
import ec.com.project.sofkaU.api.usecases.interfaces.SaveProject;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class SaveProjectUseCase implements SaveProject {

    private final IProjectRepository iProjectRepository;

    private final ModelMapper mapper;
    @Override
    public Mono<ProjectDTO> save(ProjectDTO projectDTO) {
        return this.iProjectRepository.save(mapper.map(projectDTO, Project.class))
                .switchIfEmpty(Mono.empty())
                .map(project -> mapper.map(project, ProjectDTO.class));
    }

}
