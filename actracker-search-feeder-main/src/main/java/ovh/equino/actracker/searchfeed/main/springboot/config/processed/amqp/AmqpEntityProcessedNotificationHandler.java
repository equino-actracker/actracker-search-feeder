package ovh.equino.actracker.searchfeed.main.springboot.config.processed.amqp;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ovh.equino.actracker.searchfeed.infrastructure.processed.EntityProcessedNotificationDispatcher;
import ovh.equino.actracker.searchfeed.infrastructure.processed.EntityProcessedNotificationSerializer;

import static ovh.equino.actracker.searchfeed.main.springboot.config.processed.amqp.InternalProcessedQueue.INTERNAL_PROCESSED_QUEUE_NAME;

@Component
@RabbitListener(queues = INTERNAL_PROCESSED_QUEUE_NAME)
class AmqpEntityProcessedNotificationHandler {

    @Autowired
    private EntityProcessedNotificationSerializer serializer;

    @Autowired
    private EntityProcessedNotificationDispatcher notificationDispatcher;

    @RabbitHandler
    void consume(String message) {
        notificationDispatcher.dispatchNotification(serializer.deserialize(message));
    }
}
