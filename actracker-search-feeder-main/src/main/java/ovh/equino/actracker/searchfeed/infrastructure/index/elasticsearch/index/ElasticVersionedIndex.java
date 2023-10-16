package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.DeleteRequest;
import co.elastic.clients.elasticsearch.core.IndexRequest;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

class ElasticVersionedIndex {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticVersionedIndex.class);

    private final String version;
    private final String versionedIndexName;
    private final ElasticsearchClient client;
    private final String mappingPath;

    private final ObjectMapper documentSerializer;

    ElasticVersionedIndex(String mappingsDirPath,
                          String generalIndexName,
                          String version,
                          ElasticsearchClient client) {

        this.versionedIndexName = "%s_%s".formatted(generalIndexName, version);
        this.mappingPath = "%s/%s.json".formatted(mappingsDirPath, version);
        this.version = version;
        this.client = client;

        this.documentSerializer = new ObjectMapper();
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

    void indexDocument(ElasticDocument document) {
        try {
            ByteArrayInputStream documentAsStream = new ByteArrayInputStream(
                    documentSerializer.writeValueAsBytes(document)
            );
            IndexRequest<?> indexRequest = new IndexRequest.Builder<>()
                    .index(versionedIndexName)
                    .id(document.id())
                    .withJson(documentAsStream)
                    .build();
            client.index(indexRequest);
        } catch (IOException e) {
            String message = "Error occurred while indexing a document with ID=%s into index '%s'"
                    .formatted(document.id(), versionedIndexName);
            throw new RuntimeException(message, e);
        }
    }

    void deleteDocument(String documentId) {
        DeleteRequest deleteRequest = new DeleteRequest.Builder()
                .index(versionedIndexName)
                .id(documentId)
                .build();
        try {
            client.delete(deleteRequest);
        } catch (IOException e) {
            String message = "Error occurred while deleting a document with ID=%s from index '%s'"
                    .formatted(documentId, versionedIndexName);
            throw new RuntimeException(message, e);
        }
    }
}
