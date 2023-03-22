package ec.com.project.sofkaU.api.usecases.interfaces;

import ec.com.project.sofkaU.api.domain.dto.ProjectDTO;
import reactor.core.publisher.Mono;

@FunctionalInterface
public interface UpdateProject {
    Mono<ProjectDTO> update(String studentId, ProjectDTO studentDTO);
}
