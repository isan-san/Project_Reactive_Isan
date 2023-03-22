package ec.com.project.sofkaU.api.consumer;

import ec.com.project.sofkaU.api.config.RabbitConfig;
import ec.com.project.sofkaU.api.domain.dto.ProjectDTO;
import ec.com.project.sofkaU.api.usecases.PublishProjectUseCase;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ProjectConsumer {
    private final PublishProjectUseCase publishProjectUseCase;

    @RabbitListener(queues = RabbitConfig.PORTFOLIO_QUEUE)
    public void receivePortfolioEvent(PortfolioEvent message) {
            publishProjectUseCase.apply(message.getProjectDTO()).block();
    }
}
