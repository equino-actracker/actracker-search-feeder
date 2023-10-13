package ovh.equino.actracker.searchfeed.main.springboot.config.index.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ovh.equino.actracker.searchfeed.domain.model.activity.ActivityIndex;
import ovh.equino.actracker.searchfeed.domain.model.dashboard.DashboardIndex;
import ovh.equino.actracker.searchfeed.domain.model.tag.TagIndex;
import ovh.equino.actracker.searchfeed.domain.model.tagset.TagSetIndex;
import ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index.ElasticActivityIndex;
import ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index.ElasticDashboardIndex;
import ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index.ElasticTagIndex;
import ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index.ElasticTagSetIndex;

@Configuration
class ElasticsearchIndexConfig {

    private final ElasticActivityIndex activityIndex;
    private final ElasticTagIndex tagIndex;
    private final ElasticTagSetIndex tagSetIndex;
    private final ElasticDashboardIndex dashboardIndex;

    ElasticsearchIndexConfig(ElasticsearchClient elasticsearchClient,
                             @Value("${actracker-search-feeder.environment:local}") String environment) {

        this.activityIndex = new ElasticActivityIndex(elasticsearchClient, environment);
        this.tagIndex = new ElasticTagIndex(elasticsearchClient, environment);
        this.tagSetIndex = new ElasticTagSetIndex(elasticsearchClient, environment);
        this.dashboardIndex = new ElasticDashboardIndex(elasticsearchClient, environment);
    }

    @PostConstruct
    void createIndex() {
        activityIndex.create();
        tagIndex.create();
        tagSetIndex.create();
        dashboardIndex.create();
    }

    @Bean
    ActivityIndex activityIndex() {
        return activityIndex;
    }

    @Bean
    TagIndex tagIndex() {
        return tagIndex;
    }

    @Bean
    TagSetIndex tagSetIndex() {
        return tagSetIndex;
    }

    @Bean
    DashboardIndex dashboardIndex() {
        return dashboardIndex;
    }

}
