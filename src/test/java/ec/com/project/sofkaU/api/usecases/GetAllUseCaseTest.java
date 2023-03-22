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
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;


@ExtendWith(MockitoExtension.class)
class GetAllUseCaseTest {
    
    @Mock
    IProjectRepository repository;
    ModelMapper modelMapper;
    GetAllProjectUseCase getAllProjectUseCase;

    @BeforeEach
    void init() {
        modelMapper = new ModelMapper();
        getAllProjectUseCase = new GetAllProjectUseCase(repository, modelMapper);
    }

    @Test
    @DisplayName("getAll_Success")
    void getAllprojects() {

        Project project = new Project();
        project.setProjectID("Test id");
        project.setName("Test name");
        project.setSubject("Test last name");

        Project project2 = new Project();
        project2.setProjectID("Test id");
        project2.setName("Test name");
        project2.setSubject("Test last name");

        Mockito.when(repository.findAll()).
                thenAnswer(InvocationOnMock -> {
                    return Flux.just(project, project2);
                });

        Flux<ProjectDTO> response = getAllProjectUseCase.get();

        StepVerifier.create(response)
                .expectNext(modelMapper.map(project,ProjectDTO.class))
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(repository).findAll();
    }

}