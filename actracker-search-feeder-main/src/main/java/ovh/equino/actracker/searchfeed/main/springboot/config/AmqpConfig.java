package ovh.equino.actracker.searchfeed.main.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ovh.equino.actracker.searchfeed.infrastructure.messaging.MessageDispatcher;

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
    Binding notificationBinding(TopicExchange exchange, Queue queue) {
        return BindingBuilder.bind(queue).to(exchange).with("ActivityChangedNotification");
    }

    @RabbitListener(queues = QUEUE_NAME)
    void receiveMessage(String message) {
        messageDispatcher.dispatchMessage(message);
    }
}
