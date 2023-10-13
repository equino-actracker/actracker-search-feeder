package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

public class ElasticTagSetIndex extends ElasticIndex {

    private static final String INDEX_NAME = "tagset";

    public ElasticTagSetIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }
}
