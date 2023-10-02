package ovh.equino.actracker.searchfeed.main.springboot.config.index.elasticsearch;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index.TagSetIndex;

@Configuration
class ElasticsearchIndexConfig {

    private final TagSetIndex tagSetIndex;

    ElasticsearchIndexConfig(ElasticsearchClient elasticsearchClient,
                             @Value("${actracker-search-feeder.environment:local}") String environment) {

        this.tagSetIndex = new TagSetIndex(elasticsearchClient, environment);
    }

    @PostConstruct
    void createIndex() {
        tagSetIndex.create();
    }
}
