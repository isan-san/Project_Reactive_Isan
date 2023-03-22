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
class GetByIdUseCaseTest {

    @Mock
    IProjectRepository repository;
    ModelMapper modelMapper;
    GetProjectByIdUseCase getProjectByIdUseCase;

    @BeforeEach
    void init() {
        modelMapper = new ModelMapper();
        getProjectByIdUseCase = new GetProjectByIdUseCase(repository, modelMapper);
    }

    @Test
    @DisplayName("getById_Success")
    void getAllBooks() {

        Project project = new Project();
        project.setProjectID("Test id");
        project.setName("Test name");
        project.setSubject("Test last name");

        Mockito.when(repository.findById("1")).
                thenAnswer(InvocationOnMock -> {
                    return Mono.just(project);
                });

        Mono<ProjectDTO> response = getProjectByIdUseCase.apply("1");

        StepVerifier.create(response)
                .expectNext(modelMapper.map(project,ProjectDTO.class))
                .expectNextCount(0)
                .verifyComplete();

        Mockito.verify(repository).findById("1");
    }

}