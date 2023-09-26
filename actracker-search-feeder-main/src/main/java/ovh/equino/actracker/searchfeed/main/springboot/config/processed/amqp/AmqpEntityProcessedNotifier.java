package ovh.equino.actracker.searchfeed.main.springboot.config.processed.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotification;
import ovh.equino.actracker.searchfeed.infrastructure.processed.EntityProcessedNotificationSerializer;

class AmqpEntityProcessedNotifier {

    @Autowired
    @Qualifier(InternalProcessedQueue.INTERNAL_PROCESSED_QUEUE_NAME)
    protected Queue queue;

    @Autowired
    protected RabbitTemplate rabbit;

    @Autowired
    private EntityProcessedNotificationSerializer serializer;

    protected void notifyEntityProcessed(EntityProcessedNotification<?, ?> notification) {
        String message = serializer.serialize(notification);
        rabbit.convertAndSend(queue.getName(), message);
    }
}
