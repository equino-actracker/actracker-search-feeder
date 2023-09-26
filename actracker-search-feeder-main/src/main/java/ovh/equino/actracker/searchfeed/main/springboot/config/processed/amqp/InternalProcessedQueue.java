package ovh.equino.actracker.searchfeed.main.springboot.config.processed.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class InternalProcessedQueue {

    static final String INTERNAL_PROCESSED_QUEUE_NAME = "search.feeder.internal.processed.Q";

    @Bean(name = INTERNAL_PROCESSED_QUEUE_NAME)
    Queue internalProcessedQueue() {
        return new Queue(INTERNAL_PROCESSED_QUEUE_NAME);
    }
}
