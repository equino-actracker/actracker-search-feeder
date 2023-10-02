package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.transport.endpoints.BooleanResponse;

import java.io.IOException;
import java.io.InputStream;

public class TagSetIndex {

    private static final String MAPPINGS_PATH = "/elasticsearch/mappings/tagset/v0001.json";
    private final String indexName;

    private final ElasticsearchClient client;

    public TagSetIndex(ElasticsearchClient client, String environment) {
        this.client = client;
        this.indexName = "%s_%s_%s".formatted("tagset", "v0001", environment);
    }

    public void create() {
        try (InputStream mappings = loadFileInputStream(MAPPINGS_PATH)) {
            if (!indexExists()) {
                CreateIndexRequest createIndexRequest = new CreateIndexRequest.Builder()
                        .index(indexName)
                        .withJson(mappings)
                        .build();
                client.indices().create(createIndexRequest);
            }
        } catch (IOException e) {
            String message = "Unable to create index '%s'".formatted(indexName);
            throw new RuntimeException(message, e);
        }
    }

    private boolean indexExists() throws IOException {
        ExistsRequest indexExistsRequest = new ExistsRequest.Builder()
                .index(indexName)
                .build();
        BooleanResponse exists = client.indices().exists(indexExistsRequest);
        return exists.value();
    }

    private InputStream loadFileInputStream(final String path) {
        return getClass().getResourceAsStream(path);
    }
}
