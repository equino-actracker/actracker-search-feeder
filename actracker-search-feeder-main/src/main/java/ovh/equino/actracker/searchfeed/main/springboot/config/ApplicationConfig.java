package ovh.equino.actracker.searchfeed.main.springboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ovh.equino.actracker.searchfeed.application.activity.ActivityService;
import ovh.equino.actracker.searchfeed.application.tag.TagService;
import ovh.equino.actracker.searchfeed.application.tagset.TagSetService;

@Configuration
@ComponentScan(value = "ovh.equino.actracker.searchfeed.application", includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {
                        ActivityService.class,
                        TagService.class,
                        TagSetService.class
                }
        )
})
class ApplicationConfig {
}
