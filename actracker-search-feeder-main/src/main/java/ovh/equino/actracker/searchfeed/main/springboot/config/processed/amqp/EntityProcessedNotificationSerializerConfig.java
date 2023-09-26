package ovh.equino.actracker.searchfeed.main.springboot.config.processed.amqp;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ovh.equino.actracker.searchfeed.infrastructure.processed.EntityProcessedNotificationSerializer;

@Configuration
class EntityProcessedNotificationSerializerConfig {

    @Bean
    EntityProcessedNotificationSerializer serializer() {
        return new EntityProcessedNotificationSerializer();
    }
}
