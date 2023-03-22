package ec.com.project.sofkaU.api.usecases;

import ec.com.project.sofkaU.api.domain.collection.Project;
import ec.com.project.sofkaU.api.repository.IProjectRepository;
import ec.com.project.sofkaU.api.domain.dto.ProjectDTO;
import ec.com.project.sofkaU.api.usecases.interfaces.UpdateProject;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class UpdateProjectUseCase implements UpdateProject {

    private final IProjectRepository iProjectRepository;
    private final ModelMapper mapper;

    @Override
    public Mono<ProjectDTO> update(String projectId, ProjectDTO projectDTO) {
        return this.iProjectRepository.findById(projectId)
                .switchIfEmpty(Mono.empty())
                .flatMap(project -> {
                    projectDTO.setProjectID(projectId);
                    return iProjectRepository.save(mapper.map(projectDTO, Project.class));
                })
                .switchIfEmpty(Mono.empty())
                .map(project -> mapper.map(project, ProjectDTO.class))
                .map(savedBook -> mapper.map(savedBook, ProjectDTO.class))
                .onErrorResume(Mono::error);
    }

}
