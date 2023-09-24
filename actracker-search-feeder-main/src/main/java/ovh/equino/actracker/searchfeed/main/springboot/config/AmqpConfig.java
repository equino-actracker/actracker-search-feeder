package ovh.equino.actracker.searchfeed.main.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ovh.equino.actracker.searchfeed.infrastructure.notifications.NotificationsDispatcher;

import java.util.List;

@Configuration
class AmqpConfig {

    private static final String NOTIFICATION_EXCHANGE_NAME = "notification.X.topic";
    private static final String NOTIFICATION_QUEUE_NAME = "notification.Q";

    @Autowired
    private NotificationsDispatcher notificationsDispatcher;

    @Bean(name = "notificationTopic")
    TopicExchange notificationTopic() {
        return new TopicExchange(NOTIFICATION_EXCHANGE_NAME);
    }

    @Bean(name = "notificationQueue")
    Queue notificationQueue() {
        return new Queue(NOTIFICATION_QUEUE_NAME);
    }

    @Bean
    Declarables notificationBinding(TopicExchange notificationExchange, Queue notificationQueue) {

        List<Binding> bindings = notificationsDispatcher.supportedNotificationTypes().stream()
                .map(notificationType ->
                        BindingBuilder
                                .bind(notificationQueue)
                                .to(notificationExchange)
                                .with(notificationType.getCanonicalName())
                )
                .toList();

        return new Declarables(bindings);
    }

    @RabbitListener(queues = NOTIFICATION_QUEUE_NAME)
    void receiveNotification(String message) {
        notificationsDispatcher.dispatchNotification(message);
    }
}
