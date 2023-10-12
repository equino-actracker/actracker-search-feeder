package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

public class ElasticTagIndex extends ElasticIndex {

    private static final String INDEX_NAME = "tag";

    public ElasticTagIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }
}
