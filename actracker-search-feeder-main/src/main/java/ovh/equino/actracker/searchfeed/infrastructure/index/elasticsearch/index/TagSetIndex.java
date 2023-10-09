package ovh.equino.actracker.searchfeed.infrastructure.index.elasticsearch.index;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.indices.CreateIndexRequest;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.transport.endpoints.BooleanResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class TagSetIndex {

    Logger LOG = LoggerFactory.getLogger(TagSetIndex.class);

    private static final String MAPPINGS_PATH = "/elasticsearch/mappings/tagset/v0001.json";
    private final String indexName;

    private final ElasticsearchClient client;

    public TagSetIndex(ElasticsearchClient client, String environment) {
        this.client = client;
        this.indexName = "%s_%s_%s".formatted("tagset", "v0001", environment);
    }

    public void create() {
        URL directory = getClass().getResource("/elasticsearch/mappings/tagset");
        File file = new File(directory.getPath());
        boolean exists = file.exists();
        boolean isDir = file.isDirectory();
        String[] filesList = file.list();

        LOG.error("Directory found. Exists: {}, IsDir: {}, Files: {}", exists, isDir, filesList);

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
