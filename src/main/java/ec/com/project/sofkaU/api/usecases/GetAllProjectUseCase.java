package ec.com.project.sofkaU.api.usecases;

import ec.com.project.sofkaU.api.repository.IProjectRepository;
import ec.com.project.sofkaU.api.domain.dto.ProjectDTO;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.function.Supplier;

@Service
@AllArgsConstructor
public class GetAllProjectUseCase implements Supplier<Flux<ProjectDTO>> {

    private final IProjectRepository iProjectRepository;

    private final ModelMapper mapper;

    @Override
    public Flux<ProjectDTO> get() {
        return this.iProjectRepository
                .findAll()
                .switchIfEmpty(Flux.empty())
                .map(Project -> mapper.map(Project, ProjectDTO.class));
    }
}
