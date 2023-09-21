package ovh.equino.actracker.searchfeed.main.springboot.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ovh.equino.actracker.searchfeed.domain.model.ChildrenNotifierOfParentRefresh;
import ovh.equino.actracker.searchfeed.domain.services.EntityIndexer;
import ovh.equino.actracker.searchfeed.domain.services.EntityProcessor;

@Configuration
@ComponentScan(value = "ovh.equino.actracker.searchfeed.domain.services", includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = {EntityIndexer.class, EntityProcessor.class, ChildrenNotifierOfParentRefresh.class}
        )
})
class DomainServicesConfig {
}
