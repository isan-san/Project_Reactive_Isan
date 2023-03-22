package ec.com.project.sofkaU.api.usecases;

import ec.com.project.sofkaU.api.domain.collection.Project;
import ec.com.project.sofkaU.api.domain.dto.ProjectDTO;
import ec.com.project.sofkaU.api.repository.IProjectRepository;
import ec.com.project.sofkaU.api.usecases.interfaces.UpdateProject;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class PublishProjectUseCase implements UpdateProject {

    private final IProjectRepository iProjectRepository;
    private final ModelMapper mapper;

    @Override
    public Mono<ProjectDTO> update(String projectId, ProjectDTO projectDTO) {
        return this.iProjectRepository.findById(projectId)
                .switchIfEmpty(Mono.empty())
                .flatMap(book -> {
                    projectDTO.setProjectID(projectId);
                    return iProjectRepository.save(mapper.map(projectDTO, Project.class));
                })
                .switchIfEmpty(Mono.empty())
                .map(book -> mapper.map(book, ProjectDTO.class))
                .map(savedBook -> mapper.map(savedBook, ProjectDTO.class))
                .onErrorResume(Mono::error);
    }

}
