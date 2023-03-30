package ovh.equino.actracker.searchfeed.main.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ovh.equino.actracker.searchfeed.infrastructure.messaging.MessageDispatcher;

import java.util.List;

@Configuration
class AmqpConfig {

    private static final String EXCHANGE_NAME = "notification.X.topic";
    private static final String QUEUE_NAME = "notification.Q";

    @Autowired
    private MessageDispatcher messageDispatcher;

    @Bean
    TopicExchange topic() {
        return new TopicExchange(EXCHANGE_NAME);
    }

    @Bean
    Queue notificationQueue() {
        return new Queue(QUEUE_NAME);
    }

    @Bean
    Declarables notificationBinding(TopicExchange exchange, Queue queue) {

        List<Binding> bindings = messageDispatcher.supportedNotificationTypes().stream()
                .map(notificationType ->
                        BindingBuilder
                                .bind(queue)
                                .to(exchange)
                                .with(notificationType.getCanonicalName())
                )
                .toList();

        return new Declarables(bindings);
    }

    @RabbitListener(queues = QUEUE_NAME)
    void receiveMessage(String message) {
        messageDispatcher.dispatchMessage(message);
    }
}
