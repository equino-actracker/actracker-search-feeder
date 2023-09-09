package ovh.equino.actracker.searchfeed.main.springboot.config.persistence;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ovh.equino.actracker.searchfeed.domain.model.EntityStore;

@Configuration
@ComponentScan(value = "ovh.equino.actracker.searchfeed.infrastructure.persistence.jooq", includeFilters = {
        @ComponentScan.Filter(
                type = FilterType.ASSIGNABLE_TYPE,
                classes = EntityStore.class
        )
})
class JooqStores {
}
