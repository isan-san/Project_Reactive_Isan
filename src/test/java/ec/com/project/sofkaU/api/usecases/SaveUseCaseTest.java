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
class SaveUseCaseTest {
    
    @Mock
    IProjectRepository repository;
    ModelMapper modelMapper;
    SaveProjectUseCase saveProjectUseCase;

    @BeforeEach
    void init() {
        modelMapper = new ModelMapper();
        saveProjectUseCase = new SaveProjectUseCase(repository, modelMapper);
    }

    @Test
    @DisplayName("save_Success")
    void getAllBooks() {

        Project project = new Project();
        project.setProjectID("Test id");
        project.setName("Test name");
        project.setSubject("Test last name");

        Mockito.when(repository.save(project)).
                thenAnswer(InvocationOnMock -> {
                    return Mono.just(project);
                });

        Mono<ProjectDTO> response = saveProjectUseCase.save(modelMapper.map(project, ProjectDTO.class));

        StepVerifier.create(response)
                .expectNext(modelMapper.map(project,ProjectDTO.class))
                .expectNextCount(0)
                .verifyComplete();

        Mockito.verify(repository).save(project);
    }


}