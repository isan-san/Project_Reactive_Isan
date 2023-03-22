package ec.com.project.sofkaU.api.usecases;

import ec.com.project.sofkaU.api.domain.dto.ProjectDTO;
import ec.com.project.sofkaU.api.repository.IProjectRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class GetProjectByIdUseCase implements Function<String, Mono<ProjectDTO>> {
    private final IProjectRepository iProjectRepository;

    private final ModelMapper mapper;
    @Override
    public Mono<ProjectDTO> apply(String id) {
        return this.iProjectRepository
                .findById(id)
                .switchIfEmpty(Mono.empty())
                .map(Project-> mapper.map(Project, ProjectDTO.class));
    }
}
