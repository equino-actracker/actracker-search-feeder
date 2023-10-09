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
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.*;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        } catch (URISyntaxException e) {
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

    private void getAllFilesInDir() throws IOException, URISyntaxException {
        final File jarFile = new File(getClass().getProtectionDomain().getCodeSource().getLocation().getPath());
        LOG.error("Source code path is {} and isjar={}", jarFile.getPath(), jarFile.isFile());
        URL resource = getClass().getResource(MAPPINGS_DIR_PATH);
        URI resourceUri = resource.toURI();
//        File file1 = new File(resourceUri);
//        LOG.error("Resource path: {}, URI: {}", resource.getPath(), resourceUri);
//        File file = new File(resource.getPath());
//        LOG.error("File exists: {}, isDir: {}", file1.exists(), file1.isDirectory());
//        String[] subFiles = file1.list();
//        LOG.error("Subfiles: {}", subFiles);

        Path mappingsPath;
        if ("jar".equals(resourceUri.getScheme())) {
            try (FileSystem fileSystem = FileSystems.newFileSystem(resourceUri, Collections.emptyMap())){
                Path filesystemRoot = fileSystem.getPath("/");

                // Get all contents of a resource (skip resource itself), if entry is a directory remove trailing /
                List<String> resourcesNames =
                        Files.walk(filesystemRoot)
                                .map(Path::toString)
//                                .filter(path -> path.contains(MAPPINGS_DIR_PATH))
//                                .filter(path -> path.endsWith(".json"))
                                .sorted()
                                .collect(Collectors.toList());
                LOG.error("Subfiles names from Jar: {}", resourcesNames);

//                return resourcesNames.toArray(new String[resourcesNames.size()]);
            }
        } else {
            mappingsPath = Paths.get(resourceUri);
            LOG.error("Found path in filesystem: {}", mappingsPath);
        }


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
