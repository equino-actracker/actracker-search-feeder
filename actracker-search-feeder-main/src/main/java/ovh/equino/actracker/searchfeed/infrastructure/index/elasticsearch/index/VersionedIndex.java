package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

class VersionedIndex {

    private static final Logger LOG = LoggerFactory.getLogger(VersionedIndex.class);

    private final String version;
    private final String versionedIndexName;
    private final ElasticsearchClient client;
    private final String mappingPath;

    VersionedIndex(String mappingsDirPath,
                   String generalIndexName,
                   String version,
                   ElasticsearchClient client) {

        this.versionedIndexName = "%s_%s".formatted(generalIndexName, version);
        this.mappingPath = "%s/%s.json".formatted(mappingsDirPath, version);
        this.version = version;
        this.client = client;
    }

    void create() {
        try {
            try (InputStream mappingsContent = getClass().getResourceAsStream(mappingPath)) {
                if (indexExists(versionedIndexName)) {
                    LOG.info("Elasticsearch versioned index {} already exists. Skipping.", versionedIndexName);
                } else {
                    createIndex(mappingsContent);
                    LOG.info("Elasticsearch versioned index {} created.", versionedIndexName);
                }
            }
        } catch (IOException e) {
            String message = "Unable to create index '%s'".formatted(versionedIndexName);
            throw new RuntimeException(message, e);
        }
    }

    private boolean indexExists(String versionedIndexName) throws IOException {
        ExistsRequest indexExistsRequest = new ExistsRequest.Builder()
                .index(versionedIndexName)
                .build();
        BooleanResponse exists = client.indices().exists(indexExistsRequest);
        return exists.value();
    }

    private void createIndex(InputStream mappingsContent) throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                .index(versionedIndexName)
                .withJson(mappingsContent)
                .build();
        client.indices().create(createIndexRequest);
    }

    String version() {
        return this.version;
    }

    String indexName() {
        return this.versionedIndexName;
    }
}
