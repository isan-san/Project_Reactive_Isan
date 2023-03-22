package ec.com.project.sofkaU.api.usecases;

import ec.com.project.sofkaU.api.domain.collection.Project;
import ec.com.project.sofkaU.api.domain.dto.ProjectDTO;
import ec.com.project.sofkaU.api.repository.IProjectRepository;
import ec.com.project.sofkaU.api.usecases.interfaces.UpdateProject;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class PublishProjectUseCase implements Function<String, Mono<ProjectDTO>> {

    private final IProjectRepository iProjectRepository;
    private final ModelMapper mapper;

    @Override
    public Mono<ProjectDTO> apply(String projectId) {
        return this.iProjectRepository.findById(projectId)
                .switchIfEmpty(Mono.empty())
                .flatMap(project -> {
                    return iProjectRepository.save(project.publishProject())
                            .map(publicProject -> mapper.map(publicProject, ProjectDTO.class));
                })
                .onErrorResume(Mono::error);
    }

}
