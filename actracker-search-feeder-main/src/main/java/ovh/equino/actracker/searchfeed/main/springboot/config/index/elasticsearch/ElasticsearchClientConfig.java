package ovh.equino.actracker.searchfeed.main.springboot.config.index.elasticsearch;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ElasticsearchClientConfig extends ElasticsearchConfiguration {

    @Value("${actracker-search-feeder.elasticsearch.url:localhost:9200}")
    private String url;

    @Value("${actracker-search-feeder.elasticsearch.username:elastic}")
    private String username;

    @Value("${actracker-search-feeder.elasticsearch.password:elastic}")
    private String password;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .withBasicAuth(username, password)
                .build();
    }
}