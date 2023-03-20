package ovh.equino.actracker.searchfeed.main.springboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ovh.equino.actracker.searchfeed.infrastructure.messaging.MessageDispatcher;

@Configuration
@ComponentScan(value = "ovh.equino.actracker.searchfeed.infrastructure.messaging", includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = MessageDispatcher.class
        )
})
class MessageDispatcherConfig {
}
