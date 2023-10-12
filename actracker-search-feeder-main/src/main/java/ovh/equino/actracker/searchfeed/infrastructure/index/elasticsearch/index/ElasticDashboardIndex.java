package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

public class ElasticDashboardIndex extends ElasticIndex {

    private static final String INDEX_NAME = "dashboard";

    public ElasticDashboardIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }
}
