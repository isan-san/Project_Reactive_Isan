package ec.com.project.sofkaU.api.repository;

import ec.com.project.sofkaU.api.domain.collection.Project;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IProjectRepository extends ReactiveMongoRepository<Project, String> {
}
