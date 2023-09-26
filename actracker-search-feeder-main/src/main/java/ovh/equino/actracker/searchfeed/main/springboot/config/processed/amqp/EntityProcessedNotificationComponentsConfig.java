package ovh.equino.actracker.searchfeed.main.springboot.config.processed.amqp;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ovh.equino.actracker.searchfeed.infrastructure.processed.EntityProcessedNotificationDispatcher;
import ovh.equino.actracker.searchfeed.infrastructure.processed.EntityProcessedNotificationSerializer;

@Configuration
@ComponentScan(value = "ovh.equino.actracker.searchfeed.infrastructure.processed", includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {EntityProcessedNotificationSerializer.class, EntityProcessedNotificationDispatcher.class}
        )
})
class EntityProcessedNotificationComponentsConfig {
}
