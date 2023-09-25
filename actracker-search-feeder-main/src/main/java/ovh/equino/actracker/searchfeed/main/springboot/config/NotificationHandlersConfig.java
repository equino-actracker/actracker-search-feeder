package ovh.equino.actracker.searchfeed.main.springboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ovh.equino.actracker.searchfeed.infrastructure.notifications.NotificationHandler;

@Configuration
@ComponentScan(value = "ovh.equino.actracker.searchfeed.infrastructure.notifications", includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = NotificationHandler.class
        )
})
class NotificationHandlersConfig {
}
