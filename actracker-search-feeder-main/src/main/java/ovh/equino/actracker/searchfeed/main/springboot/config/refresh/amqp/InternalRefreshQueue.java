package ovh.equino.actracker.searchfeed.main.springboot.config.refresh.amqp;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class InternalRefreshQueue {

    static final String INTERNAL_REFRESHED_QUEUE_NAME = "search.feeder.internal.refreshed.Q";

    @Bean(name = INTERNAL_REFRESHED_QUEUE_NAME)
    Queue internalRefreshedQueue() {
        return new Queue(INTERNAL_REFRESHED_QUEUE_NAME);
    }
}
