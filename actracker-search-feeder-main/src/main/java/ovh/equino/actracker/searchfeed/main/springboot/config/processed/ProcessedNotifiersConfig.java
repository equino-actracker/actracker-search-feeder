package ovh.equino.actracker.searchfeed.main.springboot.config.processed;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ovh.equino.actracker.searchfeed.domain.model.EntityProcessedNotifier;

@Configuration
@ComponentScan(value = "ovh.equino.actracker.searchfeed.infrastructure.processed", includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = EntityProcessedNotifier.class
        )
})
class ProcessedNotifiersConfig {
}
