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

    private final String versionedIndexName;
    private final ElasticsearchClient client;

    VersionedIndex(String commonMappingPath,
                   String generalIndexName,
                   String version,
                   String environment,
                   ElasticsearchClient client) {

        this.versionedIndexName = "%s_%s_%s".formatted(generalIndexName, version, environment);
        this.client = client;

        String mappingPath = "%s/%s/%s.json".formatted(commonMappingPath, generalIndexName, version);

        try (InputStream mappingsContent = loadFileInputStream(mappingPath)) {
            if (indexExists(versionedIndexName)) {
                LOG.info("Elasticsearch version index {} already exists. Skipping.", versionedIndexName);
            } else {
                createIndex(mappingsContent);
                LOG.info("Elasticsearch versioned index {} created.", versionedIndexName);
            }
        } catch (IOException e) {
            String message = "Unable to create index '%s'".formatted(versionedIndexName);
            throw new RuntimeException(message, e);
        }
    }

    private InputStream loadFileInputStream(final String path) {
        return getClass().getResourceAsStream(path);
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
}
