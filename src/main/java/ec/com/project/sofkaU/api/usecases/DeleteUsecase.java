package ec.com.project.sofkaU.api.usecases;

import ec.com.project.sofkaU.api.repository.IProjectRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Service
@AllArgsConstructor
public class DeleteUsecase implements Function<String, Mono<String>> {

    private final IProjectRepository iProjectRepository;

    @Override
    public Mono<String> apply(String id) {
        return this.iProjectRepository
                .findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Project not found")))
                .flatMap(project -> iProjectRepository.delete(project).thenReturn(id))
                .onErrorResume(Mono::error);
    }

}
