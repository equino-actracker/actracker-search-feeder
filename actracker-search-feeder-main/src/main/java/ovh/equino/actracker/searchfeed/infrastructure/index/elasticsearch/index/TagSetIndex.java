package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

public class TagSetIndex extends ElasticsearchIndex {

    private static final String INDEX_NAME = "tagset";

    public TagSetIndex(ElasticsearchClient client, String environment) {
        super(INDEX_NAME, environment, client);
    }
}
