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
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class TagSetIndex {

    Logger LOG = LoggerFactory.getLogger(TagSetIndex.class);

    private static final String MAPPINGS_PATH = "/elasticsearch/mappings/tagset/v0001.json";
    private static final String MAPPINGS_DIR_PATH = "/elasticsearch/mappings/tagset/";
    private final String indexName;

    private final ElasticsearchClient client;

    public TagSetIndex(ElasticsearchClient client, String environment) {
        this.client = client;
        this.indexName = "%s_%s_%s".formatted("tagset", "v0001", environment);
    }

    public void create() {
        try {
            getAllFilesInDir();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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

    private void getAllFilesInDir() throws IOException {
        final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        LOG.error("Source code path is {} and isjar={}", jarFile.getPath(), jarFile.isFile());
        URL resource = getClass().getResource(MAPPINGS_DIR_PATH);
        LOG.error("Resource path: {}", resource.getPath());
        File file = new File(resource.getPath());
        LOG.error("File exists: {}, isDir: {}", file.exists(), file.isDirectory());
        String[] subFiles = file.list();
        LOG.error("Subfiles: {}", subFiles);

//        if (jarFile.isFile()) {  // Run with JAR file
//            final JarFile jar = new JarFile(jarFile);
//            final Enumeration<JarEntry> entries = jar.entries(); //gives ALL entries in jar
//            while (entries.hasMoreElements()) {
//                final String name = entries.nextElement().getName();
//                if (name.startsWith(MAPPINGS_DIR_PATH)) { //filter according to the path
//                    LOG.error("Mapping found in Jar: {}", name);
//                }
//            }
//            jar.close();
//        } else { // Run with IDE
//            final URL url = getClass().getResource(MAPPINGS_DIR_PATH);
//            if (url != null) {
//                try {
//                    final File apps = new File(url.toURI());
//                    for (File app : apps.listFiles()) {
//                        LOG.error("Mapping found in Filesystem: {}", app);
//                    }
//                } catch (URISyntaxException ex) {
//                    throw new RuntimeException(ex);
//                }
//            }
//        }
    }
}
