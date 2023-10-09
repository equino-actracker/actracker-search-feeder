package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;

class VersionedIndex {

    private final String mappingPath;
    private final String environment;
    private final ElasticsearchClient client;

    VersionedIndex(String mappingPath, String environment, ElasticsearchClient client) {
        this.mappingPath = mappingPath;
        this.environment = environment;
        this.client = client;
    }
}
