package ovh.equino.actracker.searchfeed.main.springboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ovh.equino.actracker.searchfeed.application.activity.ActivityService;

@Configuration
@ComponentScan(value = "ovh.equino.actracker.searchfeed.application", includeFilters = {
    @ComponentScan.Filter(
            type = FilterType.ASSIGNABLE_TYPE,
            classes = ActivityService.class
    )
})
class ApplicationConfig {
}
