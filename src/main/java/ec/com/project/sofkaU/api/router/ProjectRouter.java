package ec.com.project.sofkaU.api.router;

import ec.com.project.sofkaU.api.usecases.*;
import ec.com.project.sofkaU.api.domain.dto.ProjectDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class ProjectRouter {

    @Bean
    public RouterFunction<ServerResponse> getAllProjects(GetAllProjectUseCase getAllProjectsUsecase) {
        return route(GET("/project"),
                request -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromPublisher(getAllProjectsUsecase.get(), ProjectDTO.class))
                        .onErrorResume(throwable -> ServerResponse.noContent().build()));
    }

    @Bean
    public RouterFunction<ServerResponse> getProjectById(GetProjectByIdUseCase getProjectByIdUsecase) {
        return route(GET("/project/{id}"),
                request -> getProjectByIdUsecase.apply(request.pathVariable("id"))
                        .flatMap(projectDTO -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(projectDTO))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }

    @Bean
    public RouterFunction<ServerResponse> saveProject(SaveProjectUseCase saveProjectUsecase) {
        return route(POST("/project").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProjectDTO.class)
                        .flatMap(projectDTO -> saveProjectUsecase.save(projectDTO)
                                .flatMap(result -> ServerResponse.status(201)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))

                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build())));
    }

    @Bean
    public RouterFunction<ServerResponse> patchProject(UpdateProjectUseCase updateProjectUsecase) {
        return route(PUT("/project/{id}").and(accept(MediaType.APPLICATION_JSON)),
                request -> request.bodyToMono(ProjectDTO.class)
                        .flatMap(projectDTO -> updateProjectUsecase.update(request.pathVariable("id"), projectDTO)
                                .flatMap(result -> ServerResponse.status(200)
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(result))
                                .onErrorResume(throwable -> ServerResponse.status(HttpStatus.NOT_ACCEPTABLE).build())));
    }

    @Bean
    public RouterFunction<ServerResponse> deleteProject(DeleteUsecase deleteUsecase) {
        return route(DELETE("/project/{id}"),
                request -> deleteUsecase.apply(request.pathVariable("id"))
                        .flatMap(result -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue("Deleted Successfully"))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }

    @Bean
    public RouterFunction<ServerResponse> publishProject(PublishProjectUseCase publishProjectUseCase) {
        return route(PATCH("/project/{id}"),
                request -> publishProjectUseCase.apply(request.pathVariable("id"))
                        .flatMap(projectDTO -> ServerResponse.ok()
                                .contentType(MediaType.APPLICATION_JSON)
                                .bodyValue(projectDTO))
                        .onErrorResume(throwable -> ServerResponse.notFound().build()));
    }
}
