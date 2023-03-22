package ec.com.project.sofkaU.api.usecases;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class DeleteUseCaseTest {
/*
    @Mock
    IPortfolioRepository repository;
    DeleteUsecase deleteUsecase;

    @BeforeEach
    void init() {
        deleteUsecase = new DeleteUsecase(repository);
    }

    @Test
    @DisplayName("getAllStudents_Success")
    void getAllStudents() {

        Portfolio student = new Portfolio();
        student.setIdNumber("Test id");
        student.setName("Test name");
        student.setTheme("Test last name");
        student.setPortfolioID("Test student");

        Mockito.when(repository.findById("Test student")).
                thenAnswer(InvocationOnMock -> {
                    return Mono.just(student);
                });
        Mockito.when(repository.delete(student)).
                thenAnswer(InvocationOnMock -> {
                    return Mono.just(Void.TYPE);
                });

        Mono<String> response = deleteUsecase.apply("Test student");

        StepVerifier.create(response)
                .expectNextCount(1)
                .verifyComplete();

        Mockito.verify(repository).delete(student);
        Mockito.verify(repository).findById("Test student");
    }
*/
}