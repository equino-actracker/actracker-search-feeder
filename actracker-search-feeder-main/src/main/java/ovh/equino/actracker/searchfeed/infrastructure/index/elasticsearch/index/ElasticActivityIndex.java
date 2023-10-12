package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

public class ElasticActivityIndex extends ElasticIndex {

    private static final String INDEX_NAME = "activity";

    public ElasticActivityIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }
}
