package ovh.equino.actracker.searchfeed.main.springboot.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class AmqpConfig {

    private static final String EXCHANGE_NAME = "notification.X.topic";
    private static final String QUEUE_NAME = "notification.Q";

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
        return BindingBuilder.bind(queue).to(exchange).with("#");
    }

    @RabbitListener(queues = QUEUE_NAME)
    void receiveMessage(String message) {
        System.out.printf("Received message: %s%n", message);
    }
}
