package ec.com.project.sofkaU.api.usecases;

import ec.com.project.sofkaU.api.domain.collection.Project;
import ec.com.project.sofkaU.api.domain.dto.ProjectDTO;
import ec.com.project.sofkaU.api.repository.IProjectRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class DeleteUseCaseTest {

    @Mock
    IProjectRepository repository;
    DeleteUsecase deleteUsecase;

    @BeforeEach
    void init() {
        deleteUsecase = new DeleteUsecase(repository);
    }

    @Test
    @DisplayName("delete_Success")
    void getAllStudents() {

        Project project = new Project();
        project.setProjectID("Test id");
        project.setName("Test name");
        project.setSubject("Test last name");

        Mockito.when(repository.findById("Test project")).
                thenAnswer(InvocationOnMock -> {
                    return Mono.just(project);
                });
        Mockito.when(repository.delete(project)).
                thenAnswer(InvocationOnMock -> {
                    return Mono.just(Void.TYPE);
                });

        Mono<String> response = deleteUsecase.apply("Test project");

        StepVerifier.create(response)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(repository).delete(project);
        Mockito.verify(repository).findById("Test project");
    }

}