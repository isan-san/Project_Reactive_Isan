package ec.com.project.sofkaU.api.usecases;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class GetAllUseCaseTest {
    /*
    @Mock
    IPortfolioRepository repository;
    ModelMapper modelMapper;
    GetAllPortfoliosUseCase getAllStudentsUsecase;

    @BeforeEach
    void init() {
        modelMapper = new ModelMapper();
        getAllStudentsUsecase = new GetAllPortfoliosUseCase(repository, modelMapper);
    }

    @Test
    @DisplayName("getAllstudents_Success")
    void getAllstudents() {

        Portfolio student = new Portfolio();
        student.setIdNumber("Test id");
        student.setName("Test name");
        student.setTheme("Test last name");

        Portfolio student2 = new Portfolio();
        student.setIdNumber("Test id2");
        student.setName("Test name2");
        student.setTheme("Test last name2");

        Mockito.when(repository.findAll()).
                thenAnswer(InvocationOnMock -> {
                    return Flux.just(student, student2);
                });

        Flux<PortfolioDTO> response = getAllStudentsUsecase.get();

        StepVerifier.create(response)
                .expectNextCount(2)
                .verifyComplete();

        Mockito.verify(repository).findAll();
    }
*/
}