package ec.com.project.sofkaU.api.usecases.interfaces;

import ec.com.project.sofkaU.api.domain.dto.ProjectDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface SaveProject {
    Mono<ProjectDTO> save(ProjectDTO studentDTO);
}
