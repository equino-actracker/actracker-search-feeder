package ovh.equino.actracker.searchfeed.main.springboot.config.refresh.amqp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotification;

import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY;
import static com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE;
import static com.fasterxml.jackson.annotation.PropertyAccessor.*;

class AmqpEntityRefreshedNotifier {

    @Autowired
    @Qualifier(InternalRefreshQueue.INTERNAL_REFRESHED_QUEUE_NAME)
    protected Queue queue;

    @Autowired
    protected RabbitTemplate rabbit;

    private final ObjectMapper serializer;

    protected AmqpEntityRefreshedNotifier() {
        this.serializer = new ObjectMapper();
        this.serializer.setVisibility(FIELD, ANY);
        this.serializer.setVisibility(GETTER, NONE);
        this.serializer.setVisibility(IS_GETTER, NONE);
    }

    protected void notifyEntityRefreshed(EntityRefreshedNotification<?, ?> notification) {
        try {
            String message = serializer.writeValueAsString(notification);
            rabbit.convertAndSend(queue.getName(), message);
        } catch (JsonProcessingException e) {
            String message = "Could not send refreshed notification message for entity %s with ID=%s"
                    .formatted(
                            notification.entityType().getSimpleName(),
                            notification.entityId().toString()
                    );
            throw new RuntimeException(message, e);
        }
    }
}
