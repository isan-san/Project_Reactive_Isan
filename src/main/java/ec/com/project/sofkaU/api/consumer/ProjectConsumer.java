package ec.com.project.sofkaU.api.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ec.com.project.sofkaU.api.usecases.PublishProjectUseCase;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProjectConsumer {
    private final ObjectMapper objectMapper;
    private final PublishProjectUseCase publishProjectUseCase;

    @RabbitListener(queues = "Project.Append.queue") //lending queue
    public void receiveEventBook(String message) throws JsonProcessingException{
        PortfolioEvent event = objectMapper.readValue(message, PortfolioEvent.class);
        publishProjectUseCase.apply(event.getProjectID());
    }

}
