package ovh.equino.actracker.searchfeed.main.springboot.config.refresh.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotification;

class AmqpEntityRefreshedNotifier {

    @Autowired
    @Qualifier(InternalRefreshQueue.INTERNAL_REFRESHED_QUEUE_NAME)
    protected Queue queue;

    @Autowired
    protected RabbitTemplate rabbit;

    protected void notifyEntityRefreshed(EntityRefreshedNotification<?, ?> notification) {
        rabbit.convertAndSend(queue.getName(), notification.toString());
    }
}
