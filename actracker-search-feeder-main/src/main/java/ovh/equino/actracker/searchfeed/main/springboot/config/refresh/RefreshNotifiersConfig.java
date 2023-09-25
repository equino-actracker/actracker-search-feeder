package ovh.equino.actracker.searchfeed.main.springboot.config.refresh;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ovh.equino.actracker.searchfeed.domain.model.EntityRefreshedNotifier;

@Configuration
@ComponentScan(value = "ovh.equino.actracker.searchfeed.infrastructure.refresh", includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = EntityRefreshedNotifier.class
        )
})
class RefreshNotifiersConfig {
}
