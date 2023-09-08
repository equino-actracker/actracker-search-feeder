package ovh.equino.actracker.searchfeed.main.springboot.config;

import org.jooq.conf.MappedSchema;
import org.jooq.conf.RenderMapping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jooq.DefaultConfigurationCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
// https://blog.jooq.org/how-to-customise-a-jooq-configuration-that-is-injected-using-spring-boot/
class JooqConfig {

    @Value("${actracker-search-feeder.postgres.schema:public}")
    private String schemaName;

    @Bean
    DefaultConfigurationCustomizer configurationCustomizer() {
        return configuration -> configuration.settings()
                .withRenderMapping(new RenderMapping()
                        .withSchemata(
                                new MappedSchema()
                                        .withInput("public")
                                        .withOutput(schemaName)
                        )
                );
    }
}
