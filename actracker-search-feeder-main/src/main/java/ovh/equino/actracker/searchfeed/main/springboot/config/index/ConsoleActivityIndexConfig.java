package ovh.equino.actracker.searchfeed.main.springboot.config.index;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ovh.equino.actracker.searchfeed.domain.model.EntityIndex;

@Configuration
@ComponentScan(value = "ovh.equino.actracker.searchfeed.infrastructure.index.console", includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = EntityIndex.class
        )
})
class ConsoleActivityIndexConfig {
}
